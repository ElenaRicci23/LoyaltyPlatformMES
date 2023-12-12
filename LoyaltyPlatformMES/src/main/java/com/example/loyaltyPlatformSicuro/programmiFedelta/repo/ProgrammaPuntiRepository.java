package com.example.loyaltyPlatformSicuro.programmiFedelta.repo;

import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.ProgrammaPunti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammaPuntiRepository extends JpaRepository<ProgrammaPunti, Long> {

    ProgrammaPunti findByAziendaId(Long aziendaId);
}
