package mes.corporation.loyaltyplatform.fedelta.repository;

import mes.corporation.loyaltyplatform.fedelta.model.ProgrammaFedeltaAzienda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Repository per la gestione del programma fedeltà punti.
 */
public interface ProgrammaFedeltaAziendaRepository extends JpaRepository<ProgrammaFedeltaAzienda, Long> {

    /**
     * Trova il programma fedeltà punti associato a un'azienda tramite l'ID dell'azienda.
     *
     * @param aziendaId L'ID dell'azienda per cui cercare il programma fedeltà punti.
     * @return Un'istanza di Optional contenente il programma fedeltà punti se trovato, altrimenti Optional vuoto.
     */
    Optional<ProgrammaFedeltaAzienda> findByAziendaId(Long aziendaId);
}
