package mes.corporation.loyaltyplatform.utenti.repo;


import mes.corporation.loyaltyplatform.utenti.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>, UtenteRepository<Cliente> {
   Optional<Cliente> findByEmail(String email);
}


