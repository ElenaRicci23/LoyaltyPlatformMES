package com.example.loyaltyPlatformSicuro.utenti.azienda.commesso;


import com.example.loyaltyPlatformSicuro.utenti.azienda.Azienda;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
/**
 * Questa classe rappresenta un commesso all'interno del sistema.
 */
@Entity
public class Commesso extends Azienda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String ruolo;

    /**
     * Costruttore vuoto per la classe Commesso.
     */
    public Commesso() {
        // Costruttore vuoto
    }

    /**
     * Restituisce l'ID del commesso.
     *
     * @return L'ID del commesso.
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID del commesso.
     *
     * @param id L'ID del commesso da impostare.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il ruolo del commesso.
     *
     * @return Il ruolo del commesso.
     */
    @Override
    public String getRuolo() {
        return ruolo;
    }

    /**
     * Imposta il ruolo del commesso.
     *
     * @param ruolo Il ruolo del commesso da impostare.
     */
    @Override
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}

