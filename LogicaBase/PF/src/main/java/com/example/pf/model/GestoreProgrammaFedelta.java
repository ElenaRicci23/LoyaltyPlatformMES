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

    public IProgrammaPunti creaProgrammaPunti(String nome, String descrizione) {
        return factory.creaProgrammaPunti(nome, descrizione);
    }

}

