package com.example.loyaltyPlatformSicuro.programmaPunti;

import com.example.loyaltyPlatformSicuro.utenti.model.Azienda;
import com.example.loyaltyPlatformSicuro.utenti.model.Tessera;
import jakarta.persistence.*;

/**
 * Questa classe rappresenta l'entità per la gestione dei punti accumulati da una tessera di un cliente
 * in relazione a un'azienda specifica all'interno del programma Fedelta.
 */
@Entity
public class PuntiPerAzienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tessera tessera;

    @ManyToOne
    private Azienda azienda;

    private int punti;

    /**
     * Costruttore per creare un'istanza di PuntiPerAzienda.
     *
     * @param tessera La tessera associata ai punti.
     * @param azienda L'azienda per cui sono accumulati i punti.
     * @param punti   Il numero di punti accumulati.
     */
    public PuntiPerAzienda(Tessera tessera, Azienda azienda, int punti) {
        this.tessera = tessera;
        this.azienda = azienda;
        this.punti = punti;
    }

    /**
     * Costruttore vuoto di default.
     */
    public PuntiPerAzienda() {
    }

    /**
     * Ottiene l'ID dei punti per azienda.
     *
     * @return L'ID dei punti per azienda.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID dei punti per azienda.
     *
     * @param id L'ID da impostare.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Ottiene la tessera associata ai punti.
     *
     * @return La tessera associata ai punti.
     */
    public Tessera getTessera() {
        return tessera;
    }

    /**
     * Imposta la tessera associata ai punti.
     *
     * @param tessera La tessera da impostare.
     */
    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    /**
     * Ottiene l'azienda associata ai punti.
     *
     * @return L'azienda associata ai punti.
     */
    public Azienda getAzienda() {
        return azienda;
    }

    /**
     * Imposta l'azienda associata ai punti.
     *
     * @param azienda L'azienda da impostare.
     */
    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    /**
     * Ottiene il numero di punti accumulati.
     *
     * @return Il numero di punti accumulati.
     */
    public int getPunti() {
        return punti;
    }

    /**
     * Imposta il numero di punti accumulati.
     *
     * @param punti Il numero di punti da impostare.
     */
    public void setPunti(int punti) {
        this.punti = punti;
    }

    // Altri metodi Getter e Setter...
}
