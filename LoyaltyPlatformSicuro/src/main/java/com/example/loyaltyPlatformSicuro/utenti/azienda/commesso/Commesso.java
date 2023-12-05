package com.example.loyaltyPlatformSicuro.utenti.azienda.commesso;


import com.example.loyaltyPlatformSicuro.utenti.azienda.Azienda;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Commesso extends Azienda {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String ruolo;

    public Commesso() {
        // Costruttore vuoto
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getRuolo() {
        return ruolo;
    }

    @Override
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    // Altri metodi specifici per il Commesso, se necessario
}

