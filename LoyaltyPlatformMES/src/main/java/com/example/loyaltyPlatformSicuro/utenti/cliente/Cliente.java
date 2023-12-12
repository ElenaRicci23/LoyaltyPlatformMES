package com.example.loyaltyPlatformSicuro.utenti.cliente;


import com.example.loyaltyPlatformSicuro.utenti.Utente;
import com.example.loyaltyPlatformSicuro.utenti.cliente.tessera.Tessera;
import jakarta.persistence.*;



/**
 * Rappresenta un cliente all'interno del sistema, estendendo la classe base "Utente".
 */
@Entity
@Table(name = "cliente")
public class Cliente extends Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ruolo")
    private String ruolo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    private Tessera tessera;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dati_personali_id")
    private DatiPersonaliCliente datiPersonali = new DatiPersonaliCliente();

    /**
     * Costruttore vuoto per la classe Cliente.
     */
    public Cliente() {
    }

    /**
     * Restituisce l'ID del cliente.
     *
     * @return L'ID del cliente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID del cliente.
     *
     * @param id L'ID da impostare per il cliente.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il ruolo del cliente.
     *
     * @return Il ruolo del cliente.
     */
    public String getRuolo() {
        return ruolo;
    }

    /**
     * Imposta il ruolo del cliente.
     *
     * @param ruolo Il ruolo da impostare per il cliente.
     */
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    /**
     * Restituisce la tessera associata al cliente.
     *
     * @return La tessera associata al cliente.
     */
    public Tessera getTessera() {
        return tessera;
    }

    /**
     * Imposta la tessera associata al cliente.
     *
     * @param tessera La tessera da associare al cliente.
     */
    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
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
     * @param datiPersonali I dati personali da impostare per il cliente.
     */
    public void setDatiPersonali(DatiPersonaliCliente datiPersonali) {
        this.datiPersonali = datiPersonali;
    }

    /**
     * Costruttore parametrico per la classe Cliente.
     *
     * @param nome            Il nome del cliente.
     * @param cognome         Il cognome del cliente.
     * @param email           L'email del cliente.
     * @param password        La password del cliente.
     * @param datiPersonali   I dati personali del cliente.
     */
    public Cliente(String nome, String cognome, String email, String password, DatiPersonaliCliente datiPersonali) {
        super(email, password);
    }

    /**
     * Restituisce l'ID della tessera associata al cliente, se presente.
     *
     * @return L'ID della tessera associata al cliente, o null se la tessera non è presente.
     */
    public Long getTesseraId() {
        if (tessera != null) {
            return tessera.getId();
        }
        return null;
    }
}
