package mes.corporation.loyaltyplatform.utenti.controller;

import mes.corporation.loyaltyplatform.fedelta.TipoProgrammaFedelta;
import mes.corporation.loyaltyplatform.utenti.DTO.AziendaDTO;
import mes.corporation.loyaltyplatform.utenti.DTO.DatiPersonaliAziendaDTO;
import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.model.DatiPersonaliAzienda;
import mes.corporation.loyaltyplatform.utenti.service.AziendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/azienda")
public class AziendaController {
    @Autowired
    private AziendaService aziendaService;
    @PostMapping("/registrazione")
    public ResponseEntity<String> registrazioneAzienda(@RequestBody AziendaDTO aziendaDTO) {


        if (aziendaService.isEmailAlreadyRegistered(aziendaDTO.getEmail())) {
            return ResponseEntity.badRequest().body("L'email è già registrata.");
        }

        DatiPersonaliAzienda datiPersonali = new DatiPersonaliAzienda();
        datiPersonali.setNome(aziendaDTO.getDatiPersonali().getNome());
        datiPersonali.setPartitaIva(aziendaDTO.getDatiPersonali().getPartitaIva());
        datiPersonali.setCodiceUnivoco(aziendaDTO.getDatiPersonali().getCodiceUnivoco());
        datiPersonali.setRagioneSociale(aziendaDTO.getDatiPersonali().getRagioneSociale());
        datiPersonali.setSettore(aziendaDTO.getDatiPersonali().getSettore());
        datiPersonali.setIndirizzo(aziendaDTO.getDatiPersonali().getIndirizzo());
        datiPersonali.setNumeroStabilimenti(aziendaDTO.getDatiPersonali().getNumeroStabilimenti());

        DatiPersonaliAziendaDTO datiPersonaliDTO = aziendaDTO.getDatiPersonali();
        if (aziendaService.isPartitaIvaAlreadyRegistered(datiPersonaliDTO.getPartitaIva())) {
            return ResponseEntity.badRequest().body("La partita IVA è già registrata.");
        }

        Azienda azienda = new Azienda();
        azienda.setEmail(aziendaDTO.getEmail());
        azienda.setPassword(aziendaDTO.getPassword());

        azienda.setDatiPersonali(datiPersonali);

        // Registra l'azienda nel database utilizzando il servizio
        aziendaService.registrazione(azienda);

        return ResponseEntity.ok("Azienda registrata con successo.");
    }

    @GetMapping("/azienda/{partitaIva}")
    public ResponseEntity<Azienda> getAziendaByPartitaIva(@PathVariable String partitaIva) {
        Azienda azienda = aziendaService.findAziendaByPartitaIva(partitaIva);
        if (azienda != null) {
            return ResponseEntity.ok(azienda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/{aziendaId}/configura-programma-fedelta")
    public ResponseEntity<String> configuraProgrammaFedelta(@PathVariable Long aziendaId, @RequestBody TipoProgrammaFedelta tipoProgrammaFedelta) {
        Azienda azienda = aziendaService.getAziendaById(aziendaId);
        if (azienda != null) {
            azienda.setTipoProgrammaFedelta(tipoProgrammaFedelta);
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
