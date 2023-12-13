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


/**
 * Questa classe è responsabile di gestire le richieste relative ai clienti e alle loro operazioni.
 */
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final TesseraService tesseraService;

    private final TransazioneService transazioneService;

    private  ModelMapper modelMapper;

    /**
     * Crea una nuova istanza di ClienteController.
     *
     * @param clienteService      Il servizio per la gestione dei clienti.
     * @param tesseraService      Il servizio per la gestione delle tessere.
     * @param transazioneService  Il servizio per la gestione delle transazioni.
     */

    @Autowired
    public ClienteController(ClienteService clienteService, TesseraService tesseraService, TransazioneService transazioneService) {
        this.clienteService = clienteService;
        this.tesseraService = tesseraService;
        this.transazioneService = transazioneService;
        this.modelMapper = new ModelMapper();


    }
    /**
     * Gestisce la registrazione di un cliente.
     *
     * @param clienteDTO  I dati del cliente da registrare.
     * @return            Una risposta HTTP che indica se la registrazione è avvenuta con successo o è fallita.
     */

    @PostMapping(path = "/registrazione", consumes = "application/json")
    public ResponseEntity<?> registrazioneCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            clienteService.registrazioneConDTO(clienteDTO);
            return ResponseEntity.ok(Map.of("message", "Registrazione completata con successo!", "redirect", "/benvenuto"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Errore nella registrazione: " + e.getMessage()));
        }
    }

    /**
     * Restituisce il dashboard del cliente.
     *
     * @param clienteId  L'ID del cliente.
     * @return           Una risposta HTTP contenente i dati del cliente.
     */


    @GetMapping("/{clienteId}/dashboardCliente")
    public ResponseEntity<?> dashboardCliente(@PathVariable Long clienteId) {
        Cliente cliente = clienteService.getClienteById(clienteId);
        if (cliente != null) {
            return ResponseEntity.ok(cliente); // Restituisci i dati del cliente invece di ModelAndView
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    /**
     * Restituisce i dettagli di una tessera specifica per un cliente.
     *
     * @param tesseraId  L'ID della tessera da recuperare.
     * @param clienteId  L'ID del cliente a cui appartiene la tessera.
     * @return           Una risposta HTTP contenente i dati della tessera o uno stato "not found" se la tessera non esiste.
     */

    @GetMapping("/{clienteId}/{tesseraId}")
    public ResponseEntity<Tessera> getTesseraById(@PathVariable Long tesseraId, @PathVariable Long clienteId) {
        Tessera tessera = tesseraService.trovaTesseraPerId(tesseraId); // Usa il servizio della tessera per ottenere i dati della tessera
        if (tessera != null) {
            return ResponseEntity.ok(tessera);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Iscrive un cliente a un programma fedeltà specifico.
     *
     * @param clienteId          L'ID del cliente da iscrivere al programma fedeltà.
     * @param iscrizioneDTO      I dati per l'iscrizione, inclusi l'ID della tessera, l'ID del programma fedeltà e l'ID dell'azienda.
     * @return                   Una risposta HTTP che indica se l'iscrizione è avvenuta con successo o è fallita.
     */


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

    /**
     * Effettua un acquisto per un cliente e registra i punti guadagnati.
     *
     * @param clienteId     L'ID del cliente che effettua l'acquisto.
     * @param acquistoDTO   I dati dell'acquisto, inclusi l'importo e il nome dell'azienda.
     * @return              Una risposta HTTP che indica se l'acquisto è avvenuto con successo o è fallito.
     */



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
    /**
     * Restituisce una lista di premi associati a una tessera e a un programma fedeltà specifici.
     *
     * @param tesseraId          L'ID della tessera.
     * @param programmaPuntiId   L'ID del programma fedeltà.
     * @param clienteId          L'ID del cliente a cui appartiene la tessera.
     * @return                   Una risposta HTTP contenente una lista di PremioDTO o uno stato "not found" se non sono disponibili premi.
     */


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

    /**
     * Riscatta un premio per un cliente all'interno di un programma fedeltà specifico.
     *
     * @param tesseraId          L'ID della tessera.
     * @param programmaFedeltaId L'ID del programma fedeltà.
     * @param premioDTO          I dati del premio da riscattare.
     * @param clienteId          L'ID del cliente a cui appartiene la tessera.
     * @return                   Una risposta HTTP che indica se il riscatto del premio è avvenuto con successo o è fallito.
     */


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
    /**
     * Restituisce lo storico dei punti per una tessera specifica.
     *
     * @param tesseraId  L'ID della tessera.
     * @param clienteId  L'ID del cliente a cui appartiene la tessera.
     * @return           Una lista di StoricoPuntiDTO che rappresenta lo storico dei punti per la tessera.
     */

    @GetMapping("/{clienteId}/{tesseraId}/storico-punti")
    public List<StoricoPuntiDTO> getStoricoPuntiByTesseraId(@PathVariable Long tesseraId, @PathVariable String clienteId) {
        return tesseraService.getStoricoPuntiByTesseraId(tesseraId);
    }


}

