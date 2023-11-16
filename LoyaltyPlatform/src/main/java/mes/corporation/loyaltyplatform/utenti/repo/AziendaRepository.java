package mes.corporation.loyaltyplatform.utenti.repo;

import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository per la gestione delle operazioni sui dati dell'azienda.
 */
@Repository
public interface AziendaRepository extends UtenteRepository<Azienda> {

  /**
   * Trova un'azienda in base all'indirizzo email.
   *
   * @param email L'indirizzo email dell'azienda da cercare.
   * @return Un'istanza di Optional contenente l'azienda trovata (se presente).
   */
  Optional<Azienda> findByEmail(String email);

  /**
   * Trova un'azienda in base alla partita IVA.
   *
   * @param partitaIva La partita IVA dell'azienda da cercare.
   * @return L'azienda trovata in base alla partita IVA.
   */
  Azienda findByDatiPersonali_PartitaIva(String partitaIva);
}
