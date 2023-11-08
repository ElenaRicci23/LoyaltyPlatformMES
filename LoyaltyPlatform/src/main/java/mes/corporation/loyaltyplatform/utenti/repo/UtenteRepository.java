package mes.corporation.loyaltyplatform.utenti.repo;

import mes.corporation.loyaltyplatform.utenti.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UtenteRepository<T extends Utente> extends JpaRepository<T, Long> {
    T findByEmail(String email);
}






