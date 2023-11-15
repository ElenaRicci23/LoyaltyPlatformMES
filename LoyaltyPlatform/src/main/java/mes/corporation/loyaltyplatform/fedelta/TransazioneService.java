package mes.corporation.loyaltyplatform.fedelta;

import mes.corporation.loyaltyplatform.utenti.repo.TesseraRepository;
import mes.corporation.loyaltyplatform.utenti.service.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransazioneService {

    @Autowired
    private TesseraRepository tesseraRepository;
    @Autowired
    private TesseraService tesseraService;
    @Autowired
    private ProgrammaFedeltaService programmaFedeltaService;

    public void processaTransazione(BigDecimal importoSpeso, Long clienteId, Long aziendaId) throws Exception {
        // Esegui la conferma del pagamento tramite il servizio di pagamento di terze parti
        boolean pagamentoConfermato = confermaPagamento();

        if (pagamentoConfermato) {
            // Recupera l'istanza corretta di ProgrammaFedelta basata sull'aziendaId
            ProgrammaFedelta programmaFedelta = programmaFedeltaService.getProgrammaFedeltaByAziendaId(aziendaId);

            // Calcola i punti in base all'importo speso
            int punti = programmaFedeltaService.calcolaPunti(importoSpeso);

            // Aggiungi i punti alla tessera del cliente
            tesseraService.aggiungiPuntiATessera(clienteId, punti, aziendaId);
        }
    }

    private boolean confermaPagamento() {
        // Implementa la logica per confermare il pagamento
        return true; // Ritorna true per simulare un pagamento confermato
    }
}


