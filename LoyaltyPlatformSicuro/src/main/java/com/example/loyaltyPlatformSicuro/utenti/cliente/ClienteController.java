package com.example.loyaltyPlatformSicuro.utenti.cliente;

import com.example.loyaltyPlatformSicuro.DTO.*;
import com.example.loyaltyPlatformSicuro.integrazioni.PagamentoSimulato;
import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.Configurazione.Premio;
import com.example.loyaltyPlatformSicuro.utenti.cliente.tessera.Tessera;
import com.example.loyaltyPlatformSicuro.utenti.cliente.tessera.TesseraService;
import com.example.loyaltyPlatformSicuro.utenti.transazione.Transazione;
import com.example.loyaltyPlatformSicuro.utenti.transazione.TransazioneService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final TesseraService tesseraService;

    private final TransazioneService transazioneService;

    private final ModelMapper modelMapper;

    @Autowired
    public ClienteController(ClienteService clienteService, TesseraService tesseraService, TransazioneService transazioneService, ModelMapper modelMapper) {
        this.clienteService = clienteService;
        this.tesseraService = tesseraService;
        this.transazioneService = transazioneService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/registrazione", consumes = "application/json")
    public ResponseEntity<?> registrazioneCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            clienteService.registrazioneConDTO(clienteDTO);
            return ResponseEntity.ok(Map.of("message", "Registrazione completata con successo!", "redirect", "/benvenuto"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Errore nella registrazione: " + e.getMessage()));
        }
    }

    @GetMapping("/{clienteId}/dashboardCliente")
    public ResponseEntity<?> dashboardCliente(@PathVariable Long clienteId) {
        Cliente cliente = clienteService.getClienteById(clienteId);
        if (cliente != null) {
            return ResponseEntity.ok(cliente); // Restituisci i dati del cliente invece di ModelAndView
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{clienteId}/{tesseraId}")
    public ResponseEntity<Tessera> getTesseraById(@PathVariable Long tesseraId, @PathVariable Long clienteId) {
        Tessera tessera = tesseraService.trovaTesseraPerId(tesseraId); // Usa il servizio della tessera per ottenere i dati della tessera
        if (tessera != null) {
            return ResponseEntity.ok(tessera);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{clienteId}/iscrivi-programma-fedelta")
    public ResponseEntity<?> iscriviClienteAProgrammaFedelta(@PathVariable Long clienteId, @RequestBody IscrizioneProgrammaFedeltaDTO iscrizioneDTO) {
        try {
            Long tesseraId = iscrizioneDTO.getTesseraId();
            Long programmaFedeltaId = iscrizioneDTO.getProgrammaFedeltaId();
            Long aziendaId = iscrizioneDTO.getAziendaId();

            boolean iscrizioneRiuscita = tesseraService.iscriviTesseraAProgrammaFedelta(tesseraId, programmaFedeltaId, aziendaId);
            if (iscrizioneRiuscita) {
                return new ResponseEntity<>("Iscrizione avvenuta con successo", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("La tessera è già iscritta a questo programma fedeltà", HttpStatus.BAD_REQUEST);
            }
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Tessera, programma fedeltà o azienda non trovati", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{clienteId}/effettua-acquisto")
    public ResponseEntity<?> effettuaAcquisto(@PathVariable Long clienteId, @RequestBody AcquistoDTO acquistoDTO) {
        // Chiamata al servizio per effettuare l'acquisto
        PagamentoSimulato pagamento = transazioneService.effettuaAcquistoCliente(acquistoDTO);

        if (pagamento.isConferma()) {
            Transazione transazione = transazioneService.getUltimaTransazioneCliente(clienteId); // Ottieni la transazione dalla chiamata

            // Chiamata diretta al metodo per aggiungere i punti al programma fedeltà e ottenere i punti guadagnati
            int puntiGuadagnati = tesseraService.aggiungiPuntiProgrammaFedelta(transazione);

            // Creazione della risposta che include i punti guadagnati
            String responseMessage = "Pagamento effettuato con successo. Importo: " + pagamento.getImportoPagato()
                    + "  " + pagamento.getDataTransazione() + " " + pagamento.getNomeAzienda()
                    + ". Hai guadagnato " + puntiGuadagnati + " punti.";

            return ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity.badRequest().body("Pagamento non confermato. Messaggio: " + pagamento.getMessaggio());
        }
    }
    @GetMapping("/{clienteId}/{tesseraId}/{programmaPuntiId}/premiAssociati")
    public ResponseEntity<List<PremioDTO>> getPremiPerTesseraEProgramma(
            @PathVariable Long tesseraId,
            @PathVariable Long programmaPuntiId,
            @PathVariable Long clienteId) {
        try {
            List<Premio> premi = tesseraService.getPremiPerProgrammaPuntiSeAssociato(tesseraId, programmaPuntiId);

            // Creare una lista di PremioDTO mappando gli oggetti Premio
            List<PremioDTO> premiDTO = premi.stream()
                    .map(premio -> modelMapper.map(premio, PremioDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(premiDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/{clienteId}/{tesseraId}/{programmaFedeltaId}/riscattaPremio")
    public ResponseEntity<String> riscattaPremio(
            @PathVariable Long tesseraId,
            @PathVariable Long programmaFedeltaId,
            @RequestBody PremioDTO premioDTO, @PathVariable String clienteId) {

        try {
            boolean riscattoAvvenuto = tesseraService.riscattaPremio(tesseraId, programmaFedeltaId, premioDTO);

            if (riscattoAvvenuto) {
                return ResponseEntity.ok("Premio riscattato con successo.");
            } else {
                return ResponseEntity.badRequest().body("Impossibile riscattare il premio.");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{clienteId}/{tesseraId}/storico-punti")
    public List<StoricoPuntiDTO> getStoricoPuntiByTesseraId(@PathVariable Long tesseraId, @PathVariable String clienteId) {
        return tesseraService.getStoricoPuntiByTesseraId(tesseraId);
    }


}

