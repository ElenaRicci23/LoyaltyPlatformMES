package mes.corporation.loyaltyplatform.fedelta.repository;

import mes.corporation.loyaltyplatform.fedelta.model.ProgrammaFedelta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Repository per la gestione del programma Fedelta punti.
 */
public interface ProgrammaFedeltaRepository extends JpaRepository<ProgrammaFedelta, Long> {

    /**
     * Trova il programma Fedelta punti associato a un'azienda tramite l'ID dell'azienda.
     *
     * @param aziendaId L'ID dell'azienda per cui cercare il programma Fedelta punti.
     * @return Un'istanza di Optional contenente il programma Fedelta punti se trovato, altrimenti Optional vuoto.
     */
    Optional<ProgrammaFedelta> findByAziendaId(Long aziendaId);
}
