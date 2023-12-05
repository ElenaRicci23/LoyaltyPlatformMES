package com.example.loyaltyPlatformSicuro.DTO;

public class PremioDTO {

    private String nome;
    private int puntiDelPremio;
    private String descrizione;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPuntiDelPremio() {
        return puntiDelPremio;
    }

    public void setPuntiDelPremio(int puntiDelPremio) {
        this.puntiDelPremio = puntiDelPremio;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
