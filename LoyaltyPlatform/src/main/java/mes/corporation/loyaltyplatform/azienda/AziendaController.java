package mes.corporation.loyaltyplatform.azienda;

import mes.corporation.loyaltyplatform.DTO.AziendaDTO;
import mes.corporation.loyaltyplatform.DTO.PremioDTO;
import mes.corporation.loyaltyplatform.DTO.ProgrammaFedeltaDTO;
import mes.corporation.loyaltyplatform.DTO.ProgrammaPuntiDTO;
import mes.corporation.loyaltyplatform.factory.ProgrammaPunti;
import mes.corporation.loyaltyplatform.model.Premio;
import mes.corporation.loyaltyplatform.model.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mes.corporation.loyaltyplatform.DTO.AziendaDTO.convertToDTO;
import static mes.corporation.loyaltyplatform.DTO.AziendaDTO.convertToEntity;

/**
 * Questa classe rappresenta il controller per le operazioni relative alle entità Azienda.
 */
@RestController
@RequestMapping("/api/aziende")
public class AziendaController {

    private final AziendaService aziendaService;

    @Autowired
    public AziendaController(AziendaService aziendaService) {
        this.aziendaService = aziendaService;
    }

    /**
     * Crea una nuova azienda.
     *
     * @param aziendaDTO Il DTO contenente i dettagli dell'azienda da creare.
     * @return Una risposta con l'azienda creata e lo stato HTTP 201 (CREATED).
     */
    @PostMapping("/aggiungi")
    public ResponseEntity<AziendaDTO> createAzienda(@RequestBody AziendaDTO aziendaDTO) {
        Azienda azienda = convertToEntity(aziendaDTO);
        Azienda savedAzienda = aziendaService.saveAzienda(azienda);
        AziendaDTO savedAziendaDTO = convertToDTO(savedAzienda);
        return new ResponseEntity<>(savedAziendaDTO, HttpStatus.CREATED);
    }

    /**
     * Ottiene una lista di tutte le aziende.
     *
     * @return Una risposta con una lista di aziende e lo stato HTTP 200 (OK).
     */
    @GetMapping("/")
    public ResponseEntity<List<Azienda>> getAllAziende() {
        return ResponseEntity.ok(aziendaService.findAllAziende());
    }

