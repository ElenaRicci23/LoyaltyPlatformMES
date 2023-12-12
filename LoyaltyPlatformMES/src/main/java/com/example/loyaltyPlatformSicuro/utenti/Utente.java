package com.example.loyaltyPlatformSicuro.utenti;

import jakarta.persistence.*;
/**
 * Classe astratta che rappresenta un utente nel sistema.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Indirizzo email dell'utente.
     */
    protected String email;

    /**
     * Password dell'utente.
     */
    protected String password;

    /**
     * Costruttore vuoto.
     */
    public Utente() {
    }

    /**
     * Costruttore per creare un utente con email e password.
     *
     * @param email    L'indirizzo email dell'utente.
     * @param password La password dell'utente.
     */
    public Utente(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Restituisce l'ID dell'utente.
     *
     * @return L'ID dell'utente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID dell'utente.
     *
     * @param id L'ID dell'utente da impostare.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce l'indirizzo email dell'utente.
     *
     * @return L'indirizzo email dell'utente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'indirizzo email dell'utente.
     *
     * @param email L'indirizzo email dell'utente da impostare.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce la password dell'utente.
     *
     * @return La password dell'utente.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password dell'utente.
     *
     * @param password La password dell'utente da impostare.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
