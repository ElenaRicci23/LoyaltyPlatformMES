package com.example.loyaltyPlatformSicuro.utenti.cliente.tessera;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesseraRepository extends JpaRepository<Tessera, Long> {
    // Puoi aggiungere metodi personalizzati per le query se necessario
}
