package mes.corporation.loyaltyplatform.utenti.DTO;


/**
 * Questa classe rappresenta un DTO (Data Transfer Object) per i dati di un'azienda.
 * Viene utilizzato per trasferire informazioni tra il client e il server.
 */
public class AziendaDTO {
    private String email;
    private String password;
    private DatiPersonaliAziendaDTO datiPersonali;

    /**
     * Costruttore per la classe AziendaDTO.
     *
     * @param email        L'email dell'azienda.
     * @param password     La password dell'azienda.
     * @param datiPersonali I dati personali dell'azienda.
     */
    public AziendaDTO(String email, String password, DatiPersonaliAziendaDTO datiPersonali) {
        this.email = email;
        this.password = password;
        this.datiPersonali = datiPersonali;
    }

    public AziendaDTO() {

    }

    /**
     * Restituisce l'email dell'azienda.
     *
     * @return L'email dell'azienda.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'email dell'azienda.
     *
     * @param email L'email dell'azienda da impostare.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce la password dell'azienda.
     *
     * @return La password dell'azienda.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password dell'azienda.
     *
     * @param password La password dell'azienda da impostare.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Restituisce i dati personali dell'azienda.
     *
     * @return I dati personali dell'azienda.
     */
    public DatiPersonaliAziendaDTO getDatiPersonali() {
        return datiPersonali;
    }

    /**
     * Imposta i dati personali dell'azienda.
     *
     * @param datiPersonali I dati personali dell'azienda da impostare.
     */
    public void setDatiPersonali(DatiPersonaliAziendaDTO datiPersonali) {
        this.datiPersonali = datiPersonali;
    }
}





