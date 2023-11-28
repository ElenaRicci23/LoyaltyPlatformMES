package com.example.loyaltyPlatformSicuro.fedelta.model;

import com.example.loyaltyPlatformSicuro.utenti.model.Azienda;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Questa classe rappresenta un programma Fedelta generico.
 * Può essere estesa per creare implementazioni specifiche di programmi Fedelta.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public class ProgrammaFedelta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;

    @Enumerated(EnumType.STRING)
    private TipoProgrammaFedelta tipoProgramma;

    @ManyToOne
    @JoinColumn(name = "azienda_id")
    @JsonBackReference
    private Azienda azienda;

    /**
     * Costruttore per creare un'istanza di ProgrammaFedelta generco.
     *
     * @param nome           Il nome del programma Fedelta.
     * @param tipoProgramma  Il tipo di programma Fedelta.
     * @param descrizione         Il valore associato al programma Fedelta.
     */
    public ProgrammaFedelta(String nome, String descrizione, TipoProgrammaFedelta tipoProgramma) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipoProgramma = tipoProgramma;
    }

    /**
     * Costruttore per creare un'istanza di ProgrammaFedelta per aziende.
     *
     * @param nome           Il nome del programma Fedelta.
     * @param tipoProgramma  Il tipo di programma Fedelta.
     * @param descrizione         Il valore associato al programma Fedelta.
     */
    public ProgrammaFedelta(String nome, String descrizione, TipoProgrammaFedelta tipoProgramma, Azienda azienda) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipoProgramma = tipoProgramma;
        this.azienda = azienda;
    }

    public ProgrammaFedelta() {
    }

    /**
     * Restituisce il nome del programma Fedelta.
     *
     * @return Il nome del programma Fedelta.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce il tipo di programma Fedelta.
     *
     * @return Il tipo di programma Fedelta.
     */
    public TipoProgrammaFedelta getTipoProgramma() {
        return tipoProgramma;
    }

    public Long getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setTipoProgramma(TipoProgrammaFedelta tipoProgramma) {
        this.tipoProgramma = tipoProgramma;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public void setTipoProgrammaFedelta(TipoProgrammaFedelta tipoProgrammaFedelta) {
        this.tipoProgramma = tipoProgrammaFedelta;
    }


}
