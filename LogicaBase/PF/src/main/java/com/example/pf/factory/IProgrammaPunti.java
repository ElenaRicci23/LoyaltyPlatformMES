package com.example.pf.factory;

import com.example.pf.cliente.Transazione;

public interface IProgrammaPunti {

    void accumulaPunti(int puntiDaAggiungere, Transazione transazione);
    int getPuntiTotali();



}
