package com.example.pf.configurazione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class ProgrammaPuntiConfigurazioneService {
    private ProgrammaPuntiConfigurazione configurazione;

    @Autowired
    public ProgrammaPuntiConfigurazioneService(ProgrammaPuntiConfigurazione configurazione) {
        this.configurazione = configurazione;
    }

    public void impostaPuntiPerAcquisto(int punti) {
        configurazione.setPuntiPerAcquisto(punti);
    }

    public void impostaSogliaPremio(int soglia) {
        configurazione.setSogliaPremio(soglia);
    }

    // Altri metodi per configurare il programma
}
