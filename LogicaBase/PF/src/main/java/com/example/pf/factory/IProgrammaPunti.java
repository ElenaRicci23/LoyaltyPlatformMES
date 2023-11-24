package com.example.pf.factory;

import com.example.pf.cliente.Acquisto;

public interface IProgrammaPunti {

    void accumulaPunti(int puntiDaAggiungere, Acquisto acquisto);
    int getPuntiTotali();



}
