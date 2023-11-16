package mes.corporation.loyaltyplatform.fedelta.service;

import jakarta.persistence.EntityNotFoundException;
import mes.corporation.loyaltyplatform.fedelta.model.ProgrammaFedelta;
import mes.corporation.loyaltyplatform.fedelta.model.ProgrammaFedeltaAzienda;
import mes.corporation.loyaltyplatform.fedelta.model.ProgrammaFedeltaFactory;
import mes.corporation.loyaltyplatform.fedelta.repository.ProgrammaFedeltaAziendaRepository;
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
    private ProgrammaFedeltaAziendaRepository programmaFedeltaAziendaRepository;

    @Autowired
    private AziendaRepository aziendaRepository;

    @Autowired
    private ProgrammaFedeltaFactory programmaFedeltaFactory; // Assicurati che il servizio di factory sia iniettato correttamente

    @Override
    public void registraCliente(Cliente cliente) {
        // Implementa la logica per la registrazione del cliente nel programma fedeltà, se necessario.
    }


    public int calcolaPunti(BigDecimal importoSpeso) {
        int punti = importoSpeso.divide(BigDecimal.valueOf(10)).intValue();

        return punti;
    }



    /**
     * Ottiene il programma fedeltà associato a un'azienda tramite l'ID dell'azienda.
     *
     * @param aziendaId L'ID dell'azienda per cui ottenere il programma fedeltà.
     * @return Il programma fedeltà associato all'azienda, se presente.
     * @throws EntityNotFoundException Se il programma fedeltà non è stato trovato per l'ID azienda specificato.
     */
    public ProgrammaFedelta getProgrammaFedeltaByAziendaId(Long aziendaId) {
        return programmaFedeltaAziendaRepository.findByAziendaId(aziendaId)
                .orElseThrow(() -> new EntityNotFoundException("Programma Fedeltà non trovato per l'ID Azienda: " + aziendaId));
    }
}
