package com.example.loyaltyPlatformSicuro.utenti.cliente.tessera;

import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class StoricoPunti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tessera_id")
    private Tessera tessera;

    @ManyToOne
    @JoinColumn(name = "programma_fedelta_id")
    private ProgrammaFedelta programmaFedelta;

    private int puntiGuadagnati;
    private int puntiUtilizzati;
    private int operazione;
    private int punti;

    private LocalDateTime dataOperazione;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    public ProgrammaFedelta getProgrammaFedelta() {
        return programmaFedelta;
    }

    public void setProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        this.programmaFedelta = programmaFedelta;
    }

    public int getPuntiGuadagnati() {
        return puntiGuadagnati;
    }

    public void setPuntiGuadagnati(int puntiGuadagnati) {
        this.puntiGuadagnati = puntiGuadagnati;
    }

    public int getPuntiUtilizzati() {
        return puntiUtilizzati;
    }

    public void setPuntiUtilizzati(int puntiUtilizzati) {
        this.puntiUtilizzati = puntiUtilizzati;
    }

    public LocalDateTime getDataOperazione() {
        return dataOperazione;
    }

    public void setDataOperazione(LocalDateTime dataOperazione) {
        this.dataOperazione = dataOperazione;
    }

    public int getOperazione() {
        return operazione;
    }

    public void setOperazione(int operazione) {
        this.operazione = operazione;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }


}
