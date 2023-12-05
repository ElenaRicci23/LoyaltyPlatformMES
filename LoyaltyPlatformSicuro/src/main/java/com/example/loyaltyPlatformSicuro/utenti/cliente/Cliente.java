package com.example.loyaltyPlatformSicuro.utenti.cliente;


import com.example.loyaltyPlatformSicuro.utenti.Utente;
import com.example.loyaltyPlatformSicuro.utenti.cliente.tessera.Tessera;
import jakarta.persistence.*;


/**
 * Rappresenta un cliente nel sistema, estendendo la classe astratta Utente.
 */
@Entity
public class Cliente extends Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn
//    private Utente utente;

    private String ruolo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    private Tessera tessera;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private DatiPersonaliCliente datiPersonali = new DatiPersonaliCliente();

    public Cliente() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

//    public Utente getUtente() {
//        return utente;
//    }
//
//    public void setUtente(Utente utente) {
//        this.utente = utente;
//    }

    public DatiPersonaliCliente getDatiPersonali() {
        return datiPersonali;
    }

    public void setDatiPersonali(DatiPersonaliCliente datiPersonali) {
        this.datiPersonali = datiPersonali;
    }

    public Cliente(String nome, String cognome, String email, String password, DatiPersonaliCliente datiPersonali) {
        super(email, password);
    }


    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
    public Long getTesseraId() {
        if (tessera != null) {
            return tessera.getId();
        }
        return null;
    }



}

