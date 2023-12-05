package com.example.loyaltyPlatformSicuro.integrazioni;

public class PagamentoSimulato {
    private boolean conferma;
    private String messaggio;
    private String dataTransazione;
    private double importoPagato;
    private String nomeAzienda;

    public boolean isConferma() {
        return conferma;
    }

    public void setConferma(boolean conferma) {
        this.conferma = conferma;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getDataTransazione() {
        return dataTransazione;
    }

    public void setDataTransazione(String dataTransazione) {
        this.dataTransazione = dataTransazione;
    }

    public double getImportoPagato() {
        return importoPagato;
    }

    public void setImportoPagato(double importoPagato) {
        this.importoPagato = importoPagato;
    }

    public String getNomeAzienda() {
        return nomeAzienda;
    }

    public void setNomeAzienda(String nomeAzienda) {
        this.nomeAzienda = nomeAzienda;
    }
}

