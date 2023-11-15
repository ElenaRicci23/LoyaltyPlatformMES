package mes.corporation.loyaltyplatform.utenti.repo;

import mes.corporation.loyaltyplatform.utenti.model.Tessera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TesseraRepository extends JpaRepository<Tessera, Long> {

    Optional<Tessera> findByProprietario_Id(Long clienteId);

}
