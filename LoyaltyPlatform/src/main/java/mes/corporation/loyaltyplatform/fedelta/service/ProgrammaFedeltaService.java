package mes.corporation.loyaltyplatform.fedelta.service;

import jakarta.persistence.EntityNotFoundException;
import mes.corporation.loyaltyplatform.fedelta.model.ProgrammaFedelta;
import mes.corporation.loyaltyplatform.fedelta.repository.ProgrammaFedeltaRepository;
import mes.corporation.loyaltyplatform.utenti.model.Cliente;
import mes.corporation.loyaltyplatform.utenti.repo.AziendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Questa classe rappresenta un servizio per la gestione dei programmi Fedelta.
 * Tuttavia, al momento, non ha implementazioni specifiche.
 */
@Service
public class ProgrammaFedeltaService {

    @Autowired
    private ProgrammaFedeltaRepository programmaFedeltaRepository;

    @Autowired
    private AziendaRepository aziendaRepository;

    public void registraCliente(Cliente cliente) {
        // Implementa la logica per la registrazione del cliente nel programma Fedelta, se necessario.
    }


    public int calcolaPunti(BigDecimal importoSpeso) {
        int punti = importoSpeso.divide(BigDecimal.valueOf(10)).intValue();

        return punti;
    }



    /**
     * Ottiene il programma Fedelta associato a un'azienda tramite l'ID dell'azienda.
     *
     * @param aziendaId L'ID dell'azienda per cui ottenere il programma Fedelta.
     * @return Il programma Fedelta associato all'azienda, se presente.
     * @throws EntityNotFoundException Se il programma Fedelta non è stato trovato per l'ID azienda specificato.
     */
    public ProgrammaFedelta getProgrammaFedeltaByAziendaId(Long aziendaId) {
        return programmaFedeltaRepository.findByAziendaId(aziendaId)
                .orElseThrow(() -> new EntityNotFoundException("Programma Fedelta non trovato per l'ID Azienda: " + aziendaId));
    }
}
