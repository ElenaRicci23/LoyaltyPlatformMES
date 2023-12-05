package com.example.loyaltyPlatformSicuro.programmiFedelta;

import com.example.loyaltyPlatformSicuro.utenti.azienda.Azienda;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ProgrammaFedelta {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private String descrizione;

    @Enumerated(EnumType.STRING)
    private TipoProgrammaFedelta tipoProgrammaFedelta;

    @ManyToOne
    @JoinColumn(name = "azienda_id")
    private Azienda azienda;


    public ProgrammaFedelta(Long id, String nome, String descrizione, TipoProgrammaFedelta tipoProgrammaFedelta) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipoProgrammaFedelta = tipoProgrammaFedelta;
    }

    public ProgrammaFedelta() {
    }
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

    public TipoProgrammaFedelta getTipoProgrammaFedelta() {
        return tipoProgrammaFedelta;
    }

    public void setTipoProgrammaFedelta(TipoProgrammaFedelta tipoProgrammaFedelta) {
        this.tipoProgrammaFedelta = tipoProgrammaFedelta;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }


    public String getDescrizione() {
        return descrizione;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }



}