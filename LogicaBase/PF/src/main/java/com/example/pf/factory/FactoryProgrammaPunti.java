package com.example.pf.factory;
import org.springframework.stereotype.Component;



@Component
public class FactoryProgrammaPunti implements ProgrammaPuntiFactory {
    public ProgrammaPunti creaProgrammaPunti(String nome, String descrizione) {
        return new ProgrammaPunti(nome, descrizione);
    }

}

