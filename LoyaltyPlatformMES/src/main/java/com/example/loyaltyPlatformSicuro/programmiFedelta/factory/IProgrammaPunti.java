package com.example.loyaltyPlatformSicuro.programmiFedelta.factory;



import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.Configurazione.Premio;

import java.util.List;

public interface IProgrammaPunti {
    double getTassoConversione();
    void setTassoConversione(double tassoConversione);

    List<Premio> getPremi();
    void aggiungiPremio(Premio premio);
    void rimuoviPremio(Premio premio);

    void modificaPremio(Premio premio, String nuovoNome, int nuoviPunti, String nuovaDescrizione);
}

