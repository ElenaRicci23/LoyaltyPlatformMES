package com.example.pf.factory;

import com.example.pf.azienda.Premio;
import com.example.pf.cliente.Cliente;
import com.example.pf.cliente.Transazione;
import com.example.pf.model.ProgrammaFedelta;
import com.example.pf.model.TipoProgrammaFedelta;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ProgrammaPunti extends ProgrammaFedelta implements IProgrammaPunti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "programmaPunti", cascade = CascadeType.ALL)
    private List<Premio> premi = new ArrayList<>();

    private double tassoConversione;
    private int puntiBonus;
    private String messaggioPersonalizzato;
    private String occasioneSpeciale;
    private String eccezione;



    // Costruttore
    public ProgrammaPunti(String nome, String descrizione) {
        // Imposta i campi necessari e chiama il costruttore della superclasse
        super(null, nome, descrizione, TipoProgrammaFedelta.PUNTI);
    }

    public ProgrammaPunti() {

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<Premio> getPremi() {
        return premi;
    }

    public void setPremi(List<Premio> premi) {
        this.premi = premi;
    }

    public double getTassoConversione() {
        return tassoConversione;
    }

    public void setTassoConversione(double tassoConversione) {
        this.tassoConversione = tassoConversione;
    }

    public int getPuntiBonus() {
        return puntiBonus;
    }

    public void setPuntiBonus(int puntiBonus) {
        this.puntiBonus = puntiBonus;
    }

    public String getMessaggioPersonalizzato() {
        return messaggioPersonalizzato;
    }

    public void setMessaggioPersonalizzato(String messaggioPersonalizzato) {
        this.messaggioPersonalizzato = messaggioPersonalizzato;
    }

    public String getOccasioneSpeciale() {
        return occasioneSpeciale;
    }

    public void setOccasioneSpeciale(String occasioneSpeciale) {
        this.occasioneSpeciale = occasioneSpeciale;
    }

    public String getEccezione() {
        return eccezione;
    }

    public void setEccezione(String eccezione) {
        this.eccezione = eccezione;
    }

    @Override
    public void accumulaPunti(Transazione transazione) {
        // Implementa la logica per accumulare punti in base all'acquisto effettuato
        // Aggiungi i punti all'oggetto transazione o ad un altro oggetto adatto
    }

    @Override
    public int getPuntiTotali() {
        // Implementa la logica per ottenere il totale dei punti accumulati fino a questo momento
        // Restituisci il valore dei punti accumulati
        return 0; // Valore fittizio, devi implementare questa logica
    }

    @Override
    public void aggiungiPremio(Premio premio) {
        premi.add(premio);
        // Aggiungi l'associazione tra il premio e il programma fedeltà
        premio.setProgrammaPunti(this);
    }

    @Override
    public void modificaPremio(int idPremio, Premio premio) {
        // Implementa la logica per modificare un premio esistente nel programma fedeltà
    }

    @Override
    public void rimuoviPremio(int idPremio) {
        // Implementa la logica per rimuovere un premio dal programma fedeltà
    }

    @Override
    public List<Premio> getPremiDisponibili() {
        // Implementa la logica per ottenere la lista dei premi disponibili nel programma fedeltà
        return premi;
    }

    @Override
    public void impostaTassoConversione(double tasso) {
        this.tassoConversione = tasso;
    }

    @Override
    public void impostaPuntiBonus(int puntiBonus) {
        this.puntiBonus = puntiBonus;
    }

    @Override
    public void aggiungiPremioPersonalizzato(Premio premio) {
        // Implementa la logica per aggiungere un nuovo premio personalizzato al programma fedeltà
    }

    @Override
    public void modificaPremioPersonalizzato(int idPremio, Premio premio) {
        // Implementa la logica per modificare un premio personalizzato esistente nel programma fedeltà
    }

    @Override
    public void rimuoviPremioPersonalizzato(int idPremio) {
        // Implementa la logica per rimuovere un premio personalizzato dal programma fedeltà
    }

    @Override
    public void personalizzaMessaggiComunicazioni(String messaggioPersonalizzato) {
        this.messaggioPersonalizzato = messaggioPersonalizzato;
    }

    @Override
    public void offriRicompenseSpeciali(String occasioneSpeciale) {
        this.occasioneSpeciale = occasioneSpeciale;
    }

    @Override
    public void gestisciEccezioni(String eccezione) {
        this.eccezione = eccezione;
    }

    @Override
    public void registraAttivitaCliente(Cliente cliente, String attivita) {
        // Implementa la logica per registrare l'attività dei clienti, come transazioni o eventi speciali
    }

    @Override
    public String generaStatisticheReport() {
        // Implementa la logica per generare statistiche o report sul programma fedeltà
        // Restituisci una stringa contenente il report
        return null; // Valore fittizio, devi implementare questa logica
    }

    @Override
    public void configuraParametriAvanzati(String parametro, String valore) {
        // Implementa la logica per configurare parametri avanzati
        // Utilizza i parametri parametro e valore per impostare le opzioni desiderate
    }
}




