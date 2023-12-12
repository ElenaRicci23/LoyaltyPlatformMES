package com.example.loyaltyPlatformSicuro.utenti.transazione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransazioneRepository extends JpaRepository<Transazione, Long> {
    List<Transazione> findTransazioniByClienteIdOrderByDataTransazioneDesc(Long clienteId);
}

