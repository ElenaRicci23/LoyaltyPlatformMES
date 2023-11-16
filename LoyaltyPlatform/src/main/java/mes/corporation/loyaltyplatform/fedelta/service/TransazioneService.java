package mes.corporation.loyaltyplatform.fedelta.service;

import mes.corporation.loyaltyplatform.fedelta.model.ProgrammaFedelta;
import mes.corporation.loyaltyplatform.utenti.repo.TesseraRepository;
import mes.corporation.loyaltyplatform.utenti.service.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * Questa classe gestisce le transazioni e l'aggiunta di punti fedeltà ai clienti dopo la conferma del pagamento.
 */

@Service
public class TransazioneService {




    @Autowired
    private TesseraRepository tesseraRepository;
    @Autowired
    private TesseraService tesseraService;
    @Autowired
    private ProgrammaFedeltaService programmaFedeltaService;

    /**
     * Processa una transazione effettuando la conferma del pagamento e l'aggiunta di punti fedeltà al cliente.
     *
     * @param importoSpeso L'importo speso nella transazione.
     * @param clienteId    L'ID del cliente coinvolto nella transazione.
     * @param aziendaId    L'ID dell'azienda coinvolta nella transazione.
     * @throws Exception In caso di errore durante la gestione della transazione.
     */

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

    /**
     * Simula la conferma del pagamento.
     *
     * @return true se il pagamento è stato confermato con successo, altrimenti false.
     */

    private boolean confermaPagamento() {
        // Implementa la logica per confermare il pagamento
        return true; // Ritorna true per simulare un pagamento confermato
    }
}



