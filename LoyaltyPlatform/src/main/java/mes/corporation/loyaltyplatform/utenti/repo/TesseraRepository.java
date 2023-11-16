package mes.corporation.loyaltyplatform.utenti.repo;

import mes.corporation.loyaltyplatform.utenti.model.Tessera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository per la gestione delle operazioni sulle tessere dei clienti.
 */
@Repository
public interface TesseraRepository extends JpaRepository<Tessera, Long> {

    /**
     * Trova una tessera in base all'ID del proprietario (cliente).
     *
     * @param clienteId L'ID del cliente proprietario della tessera.
     * @return Un'istanza di Optional contenente la tessera trovata (se presente).
     */
    Optional<Tessera> findByProprietario_Id(Long clienteId);
}
