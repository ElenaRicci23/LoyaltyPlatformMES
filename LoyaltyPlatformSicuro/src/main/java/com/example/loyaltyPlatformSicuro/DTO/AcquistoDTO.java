package com.example.loyaltyPlatformSicuro.DTO;

import java.time.LocalDate;

public class AcquistoDTO {
    private String nomeProdottoAcquistato;
    private double importoProdotto;
    private LocalDate dataPagamento;
    private String nomeAziendaAcquisto;

    private Long clienteId;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNomeProdottoAcquistato() {
        return nomeProdottoAcquistato;
    }

    public void setNomeProdottoAcquistato(String nomeProdottoAcquistato) {
        this.nomeProdottoAcquistato = nomeProdottoAcquistato;
    }

    public double getImportoProdotto() {
        return importoProdotto;
    }

    public void setImportoProdotto(double importoProdotto) {
        this.importoProdotto = importoProdotto;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getNomeAziendaAcquisto() {
        return nomeAziendaAcquisto;
    }

    public void setNomeAziendaAcquisto(String nomeAziendaAcquisto) {
        this.nomeAziendaAcquisto = nomeAziendaAcquisto;
    }
}

