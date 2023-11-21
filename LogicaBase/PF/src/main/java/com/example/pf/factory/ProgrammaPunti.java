package com.example.pf.factory;

import com.example.pf.model.ProgrammaFedelta;
import com.example.pf.model.TipoProgrammaFedelta;

public class ProgrammaPunti extends ProgrammaFedelta implements IProgrammaPunti {
    private String messaggio;
    private int punti;

    @Override
    public void accumulaPunti(int punti) {
        this.punti=punti;

    }
    @Override
    public int getPuntiTotali() {
        return punti;
    }
    public ProgrammaPunti(String nome, String descrizione) {
        // Imposta i campi necessari e chiama il costruttore della superclasse
        super(null, nome, descrizione, TipoProgrammaFedelta.PUNTI);
    }

}
