package mes.corporation.loyaltyplatform.cliente;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.tessera.Tessera;

import java.sql.Date;

/**
 * Questa classe rappresenta un cliente all'interno del sistema.
 */
@Entity
public class Cliente {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String email;
    private String cognome;
    private char sesso;
    private String codiceFiscale;
    private Date dataNascita;
    private String residenza;
    private String indirizzo;
    private String cellulare;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    private Tessera tessera;

    /**
     * Ottiene l'ID del cliente.
     *
     * @return L'ID del cliente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID del cliente.
     *
     * @param id L'ID da impostare.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Ottiene il nome del cliente.
     *
     * @return Il nome del cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del cliente.
     *
     * @param nome Il nome da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Ottiene l'email del cliente.
     *
     * @return L'email del cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'email del cliente.
     *
     * @param email L'email da impostare.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Ottiene il cognome del cliente.
     *
     * @return Il cognome del cliente.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome del cliente.
     *
     * @param cognome Il cognome da impostare.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Ottiene il sesso del cliente.
     *
     * @return Il sesso del cliente.
     */
    public char getSesso() {
        return sesso;
    }

    /**
     * Imposta il sesso del cliente.
     *
     * @param sesso Il sesso da impostare.
     */
    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    /**
     * Ottiene il codice fiscale del cliente.
     *
     * @return Il codice fiscale del cliente.
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Imposta il codice fiscale del cliente.
     *
     * @param codiceFiscale Il codice fiscale da impostare.
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * Ottiene la data di nascita del cliente.
     *
     * @return La data di nascita del cliente.
     */
    public Date getDataNascita() {
        return dataNascita;
    }

    /**
     * Imposta la data di nascita del cliente.
     *
     * @param dataNascita La data di nascita da impostare.
     */
    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    /**
     * Ottiene la residenza del cliente.
     *
     * @return La residenza del cliente.
     */
    public String getResidenza() {
        return residenza;
    }

    /**
     * Imposta la residenza del cliente.
     *
     * @param residenza La residenza da impostare.
     */
    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    /**
     * Ottiene l'indirizzo del cliente.
     *
     * @return L'indirizzo del cliente.
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Imposta l'indirizzo del cliente.
     *
     * @param indirizzo L'indirizzo da impostare.
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Ottiene il numero di cellulare del cliente.
     *
     * @return Il numero di cellulare del cliente.
     */
    public String getCellulare() {
        return cellulare;
    }

    /**
     * Imposta il numero di cellulare del cliente.
     *
     * @param cellulare Il numero di cellulare da impostare.
     */
    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    /**
     * Ottiene la tessera associata al cliente.
     *
     * @return La tessera associata al cliente.
     */
    public Tessera getTessera() {
        return tessera;
    }

    /**
     * Imposta la tessera associata al cliente.
     *
     * @param tessera La tessera da associare al cliente.
     */
    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }
}
