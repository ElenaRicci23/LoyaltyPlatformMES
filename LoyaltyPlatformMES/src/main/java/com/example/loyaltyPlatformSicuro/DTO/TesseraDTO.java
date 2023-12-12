package com.example.loyaltyPlatformSicuro.DTO;

import java.time.LocalDate;
public class TesseraDTO {
    private Long id;
    private String codiceABarreUnivoco;
    private LocalDate dataEmissione;
    private LocalDate dataScadenza;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodiceABarreUnivoco() {
        return codiceABarreUnivoco;
    }

    public void setCodiceABarreUnivoco(String codiceABarreUnivoco) {
        this.codiceABarreUnivoco = codiceABarreUnivoco;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }
}