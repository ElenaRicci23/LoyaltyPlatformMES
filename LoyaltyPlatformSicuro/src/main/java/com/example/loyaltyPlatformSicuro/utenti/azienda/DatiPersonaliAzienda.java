package com.example.loyaltyPlatformSicuro.utenti.azienda;

import jakarta.persistence.*;

/**
 * Rappresenta i dati personali di un'azienda.
 */

@Entity
@Table(name = "dati_personali_azienda")
public class DatiPersonaliAzienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "partita_iva")
    private String partitaIva;

    @Column(name = "codice_univoco")
    private String codiceUnivoco;

    @Column(name = "ragione_sociale")
    private String ragioneSociale;

    @Column(name = "settore")
    private String settore;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "numero_stabilimenti")
    private int numeroStabilimenti;

    @OneToOne(mappedBy = "datiPersonali")
    private Azienda azienda;

    // Metodi Getter e Setter per tutti i campi

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }

    public void setCodiceUnivoco(String codiceUnivoco) {
        this.codiceUnivoco = codiceUnivoco;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getSettore() {
        return settore;
    }

    public void setSettore(String settore) {
        this.settore = settore;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public int getNumeroStabilimenti() {
        return numeroStabilimenti;
    }

    public void setNumeroStabilimenti(int numeroStabilimenti) {
        this.numeroStabilimenti = numeroStabilimenti;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }
}
