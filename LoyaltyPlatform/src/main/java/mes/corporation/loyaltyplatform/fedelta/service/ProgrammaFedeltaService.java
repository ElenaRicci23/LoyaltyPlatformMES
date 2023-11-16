package mes.corporation.loyaltyplatform.fedelta.service;

import jakarta.persistence.EntityNotFoundException;
import mes.corporation.loyaltyplatform.fedelta.model.ProgrammaFedelta;
import mes.corporation.loyaltyplatform.fedelta.model.ProgrammaFedeltaFactory;
import mes.corporation.loyaltyplatform.fedelta.repository.ProgrammaFedeltaPuntiRepository;
import mes.corporation.loyaltyplatform.utenti.model.Cliente;
import mes.corporation.loyaltyplatform.utenti.repo.AziendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;



/**
 * Questa classe rappresenta un servizio per la gestione dei programmi fedeltà.
 * Tuttavia, al momento, non ha implementazioni specifiche.
 */
@Service
public class ProgrammaFedeltaService implements ProgrammaFedelta {

    @Autowired
    private ProgrammaFedeltaPuntiRepository programmaFedeltaPuntiRepository;

    @Autowired
    private AziendaRepository aziendaRepository;

    @Autowired
    private ProgrammaFedeltaFactory programmaFedeltaFactory; // Assicurati che il servizio di factory sia iniettato correttamente

    @Override
    public void registraCliente(Cliente cliente) {
        // Implementa la logica per la registrazione del cliente nel programma fedeltà, se necessario.
    }

    @Override
    public int calcolaPunti(BigDecimal importoSpeso) {
        // Implementa la logica per il calcolo dei punti in base all'importo speso, se necessario.
        return 0; // Esempio: restituisci un valore fisso di 0, ma dovresti calcolare i punti in modo reale.
    }

    /**
     * Ottiene il programma fedeltà associato a un'azienda tramite l'ID dell'azienda.
     *
     * @param aziendaId L'ID dell'azienda per cui ottenere il programma fedeltà.
     * @return Il programma fedeltà associato all'azienda, se presente.
     * @throws EntityNotFoundException Se il programma fedeltà non è stato trovato per l'ID azienda specificato.
     */
    public ProgrammaFedelta getProgrammaFedeltaByAziendaId(Long aziendaId) {
        return programmaFedeltaPuntiRepository.findByAziendaId(aziendaId)
                .orElseThrow(() -> new EntityNotFoundException("Programma Fedeltà non trovato per l'ID Azienda: " + aziendaId));
    }
}
