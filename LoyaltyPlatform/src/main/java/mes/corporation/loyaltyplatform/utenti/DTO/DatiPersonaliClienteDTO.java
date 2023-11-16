package mes.corporation.loyaltyplatform.utenti.DTO;

import org.springframework.core.StandardReflectionParameterNameDiscoverer;

import java.sql.Date;
/**
 * Questa classe rappresenta un DTO (Data Transfer Object) per i dati personali di un cliente.
 * Viene utilizzato per trasferire informazioni tra il client e il server.
 */
public class DatiPersonaliClienteDTO {
    private String nome;
    private String cognome;
    private char sesso;
    private String codiceFiscale;
    private Date dataNascita;
    private String residenza;
    private String indirizzo;
    private String cellulare;

    /**
     * Costruttore per la classe DatiPersonaliClienteDTO.
     */

    public DatiPersonaliClienteDTO(String nome, String cognome, char sesso, String codiceFiscale, Date dataNascita, String residenza, String indirizzo, String cellulare) {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.codiceFiscale = codiceFiscale;
        this.dataNascita = dataNascita;
        this.residenza = residenza;
        this.indirizzo = indirizzo;
        this.cellulare = cellulare;
    }
    /**
     * Restituisce il nome del cliente.
     *
     * @return Il nome del cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del cliente.
     *
     * @param nome Il nome del cliente da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome del cliente.
     *
     * @return Il cognome del cliente.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome del cliente.
     *
     * @param cognome Il cognome del cliente da impostare.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce il sesso del cliente.
     *
     * @return Il sesso del cliente.
     */
    public char getSesso() {
        return sesso;
    }

    /**
     * Imposta il sesso del cliente.
     *
     * @param sesso Il sesso del cliente da impostare.
     */
    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    /**
     * Restituisce il codice fiscale del cliente.
     *
     * @return Il codice fiscale del cliente.
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Imposta il codice fiscale del cliente.
     *
     * @param codiceFiscale Il codice fiscale del cliente da impostare.
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * Restituisce la data di nascita del cliente.
     *
     * @return La data di nascita del cliente.
     */
    public Date getDataNascita() {
        return dataNascita;
    }

    /**
     * Imposta la data di nascita del cliente.
     *
     * @param dataNascita La data di nascita del cliente da impostare.
     */
    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    /**
     * Restituisce la residenza del cliente.
     *
     * @return La residenza del cliente.
     */
    public String getResidenza() {
        return residenza;
    }

    /**
     * Imposta la residenza del cliente.
     *
     * @param residenza La residenza del cliente da impostare.
     */
    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    /**
     * Restituisce l'indirizzo del cliente.
     *
     * @return L'indirizzo del cliente.
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Imposta l'indirizzo del cliente.
     *
     * @param indirizzo L'indirizzo del cliente da impostare.
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Restituisce il numero di cellulare del cliente.
     *
     * @return Il numero di cellulare del cliente.
     */
    public String getCellulare() {
        return cellulare;
    }

    /**
     * Imposta il numero di cellulare del cliente.
     *
     * @param cellulare Il numero di cellulare del cliente da impostare.
     */
    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }
}
