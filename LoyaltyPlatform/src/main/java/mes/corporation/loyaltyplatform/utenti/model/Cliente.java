package mes.corporation.loyaltyplatform.utenti.model;

import jakarta.persistence.*;


/**
 * Rappresenta un cliente nel sistema, estendendo la classe astratta Utente.
 */
@Entity
public class Cliente extends Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Dati personali del cliente, come nome, cognome, data di nascita, ecc.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id")
    private DatiPersonaliCliente datiPersonali;

    /**
     * Tessera associata al cliente per il programma Fedelta.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proprietario")
    private Tessera tessera;

    /**
     * Costruttore vuoto per l'entità Cliente.
     */
    public Cliente() {
    }

    /**
     * Costruttore per creare un'istanza di Cliente con dati iniziali.
     *
     * @param nome          Il nome del cliente.
     * @param cognome       Il cognome del cliente.
     * @param email         L'indirizzo email del cliente.
     * @param password      La password associata all'account del cliente.
     * @param datiPersonali Dati personali del cliente come oggetto DatiPersonaliCliente.
     */
    public Cliente(String nome, String cognome, String email, String password, DatiPersonaliCliente datiPersonali) {
        super(email, password);
        this.datiPersonali = datiPersonali;
        this.datiPersonali.setCliente(this); // Imposta il collegamento bidirezionale
    }

    /**
     * Restituisce i dati personali del cliente.
     *
     * @return I dati personali del cliente.
     */
    public DatiPersonaliCliente getDatiPersonali() {
        return datiPersonali;
    }

    /**
     * Imposta i dati personali del cliente.
     *
     * @param datiPersonali I dati personali del cliente.
     */
    public void setDatiPersonali(DatiPersonaliCliente datiPersonali) {
        this.datiPersonali = datiPersonali;
        this.datiPersonali.setCliente(this); // Imposta il collegamento bidirezionale
    }

    /**
     * Restituisce la tessera associata al cliente per il programma Fedelta.
     *
     * @return La tessera del cliente.
     */
    public Tessera getTessera() {
        return tessera;
    }

    /**
     * Imposta la tessera associata al cliente per il programma Fedelta.
     *
     * @param tessera La tessera del cliente.
     */
    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}

