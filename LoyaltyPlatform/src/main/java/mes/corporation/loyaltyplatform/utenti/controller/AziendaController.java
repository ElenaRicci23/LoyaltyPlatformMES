package mes.corporation.loyaltyplatform.utenti.controller;

import mes.corporation.loyaltyplatform.fedelta.model.TipoProgrammaFedelta;
import mes.corporation.loyaltyplatform.utenti.DTO.AziendaDTO;
import mes.corporation.loyaltyplatform.utenti.DTO.DatiPersonaliAziendaDTO;
import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.model.DatiPersonaliAzienda;
import mes.corporation.loyaltyplatform.utenti.service.AziendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Questa classe è responsabile di gestire le richieste relative alle aziende utenti.
 */
@RestController
@RequestMapping("/api/azienda")
public class AziendaController {
    @Autowired
    private AziendaService aziendaService;

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

    /**
     * Gestisce una richiesta POST per configurare il programma fedeltà per un'azienda.
     *
     * @param aziendaId           L'ID dell'azienda per cui configurare il programma fedeltà.
     * @param tipoProgrammaFedelta Il tipo di programma fedeltà da configurare.
     * @return Una ResponseEntity con un messaggio di successo o di errore.
     */
    @PostMapping("/{aziendaId}/configura-programma-fedelta")
    public ResponseEntity<String> configuraProgrammaFedelta(@PathVariable Long aziendaId, @RequestBody TipoProgrammaFedelta tipoProgrammaFedelta) {
        // Ottiene l'azienda in base all'ID
        Azienda azienda = aziendaService.getAziendaById(aziendaId);

        if (azienda != null) {
            // Imposta il tipo di programma fedeltà
            azienda.setTipoProgrammaFedelta(tipoProgrammaFedelta);

            // Salva l'azienda nel database utilizzando il servizio
            aziendaService.saveAzienda(azienda);

            return ResponseEntity.ok("Programma fedeltà configurato con successo per l'azienda.");
        } else {
            return ResponseEntity.notFound().build();
        }
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
