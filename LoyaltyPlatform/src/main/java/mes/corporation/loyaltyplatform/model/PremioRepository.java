package mes.corporation.loyaltyplatform.model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremioRepository extends JpaRepository<Premio, Long> {
    Premio findPremioByNome(String nome);
}
