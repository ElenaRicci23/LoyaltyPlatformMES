package mes.corporation.loyaltyplatform.repo;

import mes.corporation.loyaltyplatform.factory.ProgrammaPunti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammaPuntiRepository extends JpaRepository<ProgrammaPunti, Long> {

    ProgrammaPunti findByAziendaId(Long aziendaId);
}
