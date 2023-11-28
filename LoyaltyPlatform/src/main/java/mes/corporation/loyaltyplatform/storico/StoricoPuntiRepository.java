package mes.corporation.loyaltyplatform.storico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoricoPuntiRepository extends JpaRepository<StoricoPunti, Long> {

    @Query("SELECT sp FROM StoricoPunti sp WHERE sp.tessera.id = :tesseraId")
    List<StoricoPunti> findStoricoPuntiByTesseraId(@Param("tesseraId") Long tesseraId);
}

