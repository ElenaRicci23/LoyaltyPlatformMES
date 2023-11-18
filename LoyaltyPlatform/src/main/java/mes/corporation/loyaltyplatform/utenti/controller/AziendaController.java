package mes.corporation.loyaltyplatform.utenti.controller;

import mes.corporation.loyaltyplatform.fedelta.model.ProgrammaFedelta;
import mes.corporation.loyaltyplatform.fedelta.model.TipoProgrammaFedelta;
import mes.corporation.loyaltyplatform.fedelta.repository.ProgrammaFedeltaRepository;
import mes.corporation.loyaltyplatform.utenti.DTO.AziendaDTO;
import mes.corporation.loyaltyplatform.utenti.DTO.DatiPersonaliAziendaDTO;
import mes.corporation.loyaltyplatform.utenti.DTO.ProgrammaFedeltaDTO;
import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.model.DatiPersonaliAzienda;
import mes.corporation.loyaltyplatform.utenti.repo.AziendaRepository;
import mes.corporation.loyaltyplatform.utenti.service.AziendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Questa classe è responsabile di gestire le richieste relative alle aziende utenti.
 */
@RestController
@RequestMapping("/api/azienda")
public class AziendaController {
    @Autowired
    private AziendaService aziendaService;
    @Autowired
    private AziendaRepository aziendaRepository;
    @Autowired
    private ProgrammaFedeltaRepository programmaFedeltaRepository;

    /**
     * Gestisce una richiesta POST per la registrazione di un'azienda.
     *
     * @param aziendaDTO I dati dell'azienda da registrare.
     * @return Una ResponseEntity con un messaggio di successo o di errore.
     */
    @PostMapping("/registrazione")
    public ResponseEntity<String> registrazioneAzienda(@RequestBody AziendaDTO aziendaDTO) {
        // Verifica se l'email è già registrata
        if (aziendaService.isEmailAlreadyRegistered(aziendaDTO.getEmail())) {
            return ResponseEntity.badRequest().body("L'email è già registrata.");
        }

        // Crea e popola l'oggetto datiPersonali
        DatiPersonaliAzienda datiPersonali = new DatiPersonaliAzienda();
        DatiPersonaliAziendaDTO datiPersonaliDTO = aziendaDTO.getDatiPersonali();
        datiPersonali.setNome(datiPersonaliDTO.getNome());
        datiPersonali.setPartitaIva(datiPersonaliDTO.getPartitaIva());
        datiPersonali.setCodiceUnivoco(datiPersonaliDTO.getCodiceUnivoco());
        datiPersonali.setRagioneSociale(datiPersonaliDTO.getRagioneSociale());
        datiPersonali.setSettore(datiPersonaliDTO.getSettore());
        datiPersonali.setIndirizzo(datiPersonaliDTO.getIndirizzo());
        datiPersonali.setNumeroStabilimenti(datiPersonaliDTO.getNumeroStabilimenti());

        // Verifica se la partita IVA è già registrata
        if (aziendaService.isPartitaIvaAlreadyRegistered(datiPersonaliDTO.getPartitaIva())) {
            return ResponseEntity.badRequest().body("La partita IVA è già registrata.");
        }

        // Crea e popola l'oggetto azienda
        Azienda azienda = new Azienda();
        azienda.setEmail(aziendaDTO.getEmail());
        azienda.setPassword(aziendaDTO.getPassword());
        azienda.setDatiPersonali(datiPersonali);

        // Registra l'azienda nel database utilizzando il servizio
        aziendaService.registrazione(azienda);

        return ResponseEntity.ok("Azienda registrata con successo.");
    }

