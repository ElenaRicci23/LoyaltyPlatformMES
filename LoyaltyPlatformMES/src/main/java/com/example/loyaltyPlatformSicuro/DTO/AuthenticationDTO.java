package com.example.loyaltyPlatformSicuro.DTO;


/**
 * Questa classe rappresenta un oggetto AuthenticationDTO che contiene informazioni di autenticazione dell'utente.
 * È utilizzata per memorizzare l'email e la password dell'utente durante il processo di autenticazione.
 */
public class AuthenticationDTO {
    /**
     * L'indirizzo email dell'utente.
     */
    private String email;

    /**
     * La password dell'utente.
     */
    private String password;

    /**
     * Costruttore vuoto richiesto da Spring.
     */
    public AuthenticationDTO() {
        // Costruttore vuoto richiesto da Spring
    }

    /**
     * Costruttore che prende in input l'email e la password dell'utente.
     *
     * @param email    L'indirizzo email dell'utente.
     * @param password La password dell'utente.
     */
    public AuthenticationDTO(String email, String password) {
        this.email = email;
        this.password = password;
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
     * @param email L'indirizzo email da impostare.
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
     * @param password La password da impostare.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
