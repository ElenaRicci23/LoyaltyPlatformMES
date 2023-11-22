package com.example.pf.configurazione;

import org.springframework.stereotype.Component;

@Component
public class ProgrammaPuntiConfigurazione {
    private int puntiPerAcquisto;
    private int sogliaPremio;

    // Altre regole e parametri

    public int getPuntiPerAcquisto() {
        return puntiPerAcquisto;
    }

    public void setPuntiPerAcquisto(int puntiPerAcquisto) {
        this.puntiPerAcquisto = puntiPerAcquisto;
    }

    public int getSogliaPremio() {
        return sogliaPremio;
    }

    public void setSogliaPremio(int sogliaPremio) {
        this.sogliaPremio = sogliaPremio;
    }

    // Aggiungi altri metodi per configurare le regole
    public void impostaRegoleAccumuloPunti(int puntiPerAcquisto) {
        this.puntiPerAcquisto = puntiPerAcquisto;
    }

    public void impostaRegoleSogliaPremio(int sogliaPremio) {
        this.sogliaPremio = sogliaPremio;
    }

    // Altri metodi getter e setter per le regole
}
