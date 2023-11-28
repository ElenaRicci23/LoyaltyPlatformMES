package com.example.loyaltyPlatformSicuro.utenti.DTO;

public class ProgrammaFedeltaDTO {
    private Long id;
    private String nome;
    private String descrizione;
    private String tipoProgramma; // Cambiato il tipo da TipoProgrammaFedelta a String

    // Getter e setter per i campi

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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getTipoProgramma() {
        return tipoProgramma;
    }

    public void setTipoProgramma(String tipoProgramma) {
        this.tipoProgramma = tipoProgramma;
    }
}
