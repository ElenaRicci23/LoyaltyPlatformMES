package com.example.pf.factory;

import com.example.pf.model.ProgrammaFedelta;
import com.example.pf.model.TipoProgrammaFedelta;
import jakarta.persistence.Entity;

//Da cambaire logica ..perchè l ofare al tessera o un altra table
@Entity
public class ProgrammaPunti extends ProgrammaFedelta implements IProgrammaPunti {
    private int puntiTotali;  // Variabile per tenere traccia dei punti accumulati
     //Non va bene da fare per il cliente
    // Costruttore
    public ProgrammaPunti(String nome, String descrizione) {
        // Imposta i campi necessari e chiama il costruttore della superclasse
        super(null, nome, descrizione, TipoProgrammaFedelta.PUNTI);
    }

    public ProgrammaPunti() {

    }

    @Override
    public void accumulaPunti(int puntiDaAggiungere) {
        // Implementa l'accumulo generico di punti
        puntiTotali += puntiDaAggiungere;
    }

    @Override
    public int getPuntiTotali() {
        // Restituisce il totale dei punti accumulati fino a questo momento
        return puntiTotali;
    }
}

