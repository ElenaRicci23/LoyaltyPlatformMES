package com.example.loyaltyPlatformSicuro.utenti.cliente;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "dati_personali_cliente")
public class DatiPersonaliCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "sesso")
    private char sesso;

    @Column(name = "codice_fiscale")
    private String codiceFiscale;

    @Column(name = "data_nascita")
    private Date dataNascita;

    @Column(name = "residenza")
    private String residenza;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "cellulare")
    private String cellulare;

    @OneToOne(mappedBy = "datiPersonali")
    private Cliente cliente;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
