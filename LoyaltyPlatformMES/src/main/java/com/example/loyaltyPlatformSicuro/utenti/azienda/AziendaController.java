package com.example.loyaltyPlatformSicuro.utenti.azienda;


import com.example.loyaltyPlatformSicuro.DTO.AziendaDTO;
import com.example.loyaltyPlatformSicuro.DTO.ProgrammaFedeltaDTO;
import com.example.loyaltyPlatformSicuro.DTO.ProgrammaPuntiDTO;
import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.programmiFedelta.factory.GestoreProgrammiFedelta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Questa classe è il controller per le operazioni relative alle aziende nel sistema.
 */

@RestController
@RequestMapping("/api/azienda")
public class AziendaController {

    private final AziendaService aziendaService;
    private final GestoreProgrammiFedelta gestoreProgrammiFedelta;
    private  ModelMapper modelMapper;

    @Autowired
    public AziendaController(AziendaService aziendaService, GestoreProgrammiFedelta gestoreProgrammiFedelta) {
        this.aziendaService = aziendaService;
        this.gestoreProgrammiFedelta = gestoreProgrammiFedelta;
    }
    /**
     * Registra un'azienda nel sistema utilizzando i dati forniti in un oggetto AziendaDTO.
     *
     * @param aziendaDTO I dati dell'azienda da registrare.
     * @return Una ResponseEntity con un messaggio di conferma o di errore.
     */