    /**
     * Gestisce una richiesta GET per ottenere un'azienda in base alla partita IVA.
     *
     * @param partitaIva La partita IVA dell'azienda da cercare.
     * @return Una ResponseEntity con l'azienda trovata o una risposta di errore se non trovata.
     */
    @GetMapping("/azienda/{partitaIva}")
    public ResponseEntity<Azienda> getAziendaByPartitaIva(@PathVariable String partitaIva) {
        // Cerca l'azienda in base alla partita IVA
        Azienda azienda = aziendaService.findAziendaByPartitaIva(partitaIva);

        if (azienda != null) {
            return ResponseEntity.ok(azienda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/{aziendaId}/aggiungiPF")
    public ResponseEntity<String> aggiungiProgrammaFedelta(@PathVariable Long aziendaId, @RequestBody ProgrammaFedeltaDTO programmaFedeltaDTO) {
        Azienda azienda = aziendaService.getAziendaById(aziendaId);

        if (azienda == null) {
            return ResponseEntity.notFound().build();
        }

        TipoProgrammaFedelta tipoProgrammaFedelta = convertStringToTipoProgramma(programmaFedeltaDTO.getTipoProgramma());
        if (tipoProgrammaFedelta == null) {
            return ResponseEntity.badRequest().body("Tipo di programma non valido.");
        }

        ProgrammaFedelta programmaFedelta = aziendaService.creaProgrammaFedelta(aziendaId, programmaFedeltaDTO.getNome(),
                programmaFedeltaDTO.getDescrizione(), tipoProgrammaFedelta);

        if (programmaFedelta == null) {
            return ResponseEntity.badRequest().body("Impossibile creare il programma Fedeltà.");
        }

        programmaFedeltaRepository.save(programmaFedelta);
        return ResponseEntity.ok("Programma Fedeltà aggiunto con successo all'azienda.");
    }

    // Metodo di conversione da stringa a TipoProgrammaFedelta
    private TipoProgrammaFedelta convertStringToTipoProgramma(String tipoProgrammaString) {
        try {
            return TipoProgrammaFedelta.valueOf(tipoProgrammaString);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }



//    {
//        "nome": "Programma Punti",
//            "descrizione": "Programma fedeltà basato su punti",
//            "tipoProgramma": "PUNTI"
//    }


    /**
     * Ottiene la lista dei programmi fedeltà associati a un'azienda.
     *
     * @param aziendaId L'ID dell'azienda di cui si desidera ottenere i programmi fedeltà.
     * @return Una ResponseEntity contenente la lista dei programmi fedeltà o una risposta di errore se non trovata.
     */


    @GetMapping("/{aziendaId}/programmiAzienda")
    public ResponseEntity<List<ProgrammaFedeltaDTO>> getProgrammiFedeltaByAziendaId(@PathVariable Long aziendaId) {
        Azienda azienda = aziendaService.getAziendaById(aziendaId);

        if (azienda == null) {
            // Usa build() al posto di body() per ResponseEntity.notFound() e noContent()
            return ResponseEntity.notFound().build();
        }

        List<ProgrammaFedelta> programmiFedelta = azienda.getProgrammiFedelta();
        if (programmiFedelta.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ProgrammaFedeltaDTO> programmiFedeltaDTOList = programmiFedelta.stream()
                .map(this::convertToProgrammaFedeltaDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(programmiFedeltaDTOList);
    }
    private ProgrammaFedeltaDTO convertToProgrammaFedeltaDTO(ProgrammaFedelta programmaFedelta) {
        ProgrammaFedeltaDTO dto = new ProgrammaFedeltaDTO();
        dto.setId(programmaFedelta.getId());
        dto.setNome(programmaFedelta.getNome());
        dto.setDescrizione(programmaFedelta.getDescrizione());
        dto.setTipoProgramma(programmaFedelta.getTipoProgramma().name());
        return dto;
    }







}

//{
//        "email": "Mes@example.com",
//        "password": "password456",
//        "datiPersonali": {
//        "nome": "Mes Corporation",
//        "partitaIva": "5412346745",
//        "codiceUnivoco": "codiceUnivoco123",
//        "ragioneSociale": "MesCorporation",
//        "settore": "Informatico",
//        "indirizzo": "Via Roma,23",
//        "numeroStabilimenti": 4
//        }
//        }
