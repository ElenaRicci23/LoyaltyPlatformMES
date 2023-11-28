package mes.corporation.loyaltyplatform.tessera;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TesseraRepository extends JpaRepository<Tessera, Long> {

    @Query("SELECT pf.id, pf.nome, a.id, a.nome " +
            "FROM Tessera  t " +
            "JOIN t.programmiFedelta tpf ON t.id = tpf.id " +
            "JOIN ProgrammaFedelta pf ON tpf.id = pf.id " +
            "JOIN Azienda a ON pf.id = a.id " +
            "WHERE t.id = :tesseraId " +
            "AND pf.tipoProgrammaFedelta= 'PUNTI'")
    List<Object[]> findProgrammiPuntiByTesseraId(@Param("tesseraId") Long tesseraId);



}

