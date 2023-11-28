package com.example.loyaltyPlatformSicuro.utenti.model;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

/**
 * Rappresenta i dati personali di un'azienda.
 */
@Entity
public class DatiPersonaliAzienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String partitaIva;
    private String codiceUnivoco;
    private String ragioneSociale;
    private String settore;
    private String indirizzo;
    private int numeroStabilimenti;

    /**
     * Azienda associata a questi dati personali.
     */
    @OneToOne(mappedBy = "datiPersonali")
    @JoinColumn(name = "azienda_id")
    private Azienda azienda;


    /**
     * Costruttore vuoto per l'entità DatiPersonaliAzienda.
     */
    public DatiPersonaliAzienda() {
    }

    /**
     * Restituisce l'ID dei dati personali dell'azienda.
     *
     * @return L'ID dei dati personali dell'azienda.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID dei dati personali dell'azienda.
     *
     * @param id L'ID dei dati personali dell'azienda.
     */
    public void setId(Long id) {
        this.id = id;
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
     * @param nome Il nome dell'azienda.
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
     * @param partitaIva La partita IVA dell'azienda.
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
     * @param codiceUnivoco Il codice univoco dell'azienda.
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
     * @param ragioneSociale La ragione sociale dell'azienda.
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
     * @param settore Il settore dell'azienda.
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
     * @param indirizzo L'indirizzo dell'azienda.
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
     * @param numeroStabilimenti Il numero di stabilimenti dell'azienda.
     */
    public void setNumeroStabilimenti(int numeroStabilimenti) {
        this.numeroStabilimenti = numeroStabilimenti;
    }

    /**
     * Restituisce l'azienda associata a questi dati personali.
     *
     * @return L'azienda associata a questi dati personali.
     */
    public Azienda getAzienda() {
        return azienda;
    }

    /**
     * Imposta l'azienda associata a questi dati personali.
     *
     * @param azienda L'azienda associata a questi dati personali.
     */
    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }
}
