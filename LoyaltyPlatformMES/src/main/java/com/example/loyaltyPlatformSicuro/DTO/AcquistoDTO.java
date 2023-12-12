package com.example.loyaltyPlatformSicuro.DTO;

import java.time.LocalDate;
/**
 * Questa classe rappresenta un oggetto AcquistoDTO che contiene informazioni sull'acquisto di un prodotto.
 * È utilizzata per memorizzare i dettagli dell'acquisto effettuato da un cliente.
 */
public class AcquistoDTO {
    /**
     * Il nome del prodotto acquistato.
     */
    private String nomeProdottoAcquistato;

    /**
     * L'importo del prodotto acquistato.
     */
    private double importoProdotto;

    /**
     * La data in cui è stato effettuato il pagamento per l'acquisto.
     */
    private LocalDate dataPagamento;

    /**
     * Il nome dell'azienda presso la quale è stato effettuato l'acquisto.
     */
    private String nomeAziendaAcquisto;

    /**
     * L'ID del cliente che ha effettuato l'acquisto.
     */
    private Long clienteId;

    /**
     * Restituisce l'ID del cliente.
     *
     * @return L'ID del cliente.
     */
    public Long getClienteId() {
        return clienteId;
    }

    /**
     * Imposta l'ID del cliente.
     *
     * @param clienteId L'ID del cliente da impostare.
     */
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Restituisce il nome del prodotto acquistato.
     *
     * @return Il nome del prodotto acquistato.
     */
    public String getNomeProdottoAcquistato() {
        return nomeProdottoAcquistato;
    }

    /**
     * Imposta il nome del prodotto acquistato.
     *
     * @param nomeProdottoAcquistato Il nome del prodotto da impostare.
     */
    public void setNomeProdottoAcquistato(String nomeProdottoAcquistato) {
        this.nomeProdottoAcquistato = nomeProdottoAcquistato;
    }

    /**
     * Restituisce l'importo del prodotto acquistato.
     *
     * @return L'importo del prodotto acquistato.
     */
    public double getImportoProdotto() {
        return importoProdotto;
    }

    /**
     * Imposta l'importo del prodotto acquistato.
     *
     * @param importoProdotto L'importo del prodotto da impostare.
     */
    public void setImportoProdotto(double importoProdotto) {
        this.importoProdotto = importoProdotto;
    }

    /**
     * Restituisce la data in cui è stato effettuato il pagamento.
     *
     * @return La data di pagamento.
     */
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    /**
     * Imposta la data di pagamento.
     *
     * @param dataPagamento La data di pagamento da impostare.
     */
    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    /**
     * Restituisce il nome dell'azienda presso la quale è stato effettuato l'acquisto.
     *
     * @return Il nome dell'azienda dell'acquisto.
     */
    public String getNomeAziendaAcquisto() {
        return nomeAziendaAcquisto;
    }

    /**
     * Imposta il nome dell'azienda dell'acquisto.
     *
     * @param nomeAziendaAcquisto Il nome dell'azienda da impostare.
     */
    public void setNomeAziendaAcquisto(String nomeAziendaAcquisto) {
        this.nomeAziendaAcquisto = nomeAziendaAcquisto;
    }
}


