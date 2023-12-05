package com.example.loyaltyPlatformSicuro.utenti;

import jakarta.persistence.*;

/**
 * Questa classe astratta rappresenta un utente all'interno del sistema.
 * Definisce attributi comuni a tutti gli utenti come l'ID, l'email e la password.
 * Le classi concrete estenderanno questa classe per aggiungere ulteriori dettagli specifici.
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    protected String email;

    protected String password;


    public Utente() {
    }


    public Utente(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}