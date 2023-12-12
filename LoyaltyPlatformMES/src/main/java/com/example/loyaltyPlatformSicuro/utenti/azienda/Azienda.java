package com.example.loyaltyPlatformSicuro.utenti.azienda;

import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.utenti.Utente;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**
 * Questa classe rappresenta un'azienda all'interno del sistema.
 */
@Entity
@Table(name = "azienda") // Specifica il nome della tabella
public class Azienda extends Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "ruolo")
    private String ruolo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dati_personali_id") // Specifica il nome della colonna di join
    private DatiPersonaliAzienda datiPersonali = new DatiPersonaliAzienda();

    @OneToMany(mappedBy = "azienda", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProgrammaFedelta> programmiFedelta = new HashSet<>();

    /**
     * Costruttore vuoto per la classe Azienda.
     */
    public Azienda() {
    }

    /**
     * Restituisce l'ID dell'azienda.
     *
     * @return L'ID dell'azienda.
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID dell'azienda.
     *
     * @param id L'ID dell'azienda da impostare.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il ruolo dell'azienda.
     *
     * @return Il ruolo dell'azienda.
     */
    public String getRuolo() {
        return ruolo;
    }

    /**
     * Imposta il ruolo dell'azienda.
     *
     * @param ruolo Il ruolo dell'azienda da impostare.
     */
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    /**
     * Restituisce i dati personali dell'azienda.
     *
     * @return I dati personali dell'azienda.
     */
    public DatiPersonaliAzienda getDatiPersonali() {
        return datiPersonali;
    }

    /**
     * Imposta i dati personali dell'azienda.
     *
     * @param datiPersonali I dati personali dell'azienda da impostare.
     */
    public void setDatiPersonali(DatiPersonaliAzienda datiPersonali) {
        this.datiPersonali = datiPersonali;
    }

    /**
     * Restituisce l'insieme di programmi fedeltà associati all'azienda.
     *
     * @return L'insieme di programmi fedeltà associati all'azienda.
     */
    public Set<ProgrammaFedelta> getProgrammiFedelta() {
        return programmiFedelta;
    }

    /**
     * Aggiunge un programma fedeltà all'azienda.
     *
     * @param programmaFedelta Il programma fedeltà da aggiungere.
     */
    public void addProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        programmiFedelta.add(programmaFedelta);
        programmaFedelta.setAzienda(this);
    }

    /**
     * Rimuove un programma fedeltà dall'azienda.
     *
     * @param programmaFedelta Il programma fedeltà da rimuovere.
     */
    public void removeProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        programmiFedelta.remove(programmaFedelta);
        programmaFedelta.setAzienda(null);
    }

    /**
     * Costruttore per la classe Azienda con parametri.
     *
     * @param email           L'email dell'azienda.
     * @param password        La password dell'azienda.
     * @param datiPersonali   I dati personali dell'azienda.
     */
    public Azienda(String email, String password, DatiPersonaliAzienda datiPersonali) {
        super(email, password);
        this.datiPersonali = datiPersonali;
    }

    /**
     * Imposta l'insieme di programmi fedeltà associati all'azienda.
     *
     * @param programmiFedelta L'insieme di programmi fedeltà da impostare.
     */
    public void setProgrammiFedelta(Set<ProgrammaFedelta> programmiFedelta) {
        this.programmiFedelta = programmiFedelta;
    }
}
