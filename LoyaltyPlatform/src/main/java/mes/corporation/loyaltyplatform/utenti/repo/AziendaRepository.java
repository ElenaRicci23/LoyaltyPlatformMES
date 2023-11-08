package mes.corporation.loyaltyplatform.utenti.repo;

import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import org.springframework.stereotype.Repository;

@Repository
public interface AziendaRepository extends UtenteRepository<Azienda> {
  Azienda findByEmail(String email);

  Azienda findByDatiPersonali_PartitaIva(String partitaIva);
}
