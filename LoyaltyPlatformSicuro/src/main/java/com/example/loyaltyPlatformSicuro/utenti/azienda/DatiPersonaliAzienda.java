package com.example.loyaltyPlatformSicuro.utenti.azienda;

import jakarta.persistence.*;

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

    @OneToOne(mappedBy = "datiPersonali")
    @JoinColumn
    private Azienda azienda;

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

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

    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public String getSettore() {
        return settore;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public int getNumeroStabilimenti() {
        return numeroStabilimenti;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }


    public void setCodiceUnivoco(String codiceUnivoco) {
        this.codiceUnivoco = codiceUnivoco;
    }



    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }



    public void setSettore(String settore) {
        this.settore = settore;
    }



    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }


    public void setNumeroStabilimenti(int numeroStabilimenti) {
        this.numeroStabilimenti = numeroStabilimenti;
    }

}
