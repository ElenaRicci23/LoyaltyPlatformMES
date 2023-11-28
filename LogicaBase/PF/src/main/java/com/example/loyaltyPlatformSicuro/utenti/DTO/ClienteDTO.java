package com.example.loyaltyPlatformSicuro.utenti.DTO;

/**
 * Questa classe rappresenta un DTO (Data Transfer Object) per i dati di un cliente.
 * Viene utilizzato per trasferire informazioni tra il client e il server.
 */
public class ClienteDTO {
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private DatiPersonaliClienteDTO datiPersonali;

    /**
     * Costruttore per la classe ClienteDTO.
     *
     * @param nome     Il nome del cliente.
     * @param cognome  Il cognome del cliente.
     * @param email    L'email del cliente.
     * @param password La password del cliente.
     */
    public ClienteDTO(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    /**
     * Restituisce il nome del cliente.
     *
     * @return Il nome del cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del cliente.
     *
     * @param nome Il nome del cliente da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome del cliente.
     *
     * @return Il cognome del cliente.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome del cliente.
     *
     * @param cognome Il cognome del cliente da impostare.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce l'email del cliente.
     *
     * @return L'email del cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'email del cliente.
     *
     * @param email L'email del cliente da impostare.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce la password del cliente.
     *
     * @return La password del cliente.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password del cliente.
     *
     * @param password La password del cliente da impostare.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Restituisce i dati personali del cliente.
     *
     * @return I dati personali del cliente.
     */
    public DatiPersonaliClienteDTO getDatiPersonali() {
        return datiPersonali;
    }

    /**
     * Imposta i dati personali del cliente.
     *
     * @param datiPersonali I dati personali del cliente da impostare.
     */
    public void setDatiPersonali(DatiPersonaliClienteDTO datiPersonali) {
        this.datiPersonali = datiPersonali;
    }
}
