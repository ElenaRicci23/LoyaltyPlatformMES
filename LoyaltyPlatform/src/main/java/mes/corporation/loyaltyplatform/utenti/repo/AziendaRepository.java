package mes.corporation.loyaltyplatform.utenti.repo;

import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AziendaRepository extends UtenteRepository<Azienda> {
  Optional<Azienda> findByEmail(String email);

  Azienda findByDatiPersonali_PartitaIva(String partitaIva);
}
