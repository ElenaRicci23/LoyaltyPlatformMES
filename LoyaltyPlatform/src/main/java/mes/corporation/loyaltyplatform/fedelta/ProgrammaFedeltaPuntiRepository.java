package mes.corporation.loyaltyplatform.fedelta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgrammaFedeltaPuntiRepository extends JpaRepository<ProgrammaFedeltaPunti, Long> {
    Optional<ProgrammaFedeltaPunti> findByAziendaId(Long aziendaId);
}
