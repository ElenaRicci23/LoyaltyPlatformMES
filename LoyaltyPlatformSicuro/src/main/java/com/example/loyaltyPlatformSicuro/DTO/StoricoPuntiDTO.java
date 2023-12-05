package com.example.loyaltyPlatformSicuro.DTO;

import java.time.LocalDateTime;

public class StoricoPuntiDTO {
    private Long id;
    private int puntiGuadagnati;
    private int puntiUtilizzati;
    private LocalDateTime dataOperazione;
    private int operazione;
    private int punti;


    public int getOperazione() {
        return operazione;
    }

    public void setOperazione(int operazione) {
        this.operazione = operazione;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPuntiGuadagnati() {
        return puntiGuadagnati;
    }

    public void setPuntiGuadagnati(int puntiGuadagnati) {
        this.puntiGuadagnati = puntiGuadagnati;
    }

    public int getPuntiUtilizzati() {
        return puntiUtilizzati;
    }

    public void setPuntiUtilizzati(int puntiUtilizzati) {
        this.puntiUtilizzati = puntiUtilizzati;
    }

    public LocalDateTime getDataOperazione() {
        return dataOperazione;
    }

    public void setDataOperazione(LocalDateTime dataOperazione) {
        this.dataOperazione = dataOperazione;
    }


}
