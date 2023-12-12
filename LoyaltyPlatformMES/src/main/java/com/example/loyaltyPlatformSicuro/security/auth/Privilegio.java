package com.example.loyaltyPlatformSicuro.security.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.persistence.*;

/**
 * Questa classe rappresenta un privilegio nel sistema.
 */
@Entity
public class Privilegio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * Costruttore vuoto per la classe Privilegio.
     */
    public Privilegio() {
    }

    /**
     * Costruttore con parametri per la classe Privilegio.
     *
     * @param name Il nome del privilegio.
     */
    public Privilegio(String name) {
        this.name = name;
    }

    /**
     * Restituisce l'ID del privilegio.
     *
     * @return L'ID del privilegio.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID del privilegio.
     *
     * @param id L'ID del privilegio da impostare.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il nome del privilegio.
     *
     * @return Il nome del privilegio.
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome del privilegio.
     *
     * @param name Il nome del privilegio da impostare.
     */
    public void setName(String name) {
        this.name = name;
    }
}