    /**
     * Ottiene un'azienda tramite il suo ID.
     *
     * @param id L'ID dell'azienda da cercare.
     * @return Una risposta con l'azienda trovata o uno stato HTTP 404 (NOT FOUND) se non è stata trovata.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Azienda> getAziendaById(@PathVariable Long id) {
        return aziendaService.findAziendaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Aggiunge un Programma Fedeltà a un'azienda.
     *
     * @param aziendaId         L'ID dell'azienda a cui aggiungere il programma fedeltà.
     * @param programmaFedeltaDTO Il DTO contenente i dettagli del programma fedeltà da aggiungere.
     * @return Una risposta con uno stato HTTP 201 (CREATED) se l'operazione ha successo,
     *         o uno stato HTTP 404 (NOT FOUND) se l'azienda non è stata trovata.
     */
    @PostMapping("/{aziendaId}/programmi_fedelta")
    public ResponseEntity<ProgrammaFedelta> addProgrammaFedelta(@PathVariable Long aziendaId, @RequestBody ProgrammaFedeltaDTO programmaFedeltaDTO) {
        Azienda azienda = aziendaService.addProgrammaFedelta(aziendaId, programmaFedeltaDTO);
        if (azienda != null) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Configura un Programma Punti per un'azienda.
     *
     * @param aziendaId         L'ID dell'azienda a cui configurare il programma punti.
     * @param programmaPuntiDTO Il DTO contenente i dettagli del programma punti da configurare.
     * @return Una risposta con uno stato HTTP 201 (CREATED) se l'operazione ha successo,
     *         o uno stato HTTP 404 (NOT FOUND) se l'azienda non è stata trovata.
     */
    @PutMapping("/{aziendaId}/configura-programma-punti")
    public ResponseEntity<ProgrammaPunti> configuraProgrammaPunti(@PathVariable Long aziendaId, @RequestBody ProgrammaPuntiDTO programmaPuntiDTO) {
        Azienda azienda = aziendaService.configuraProgrammaPunti(aziendaId, programmaPuntiDTO);
        if (azienda != null) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Aggiunge un nuovo premio a un Programma Punti di un'azienda.
     *
     * @param aziendaId        L'ID dell'azienda a cui aggiungere il premio.
     * @param programmaPuntiId L'ID del Programma Punti a cui aggiungere il premio.
     * @param premioDTO        Il DTO contenente i dettagli del premio da aggiungere.
     * @return Una risposta con uno stato HTTP 201 (CREATED) se l'operazione ha successo,
     *         o uno stato HTTP 400 (BAD REQUEST) se l'operazione non riesce.
     */
    @PostMapping("/{aziendaId}/programmi-punti/{programmaPuntiId}/aggiungi-premio")
    public ResponseEntity<Premio> aggiungiPremio(
            @PathVariable Long aziendaId,
            @PathVariable Long programmaPuntiId,
            @RequestBody PremioDTO premioDTO) {
        Azienda premio = aziendaService.aggiungiPremio(aziendaId, programmaPuntiId, premioDTO);
        if (premio != null) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            // Gestire il caso in cui l'aggiunta del premio non riesce
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Modifica un premio di un Programma Punti di un'azienda.
     *
     * @param aziendaId        L'ID dell'azienda a cui appartiene il premio.
     * @param programmaPuntiId L'ID del Programma Punti a cui appartiene il premio.
     * @param premioId         L'ID del premio da modificare.
     * @param premioDTO        Il DTO contenente i dettagli del premio da modificare.
     * @return Una risposta con l'azienda aggiornata se l'operazione ha successo,
     *         o uno stato HTTP 400 (BAD REQUEST) se l'operazione non riesce.
     */
    @PutMapping("/{aziendaId}/programmi-punti/{programmaPuntiId}/modifica-premio/{premioId}")
    public ResponseEntity<Azienda> modificaPremio(
            @PathVariable Long aziendaId,
            @PathVariable Long programmaPuntiId,
            @PathVariable Long premioId,
            @RequestBody PremioDTO premioDTO) {
        Azienda azienda = aziendaService.modificaPremio(aziendaId, programmaPuntiId, premioId, premioDTO);
        if (azienda != null) {
            return ResponseEntity.ok(azienda);
        } else {
            // Gestire il caso in cui la modifica del premio non riesce
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Rimuove un premio da un Programma Punti di un'azienda.
     *
     * @param aziendaId        L'ID dell'azienda a cui appartiene il premio.
     * @param programmaPuntiId L'ID del Programma Punti a cui appartiene il premio.
     * @param premioId         L'ID del premio da rimuovere.
     * @param premioDTO        Il DTO contenente i dettagli del premio da rimuovere.
     * @return Una risposta con l'azienda aggiornata se l'operazione ha successo,
     *         o uno stato HTTP 400 (BAD REQUEST) se l'operazione non riesce.
     */
    @DeleteMapping("/{aziendaId}/programmi-punti/{programmaPuntiId}/rimuovi-premio/{premioId}")
    public ResponseEntity<Azienda> rimuoviPremio(
            @PathVariable Long aziendaId,
            @PathVariable Long programmaPuntiId,
            @PathVariable Long premioId,
            @RequestBody PremioDTO premioDTO) {
        Azienda azienda = aziendaService.rimuoviPremio(aziendaId, programmaPuntiId, premioId, premioDTO);
        if (azienda != null) {
            return ResponseEntity.ok(azienda);
        } else {
            // Gestire il caso in cui la rimozione del premio non riesce
            return ResponseEntity.badRequest().build();
        }
    }
}
