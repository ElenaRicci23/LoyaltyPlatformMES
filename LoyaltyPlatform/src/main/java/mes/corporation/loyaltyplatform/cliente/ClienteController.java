package mes.corporation.loyaltyplatform.cliente;

import jakarta.persistence.EntityNotFoundException;
import mes.corporation.loyaltyplatform.DTO.AcquistoDTO;
import mes.corporation.loyaltyplatform.DTO.ClienteDTO;
import mes.corporation.loyaltyplatform.DTO.IscrizioneProgrammaFedeltaDTO;
import mes.corporation.loyaltyplatform.DTO.PremioDTO;
import mes.corporation.loyaltyplatform.azienda.AziendaService;
import mes.corporation.loyaltyplatform.model.Premio;
import mes.corporation.loyaltyplatform.storico.StoricoPunti;
import mes.corporation.loyaltyplatform.storico.StoricoPuntiService;
import mes.corporation.loyaltyplatform.tessera.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Questa classe gestisce le operazioni relative ai clienti all'interno dell'applicazione.
 */
@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    private TesseraService tesseraService;
    @Autowired
    private StoricoPuntiService storicoPuntiService;
    @Autowired
    private AziendaService aziendaService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Restituisce tutti i clienti presenti nel sistema.
     *
     * @return Una ResponseEntity contenente la lista di clienti o uno stato di not found se la lista è vuota.
     */
    @GetMapping("/")
    public ResponseEntity<List<Cliente>> getAllClienti() {
        List<Cliente> clienti = clienteService.getAllClienti();
        return new ResponseEntity<>(clienti, HttpStatus.OK);
    }

    /**
     * Restituisce un cliente in base al suo ID.
     *
     * @param id L'ID del cliente da cercare.
     * @return Una ResponseEntity contenente il cliente trovato o uno stato di not found se il cliente non esiste.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Crea un nuovo cliente nel sistema.
     *
     * @param clienteDTO I dati del cliente da creare.
     * @return Una ResponseEntity contenente il cliente creato con successo o uno stato di errore in caso di problemi durante la creazione.
     */
    @PostMapping("/registra")
    public ResponseEntity<Cliente> createCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            // Chiamare il servizio per creare il cliente
            Cliente nuovoCliente = clienteService.createCliente(clienteDTO);

            if (nuovoCliente != null) {
                // Il cliente è stato creato con successo, restituisci una risposta di successo
                return new ResponseEntity<>(nuovoCliente, HttpStatus.CREATED);
            } else {
                // Gestisci il caso in cui la creazione del cliente non sia riuscita
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            // Gestisci eventuali eccezioni qui e restituisci una risposta di errore appropriata
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Iscrive un cliente a un programma fedeltà di un'azienda tramite la tessera.
     *
     * @param clienteId     L'ID del cliente da iscrivere.
     * @param iscrizioneDTO I dati per l'iscrizione al programma fedeltà.
     * @return Una ResponseEntity contenente un messaggio di successo o uno stato di errore se l'iscrizione non è riuscita.
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
     * Restituisce i programmi punti associati a una tessera.
     *
     * @param tesseraId L'ID della tessera.
     * @return Una ResponseEntity contenente una lista di programmi punti o uno stato di not found se la lista è vuota.
     */
    @GetMapping("/{tesseraId}/programmi-punti")
    public ResponseEntity<List<Object[]>> getProgrammiPuntiByTesseraId(@PathVariable Long tesseraId) {
        List<Object[]> programmiPunti = tesseraService.findProgrammiPuntiByTesseraId(tesseraId);

        if (programmiPunti != null && !programmiPunti.isEmpty()) {
            return new ResponseEntity<>(programmiPunti, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Aggiunge punti a un programma punti in base a un acquisto.
     *
     * @param acquistoDTO I dati dell'acquisto per aggiungere punti.
     * @return Una ResponseEntity contenente un messaggio di successo o uno stato di errore se l'operazione non è riuscita.
     */
    @PostMapping("/aggiungi-punti")
    public ResponseEntity<String> aggiungiPuntiALProgrammaPunti(@RequestBody AcquistoDTO acquistoDTO) {
        try {
            // Qui puoi chiamare il servizio per elaborare l'acquisto utilizzando i dati in acquistoDTO
            tesseraService.aggiungiPuntiALProgrammaPunti(acquistoDTO);
            return ResponseEntity.ok("Acquisto effettuato con successo.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tessera o azienda non trovata.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'elaborazione dell'acquisto.");
        }
    }

    /**
     * Restituisce lo storico dei punti associati a una tessera.
     *
     * @param tesseraId L'ID della tessera.
     * @return Una ResponseEntity contenente una lista di storico punti o uno stato di not found se la lista è vuota.
     */
    @GetMapping("/storico-punti/{tesseraId}")
    public ResponseEntity<List<Object[]>> getStoricoPuntiByTesseraId(@PathVariable Long tesseraId) {
        List<StoricoPunti> storicoPunti = storicoPuntiService.getStoricoPuntiByTesseraId(tesseraId);
        if (storicoPunti.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }

    /**
     * Restituisce la lista di premi associati a un'azienda.
     *
     * @param aziendaId L'ID dell'azienda.
     * @return Una ResponseEntity contenente una lista di premi o uno stato di not found se la lista è vuota.
     */
    @GetMapping("/{aziendaId}/premi")
    public ResponseEntity<List<PremioDTO>> getPremiAzienda(@PathVariable Long aziendaId) {
        List<Premio> premi = aziendaService.getPremiAzienda(aziendaId);

        if (premi != null && !premi.isEmpty()) {
            // Mappa i premi in PremioDTO
            List<PremioDTO> premiDTO = premi.stream()
                    .map(this::mapToPremioDTO) // Metodo per la mappatura
                    .collect(Collectors.toList());

            return new ResponseEntity<>(premiDTO, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    private PremioDTO mapToPremioDTO(Premio premio) {
        PremioDTO premioDTO = new PremioDTO();
        premioDTO.setNome(premio.getNome());
        premioDTO.setDescrizione(premio.getDescrizione());
        premioDTO.setPuntiRichiesti(premio.getPuntiDelPremio());
        return premioDTO;
    }

    /**
     * Riscatta un premio per una tessera.
     *
     * @param tesseraId L'ID della tessera.
     * @param premioDTO I dati del premio da riscattare.
     * @return Una ResponseEntity contenente un messaggio di successo o uno stato di errore se l'operazione non è riuscita.
     */
    @PostMapping("/{tesseraId}/riscatta-premio")
    public ResponseEntity<String> riscattaPremio(@PathVariable Long tesseraId, @RequestBody PremioDTO premioDTO) {
        return tesseraService.riscattaPremio(tesseraId, premioDTO);
    }

    /**
     * Aggiorna le informazioni di un cliente esistente.
     *
     * @param id       L'ID del cliente da aggiornare.
     * @param cliente  I nuovi dati del cliente.
     * @return Una ResponseEntity contenente il cliente aggiornato o uno stato di not found se il cliente non esiste.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente updatedCliente = clienteService.updateCliente(id, cliente);
        if (updatedCliente != null) {
            return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Cancella un cliente dal sistema.
     *
     * @param id L'ID del cliente da cancellare.
     * @return Una ResponseEntity senza contenuto (NO_CONTENT) o uno stato di not found se il cliente non esiste.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
