package com.example.loyaltyPlatformSicuro.DTO;


import com.example.loyaltyPlatformSicuro.programmiFedelta.TipoProgrammaFedelta;

public class ProgrammaFedeltaDTO {

    private Long id;

    private String nome;
    private String descrizione;

    private TipoProgrammaFedelta tipoProgrammaFedelta;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TipoProgrammaFedelta getTipoProgrammaFedelta() {
        return tipoProgrammaFedelta;
    }

    public void setTipoProgrammaFedelta(TipoProgrammaFedelta tipoProgrammaFedelta) {
        this.tipoProgrammaFedelta = tipoProgrammaFedelta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


