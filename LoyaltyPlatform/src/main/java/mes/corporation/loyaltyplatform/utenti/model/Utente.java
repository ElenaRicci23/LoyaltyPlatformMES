package mes.corporation.loyaltyplatform.utenti.model;

import jakarta.persistence.*;


/**
 * Questa classe astratta rappresenta un utente all'interno del sistema.
 * Definisce attributi comuni a tutti gli utenti come l'ID, l'email e la password.
 * Le classi concrete estenderanno questa classe per aggiungere ulteriori dettagli specifici.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Indirizzo email dell'utente, utilizzato come identificatore unico.
     */
    protected String email;

    /**
     * Password associata all'account dell'utente.
     */
    protected String password;

    /**
     * Costruttore vuoto per l'entità Utente.
     */
    public Utente() {
    }

    /**
     * Costruttore per creare un'istanza di Utente con dati iniziali.
     *
     * @param email    Indirizzo email dell'utente.
     * @param password Password associata all'account dell'utente.
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
     * @param id L'ID dell'utente.
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
     * @param email L'indirizzo email dell'utente.
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
     * @param password La password dell'utente.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
