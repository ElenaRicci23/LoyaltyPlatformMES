package com.example.loyaltyPlatformSicuro.utenti.azienda;

import com.example.loyaltyPlatformSicuro.utenti.UtenteRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AziendaRepository extends UtenteRepository<Azienda> {
    Optional<Azienda> findByEmail(String email);
    Azienda findByDatiPersonali_PartitaIva(String partitaIva);

    @EntityGraph(attributePaths = "datiPersonali")
    Azienda findAziendaById(Long id);

    Azienda findByDatiPersonali_Nome(String nomeAzienda);
}

