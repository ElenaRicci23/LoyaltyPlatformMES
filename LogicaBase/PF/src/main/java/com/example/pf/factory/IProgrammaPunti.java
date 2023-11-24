package com.example.pf.factory;

import com.example.pf.azienda.Premio;
import com.example.pf.cliente.Cliente;
import com.example.pf.cliente.Transazione;

import java.util.List;

public interface IProgrammaPunti {
    // Accumula punti in base all'acquisto effettuato
    void accumulaPunti(Transazione transazione);

    // Ottieni il totale dei punti accumulati fino a questo momento
    int getPuntiTotali();

    // Aggiungi un premio al programma fedeltà
    void aggiungiPremio(Premio premio);

    // Modifica un premio esistente nel programma fedeltà
    void modificaPremio(int idPremio, Premio premio);

    // Rimuovi un premio dal programma fedeltà
    void rimuoviPremio(int idPremio);

    // Ottieni la lista dei premi disponibili nel programma fedeltà
    List<Premio> getPremiDisponibili();

    // Imposta il tasso di conversione da denaro a punti
    void impostaTassoConversione(double tasso);

    // Imposta i punti bonus da assegnare ai clienti quando raggiungono obiettivi specifici
    void impostaPuntiBonus(int puntiBonus);

    // Aggiungi un nuovo premio personalizzato al programma fedeltà
    void aggiungiPremioPersonalizzato(Premio premio);

    // Modifica un premio personalizzato esistente nel programma fedeltà
    void modificaPremioPersonalizzato(int idPremio, Premio premio);

    // Rimuovi un premio personalizzato dal programma fedeltà
    void rimuoviPremioPersonalizzato(int idPremio);

    // Personalizza i messaggi e le comunicazioni inviate ai clienti
    void personalizzaMessaggiComunicazioni(String messaggioPersonalizzato);

    // Offri ricompense speciali in determinate circostanze
    void offriRicompenseSpeciali(String occasioneSpeciale);

    // Gestisci eccezioni o errori specifici del programma fedeltà
    void gestisciEccezioni(String eccezione);

    // Registra attività dei clienti, come transazioni o eventi speciali
    void registraAttivitaCliente(Cliente cliente, String attivita);

    // Genera statistiche o report sul programma fedeltà
    String generaStatisticheReport();

    // Configura parametri avanzati come limiti massimi o minimi
    void configuraParametriAvanzati(String parametro, String valore);
}
