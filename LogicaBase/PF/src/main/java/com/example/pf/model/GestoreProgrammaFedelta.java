package com.example.pf.model;

import com.example.pf.factory.FactoryProgrammaPunti;
import com.example.pf.factory.IProgrammaPunti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GestoreProgrammaFedelta {

    private final FactoryProgrammaPunti factory;

    @Autowired
    public GestoreProgrammaFedelta(FactoryProgrammaPunti factory) {
        this.factory = factory;
    }

    public IProgrammaPunti creaProgrammaPunti(String nome, String descrizione, TipoProgrammaFedelta tipoProgrammaFedelta) {
        if (tipoProgrammaFedelta == TipoProgrammaFedelta.PUNTI) {
            return factory.creaProgrammaPunti(nome, descrizione);
        } else {
            // Gestisci il caso in cui la tipologia non è valida (ad esempio, se non è "PUNTI")
            System.out.println("Non gestiamo per ora altri tipi di programmi WORK IN PRORESS!");
            return null;
        }
    }

}

