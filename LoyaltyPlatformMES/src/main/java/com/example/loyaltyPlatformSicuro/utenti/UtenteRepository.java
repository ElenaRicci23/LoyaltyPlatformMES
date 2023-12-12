package com.example.loyaltyPlatformSicuro.utenti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository<T extends Utente> extends JpaRepository<T, Long> {
    Optional<T> findByEmail(String email);
    Optional<T> findAziendaByEmail(String email); // Modificato il nome del metodo
}

