package mes.corporation.loyaltyplatform.utenti.repo;


import mes.corporation.loyaltyplatform.utenti.model.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends UtenteRepository<Cliente> {
   Cliente findByEmail(String email);
}


