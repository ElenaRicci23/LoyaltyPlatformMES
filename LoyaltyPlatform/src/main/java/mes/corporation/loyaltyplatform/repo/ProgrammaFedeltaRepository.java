package mes.corporation.loyaltyplatform.repo;

import mes.corporation.loyaltyplatform.model.ProgrammaFedelta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammaFedeltaRepository extends JpaRepository<ProgrammaFedelta, Long> {

    ProgrammaFedelta findByNome(String nome);

    ProgrammaFedelta findByAziendaId(Long aziendaId);
}
