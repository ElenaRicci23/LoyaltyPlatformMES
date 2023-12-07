package com.example.loyaltyPlatformSicuro.utenti.azienda;

;
import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.utenti.Utente;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;



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

    public Azienda() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public DatiPersonaliAzienda getDatiPersonali() {
        return datiPersonali;
    }

    public void setDatiPersonali(DatiPersonaliAzienda datiPersonali) {
        this.datiPersonali = datiPersonali;
    }

    public Set<ProgrammaFedelta> getProgrammiFedelta() {
        return programmiFedelta;
    }

    public void addProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        programmiFedelta.add(programmaFedelta);
        programmaFedelta.setAzienda(this);
    }

    public void removeProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        programmiFedelta.remove(programmaFedelta);
        programmaFedelta.setAzienda(null);
    }

    public Azienda(String email, String password, DatiPersonaliAzienda datiPersonali) {
        super(email, password);
        this.datiPersonali = datiPersonali;
    }

    public void setProgrammiFedelta(Set<ProgrammaFedelta> programmiFedelta) {
        this.programmiFedelta = programmiFedelta;
    }
}
