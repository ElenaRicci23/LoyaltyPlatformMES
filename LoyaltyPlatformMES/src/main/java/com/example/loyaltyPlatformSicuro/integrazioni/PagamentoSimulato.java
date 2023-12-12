package com.example.loyaltyPlatformSicuro.integrazioni;



/**
 * Questa classe rappresenta un oggetto PagamentoSimulato che contiene informazioni su una transazione di pagamento simulata.
 * È utilizzata per memorizzare dettagli come la conferma della transazione, un messaggio associato, la data della transazione,
 * l'importo pagato e il nome dell'azienda coinvolta.
 */
public class PagamentoSimulato {
    /**
     * Indica se la transazione è confermata o meno.
     */
    private boolean conferma;

    /**
     * Un messaggio associato alla transazione.
     */
    private String messaggio;

    /**
     * La data della transazione.
     */
    private String dataTransazione;

    /**
     * L'importo pagato nella transazione.
     */
    private double importoPagato;

    /**
     * Il nome dell'azienda coinvolta nella transazione.
     */
    private String nomeAzienda;

    /**
     * Restituisce true se la transazione è confermata, altrimenti restituisce false.
     *
     * @return True se la transazione è confermata, altrimenti false.
     */
    public boolean isConferma() {
        return conferma;
    }

    /**
     * Imposta lo stato di conferma della transazione.
     *
     * @param conferma True se la transazione è confermata, altrimenti false.
     */
    public void setConferma(boolean conferma) {
        this.conferma = conferma;
    }

    /**
     * Restituisce il messaggio associato alla transazione.
     *
     * @return Il messaggio della transazione.
     */
    public String getMessaggio() {
        return messaggio;
    }

    /**
     * Imposta il messaggio associato alla transazione.
     *
     * @param messaggio Il messaggio da impostare.
     */
    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    /**
     * Restituisce la data della transazione.
     *
     * @return La data della transazione.
     */
    public String getDataTransazione() {
        return dataTransazione;
    }

    /**
     * Imposta la data della transazione.
     *
     * @param dataTransazione La data da impostare.
     */
    public void setDataTransazione(String dataTransazione) {
        this.dataTransazione = dataTransazione;
    }

    /**
     * Restituisce l'importo pagato nella transazione.
     *
     * @return L'importo pagato nella transazione.
     */
    public double getImportoPagato() {
        return importoPagato;
    }

    /**
     * Imposta l'importo pagato nella transazione.
     *
     * @param importoPagato L'importo da impostare.
     */
    public void setImportoPagato(double importoPagato) {
        this.importoPagato = importoPagato;
    }

    /**
     * Restituisce il nome dell'azienda coinvolta nella transazione.
     *
     * @return Il nome dell'azienda coinvolta nella transazione.
     */
    public String getNomeAzienda() {
        return nomeAzienda;
    }

    /**
     * Imposta il nome dell'azienda coinvolta nella transazione.
     *
     * @param nomeAzienda Il nome dell'azienda da impostare.
     */
    public void setNomeAzienda(String nomeAzienda) {
        this.nomeAzienda = nomeAzienda;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto PagamentoSimulato.
     *
     * @return Una stringa con i dettagli dell'oggetto PagamentoSimulato.
     */
    @Override
    public String toString() {
        return "PagamentoSimulato{" +
                "conferma=" + conferma +
                ", messaggio='" + messaggio + '\'' +
                ", dataTransazione='" + dataTransazione + '\'' +
                ", importoPagato=" + importoPagato +
                ", nomeAzienda='" + nomeAzienda + '\'' +
                '}';
    }
}
