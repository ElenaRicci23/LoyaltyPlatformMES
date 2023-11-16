package mes.corporation.loyaltyplatform.utenti.repo;

import mes.corporation.loyaltyplatform.utenti.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository per la gestione delle operazioni sui dati del cliente.
 */
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>, UtenteRepository<Cliente> {

   /**
    * Trova un cliente in base all'indirizzo email.
    *
    * @param email L'indirizzo email del cliente da cercare.
    * @return Un'istanza di Optional contenente il cliente trovato (se presente).
    */
   Optional<Cliente> findByEmail(String email);
}
