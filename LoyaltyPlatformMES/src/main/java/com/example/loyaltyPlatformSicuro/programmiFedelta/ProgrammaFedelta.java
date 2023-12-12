package com.example.loyaltyPlatformSicuro.programmiFedelta;

import com.example.loyaltyPlatformSicuro.utenti.azienda.Azienda;
import jakarta.persistence.*;
/**
 * Questa classe rappresenta un programma di fedeltà generico.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "programma_fedelta")
public class ProgrammaFedelta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_programma_fedelta")
    private TipoProgrammaFedelta tipoProgrammaFedelta;

    @ManyToOne
    @JoinColumn(name = "azienda_id")
    private Azienda azienda;

    /**
     * Costruttore vuoto per la classe ProgrammaFedelta.
     */
    public ProgrammaFedelta() {
    }

    /**
     * Costruttore con parametri per la classe ProgrammaFedelta.
     *
     * @param id                  L'ID del programma di fedeltà.
     * @param nome                Il nome del programma di fedeltà.
     * @param descrizione         La descrizione del programma di fedeltà.
     * @param tipoProgrammaFedelta Il tipo di programma di fedeltà.
     */
    public ProgrammaFedelta(Long id, String nome, String descrizione, TipoProgrammaFedelta tipoProgrammaFedelta) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipoProgrammaFedelta = tipoProgrammaFedelta;
    }

    /**
     * Restituisce l'ID del programma di fedeltà.
     *
     * @return L'ID del programma di fedeltà.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID del programma di fedeltà.
     *
     * @param id L'ID del programma di fedeltà da impostare.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il nome del programma di fedeltà.
     *
     * @return Il nome del programma di fedeltà.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del programma di fedeltà.
     *
     * @param nome Il nome del programma di fedeltà da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce la descrizione del programma di fedeltà.
     *
     * @return La descrizione del programma di fedeltà.
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta la descrizione del programma di fedeltà.
     *
     * @param descrizione La descrizione del programma di fedeltà da impostare.
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Restituisce il tipo di programma di fedeltà.
     *
     * @return Il tipo di programma di fedeltà.
     */
    public TipoProgrammaFedelta getTipoProgrammaFedelta() {
        return tipoProgrammaFedelta;
    }

    /**
     * Imposta il tipo di programma di fedeltà.
     *
     * @param tipoProgrammaFedelta Il tipo di programma di fedeltà da impostare.
     */
    public void setTipoProgrammaFedelta(TipoProgrammaFedelta tipoProgrammaFedelta) {
        this.tipoProgrammaFedelta = tipoProgrammaFedelta;
    }

    /**
     * Restituisce l'azienda a cui è associato il programma di fedeltà.
     *
     * @return L'azienda associata al programma di fedeltà.
     */
    public Azienda getAzienda() {
        return azienda;
    }

    /**
     * Imposta l'azienda associata al programma di fedeltà.
     *
     * @param azienda L'azienda da associare al programma di fedeltà.
     */
    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }
}
