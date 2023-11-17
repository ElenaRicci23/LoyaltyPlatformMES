package mes.corporation.loyaltyplatform.utenti.DTO;

/**
 * Questa classe rappresenta un DTO (Data Transfer Object) per i dati personali di un'azienda.
 * Viene utilizzato per trasferire informazioni tra il client e il server.
 */
public class DatiPersonaliAziendaDTO {
    private String nome;
    private String partitaIva;
    private String codiceUnivoco;
    private String ragioneSociale;
    private String settore;
    private String indirizzo;
    private int numeroStabilimenti;

    /**
     * Costruttore per la classe DatiPersonaliAziendaDTO.
     *
     * @param nome             Il nome dell'azienda.
     * @param partitaIva       La partita IVA dell'azienda.
     * @param codiceUnivoco    Il codice univoco dell'azienda.
     * @param ragioneSociale   La ragione sociale dell'azienda.
     * @param settore          Il settore dell'azienda.
     * @param indirizzo        L'indirizzo dell'azienda.
     * @param numeroStabilimenti Il numero di stabilimenti dell'azienda.
     */
    public DatiPersonaliAziendaDTO(String nome, String partitaIva, String codiceUnivoco, String ragioneSociale, String settore, String indirizzo, int numeroStabilimenti) {
        this.nome = nome;
        this.partitaIva = partitaIva;
        this.codiceUnivoco = codiceUnivoco;
        this.ragioneSociale = ragioneSociale;
        this.settore = settore;
        this.indirizzo = indirizzo;
        this.numeroStabilimenti = numeroStabilimenti;
    }

    public DatiPersonaliAziendaDTO() {

    }

    /**
     * Restituisce il nome dell'azienda.
     *
     * @return Il nome dell'azienda.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'azienda.
     *
     * @param nome Il nome dell'azienda da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce la partita IVA dell'azienda.
     *
     * @return La partita IVA dell'azienda.
     */
    public String getPartitaIva() {
        return partitaIva;
    }

    /**
     * Imposta la partita IVA dell'azienda.
     *
     * @param partitaIva La partita IVA dell'azienda da impostare.
     */
    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    /**
     * Restituisce il codice univoco dell'azienda.
     *
     * @return Il codice univoco dell'azienda.
     */
    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }

    /**
     * Imposta il codice univoco dell'azienda.
     *
     * @param codiceUnivoco Il codice univoco dell'azienda da impostare.
     */
    public void setCodiceUnivoco(String codiceUnivoco) {
        this.codiceUnivoco = codiceUnivoco;
    }

    /**
     * Restituisce la ragione sociale dell'azienda.
     *
     * @return La ragione sociale dell'azienda.
     */
    public String getRagioneSociale() {
        return ragioneSociale;
    }

    /**
     * Imposta la ragione sociale dell'azienda.
     *
     * @param ragioneSociale La ragione sociale dell'azienda da impostare.
     */
    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    /**
     * Restituisce il settore dell'azienda.
     *
     * @return Il settore dell'azienda.
     */
    public String getSettore() {
        return settore;
    }

    /**
     * Imposta il settore dell'azienda.
     *
     * @param settore Il settore dell'azienda da impostare.
     */
    public void setSettore(String settore) {
        this.settore = settore;
    }

    /**
     * Restituisce l'indirizzo dell'azienda.
     *
     * @return L'indirizzo dell'azienda.
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Imposta l'indirizzo dell'azienda.
     *
     * @param indirizzo L'indirizzo dell'azienda da impostare.
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Restituisce il numero di stabilimenti dell'azienda.
     *
     * @return Il numero di stabilimenti dell'azienda.
     */
    public int getNumeroStabilimenti() {
        return numeroStabilimenti;
    }

    /**
     * Imposta il numero di stabilimenti dell'azienda.
     *
     * @param numeroStabilimenti Il numero di stabilimenti dell'azienda da impostare.
     */
    public void setNumeroStabilimenti(int numeroStabilimenti) {
        this.numeroStabilimenti = numeroStabilimenti;
    }
}
