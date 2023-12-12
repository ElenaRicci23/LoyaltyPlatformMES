package com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.Configurazione;

import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.ProgrammaPunti;
import jakarta.persistence.*;
/**
 * Questa classe rappresenta un premio associato a un programma punti.
 */
@Entity
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "punti_del_premio")
    private int puntiDelPremio;

    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "programma_punti_id")
    private ProgrammaPunti programmaPunti;

    /**
     * Restituisce l'ID del premio.
     *
     * @return L'ID del premio.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID del premio.
     *
     * @param id L'ID del premio da impostare.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il nome del premio.
     *
     * @return Il nome del premio.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del premio.
     *
     * @param nome Il nome del premio da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il numero di punti necessari per ottenere il premio.
     *
     * @return Il numero di punti del premio.
     */
    public int getPuntiDelPremio() {
        return puntiDelPremio;
    }

    /**
     * Imposta il numero di punti necessari per ottenere il premio.
     *
     * @param puntiDelPremio Il numero di punti del premio da impostare.
     */
    public void setPuntiDelPremio(int puntiDelPremio) {
        this.puntiDelPremio = puntiDelPremio;
    }

    /**
     * Restituisce la descrizione del premio.
     *
     * @return La descrizione del premio.
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta la descrizione del premio.
     *
     * @param descrizione La descrizione del premio da impostare.
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Restituisce il programma punti a cui è associato il premio.
     *
     * @return Il programma punti associato al premio.
     */
    public ProgrammaPunti getProgrammaPunti() {
        return programmaPunti;
    }

    /**
     * Imposta il programma punti a cui associare il premio.
     *
     * @param programmaPunti Il programma punti da associare al premio.
     */
    public void setProgrammaPunti(ProgrammaPunti programmaPunti) {
        this.programmaPunti = programmaPunti;
    }
}