    @PostMapping(path = "/registrazione", consumes = "application/json")
    public ResponseEntity<?> registrazioneAzienda(@RequestBody AziendaDTO aziendaDTO) {
        try {
            aziendaService.registrazioneConDTO(aziendaDTO);
            return ResponseEntity.ok(Map.of("message", "Registrazione completata con successo!", "redirect", "/benvenuto"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Errore nella registrazione: " + e.getMessage()));
        }
    }

    /**
     * Questo metodo gestisce la visualizzazione del pannello di controllo dell'azienda con l'ID specificato.
     *
     * @param aziendaId L'ID dell'azienda da visualizzare nel pannello di controllo.
     * @return Un oggetto ModelAndView che rappresenta la vista del pannello di controllo dell'azienda o un messaggio di errore se l'azienda non è stata trovata.
     */
    @GetMapping("/{aziendaId}/dashboardAzienda")
    public ModelAndView dashboardAzienda(@PathVariable Long aziendaId) {
        Azienda azienda = aziendaService.getAziendaById(aziendaId);
        ModelAndView modelAndView;

        if (azienda != null) {
            modelAndView = new ModelAndView("azienda/dashbordAzienda");
            modelAndView.addObject("azienda", azienda);
        } else {
            modelAndView = new ModelAndView("errore");
            modelAndView.addObject("messaggio", "Azienda non trovata.");
        }

        return modelAndView;
    }

    /**
     * Questo metodo gestisce l'aggiunta di un programma fedeltà a un'azienda con l'ID specificato.
     *
     * @param aziendaId          L'ID dell'azienda a cui aggiungere il programma fedeltà.
     * @param programmaFedeltaDTO I dati del programma fedeltà da aggiungere.
     * @return Una ResponseEntity con un messaggio di conferma.
     */
    @PostMapping(path = "/{aziendaId}/aggiungi-programma-fedelta", consumes = "application/json")
    public ResponseEntity<?> aggiungiProgrammaFedelta(@PathVariable Long aziendaId, @RequestBody ProgrammaFedeltaDTO programmaFedeltaDTO) {
        aziendaService.aggiungiProgrammaFedeltaAdAzienda(aziendaId, programmaFedeltaDTO);
        return ResponseEntity.ok(Map.of("message", "Programma fedeltà aggiunto con successo."));
    }

    /**
     * Questo metodo gestisce la configurazione di un programma punti per un'azienda specifica.
     *
     * @param aziendaId         L'ID dell'azienda a cui appartiene il programma punti da configurare.
     * @param programmaPuntiId  L'ID del programma punti da configurare.
     * @param programmaPuntiDTO I dati per la configurazione del programma punti.
     * @return Una ResponseEntity con un messaggio di conferma o un messaggio di errore se si verifica un'eccezione.
     */
    @PostMapping(path = "/{aziendaId}/{programmaPuntiId}/configura", consumes = "application/json")
    public ResponseEntity<?> configuraProgrammaPunti(@PathVariable Long aziendaId, @PathVariable Long programmaPuntiId, @RequestBody ProgrammaPuntiDTO programmaPuntiDTO) {
        try {
            aziendaService.configuraProgrammaPunti(programmaPuntiDTO, aziendaId, programmaPuntiId);
            return ResponseEntity.ok(Map.of("message", "Programma punti configurato con successo."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Errore nella configurazione del programma punti: " + e.getMessage()));
        }
    }

    /**
     * Questo metodo restituisce una lista di programmi fedeltà associati a un'azienda specifica.
     *
     * @param aziendaId L'ID dell'azienda per cui recuperare i programmi fedeltà.
     * @return Una ResponseEntity contenente una lista di programmi fedeltà in formato DTO o una risposta "notFound" se l'azienda non è stata trovata.
     */
    @GetMapping(path = "/{aziendaId}/programmi-fedelta")
    public ResponseEntity<List<ProgrammaFedeltaDTO>> getProgrammiFedeltaByAzienda(@PathVariable Long aziendaId) {
        Azienda azienda = aziendaService.findById(aziendaId);

        if (azienda == null) {
            return ResponseEntity.notFound().build();
        }

        List<ProgrammaFedelta> programmiFedelta = gestoreProgrammiFedelta.getProgrammiFedeltaByAzienda(azienda);
        List<ProgrammaFedeltaDTO> programmiFedeltaDTOList = programmiFedelta.stream()
                .map(pf -> modelMapper.map(pf, ProgrammaFedeltaDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(programmiFedeltaDTOList);
    }

    /**
     * Questo metodo restituisce i dati di un'azienda specifica in formato DTO.
     *
     * @param aziendaId L'ID dell'azienda da recuperare.
     * @return Una ResponseEntity contenente i dati dell'azienda in formato DTO o una risposta "notFound" se l'azienda non è stata trovata.
     */
    @GetMapping("/{aziendaId}")
    public ResponseEntity<AziendaDTO> getAziendaById(@PathVariable Long aziendaId) {
        AziendaDTO aziendaDTO = aziendaService.getAziendaDTOById(aziendaId);
        return aziendaDTO != null ? ResponseEntity.ok(aziendaDTO) : ResponseEntity.notFound().build();
    }

    /**
     * Questo metodo restituisce una lista di tutte le aziende in formato DTO.
     *
     * @return Una ResponseEntity contenente una lista di aziende in formato DTO o una risposta "noContent" se non ci sono aziende.
     */
    @GetMapping("/tutte")
    public ResponseEntity<List<AziendaDTO>> getAllAziende() {
        List<Azienda> aziende = aziendaService.getAllAziende();
        List<AziendaDTO> aziendeDTO = aziende.stream()
                .map(azienda -> modelMapper.map(azienda, AziendaDTO.class))
                .collect(Collectors.toList());

        return !aziendeDTO.isEmpty() ? ResponseEntity.ok(aziendeDTO) : ResponseEntity.noContent().build();
    }

    /**
     * Questo metodo gestisce l'eliminazione di un'azienda con l'ID specificato.
     *
     * @param aziendaId L'ID dell'azienda da eliminare.
     * @return Una ResponseEntity con un messaggio di conferma.
     */
    @DeleteMapping("/{aziendaId}")
    public ResponseEntity<?> eliminaAzienda(@PathVariable Long aziendaId) {
        // Implementa la logica per eliminare l'azienda con l'ID specificato
        aziendaService.eliminaAzienda(aziendaId);
        return ResponseEntity.ok(Map.of("message", "Azienda eliminata con successo."));
    }

    /**
     * Questo metodo gestisce l'aggiornamento dei dati di un'azienda con l'ID specificato.
     *
     * @param aziendaId  L'ID dell'azienda da aggiornare.
     * @param aziendaDTO I nuovi dati per l'azienda.
     * @return Una ResponseEntity con un messaggio di conferma.
     */
    @PutMapping("/{aziendaId}")
    public ResponseEntity<?> aggiornaAzienda(@PathVariable Long aziendaId, @RequestBody AziendaDTO aziendaDTO) {
        // Implementa la logica per aggiornare l'azienda con l'ID specificato
        aziendaService.aggiornaAzienda(aziendaId, aziendaDTO);
        return ResponseEntity.ok(Map.of("message", "Azienda aggiornata con successo."));
    }


}
