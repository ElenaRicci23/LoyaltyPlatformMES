package com.example.loyaltyPlatformSicuro.utenti.repository;

import com.example.loyaltyPlatformSicuro.utenti.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interfaccia base per i repository degli utenti.
 *
 * @param <T> Il tipo di entità utente gestito dal repository.
 */
public interface UtenteRepository<T extends Utente> extends JpaRepository<T, Long> {

    /**
     * Trova un utente in base all'indirizzo email.
     *
     * @param email L'indirizzo email dell'utente da cercare.
     * @return Un'istanza di Optional contenente l'utente trovato (se presente).
     */
    Optional<T> findByEmail(String email);
}
