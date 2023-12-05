package com.example.loyaltyPlatformSicuro.programmiFedelta.repo;

import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.Configurazione.Premio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremioRepository extends JpaRepository<Premio, Long> {
    // Puoi aggiungere eventuali metodi personalizzati per l'accesso ai premi qui, se necessario
}
