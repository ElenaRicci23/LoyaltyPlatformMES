package com.example.pf.factory;

// Interfaccia Factory
public interface ProgrammaPuntiFactory {
    IProgrammaPunti creaProgrammaPunti(String nome, String descrizione);
}
