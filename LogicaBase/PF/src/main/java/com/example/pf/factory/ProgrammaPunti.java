package com.example.pf.factory;

import com.example.pf.cliente.Transazione;
import com.example.pf.model.ProgrammaFedelta;
import com.example.pf.model.TipoProgrammaFedelta;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

//Da cambaire logica ..perchè l ofare al tessera o un altra table
@Entity
public class ProgrammaPunti extends ProgrammaFedelta implements IProgrammaPunti {
    private int puntiTotali;  // Variabile per tenere traccia dei punti accumulati
    private List<Transazione> acquisti = new ArrayList<>();
    //Non va bene da fare per il cliente
    // Costruttore
    public ProgrammaPunti(String nome, String descrizione) {
        // Imposta i campi necessari e chiama il costruttore della superclasse
        super(null, nome, descrizione, TipoProgrammaFedelta.PUNTI);
    }

    public ProgrammaPunti() {

    }

    @Override
    public void accumulaPunti(int puntiDaAggiungere, Transazione transazione) {
        if (puntiDaAggiungere > 0) {
            puntiTotali += puntiDaAggiungere;
            // Aggiungi l'acquisto alla lista degli acquisti associati ai punti
            acquisti.add(transazione);
        } else {
            throw new IllegalArgumentException("Invalid points value. Points to add must be greater than 0.");
        }
    }

    @Override
    public int getPuntiTotali() {
        // Restituisce il totale dei punti accumulati fino a questo momento
        return puntiTotali;
    }
}

