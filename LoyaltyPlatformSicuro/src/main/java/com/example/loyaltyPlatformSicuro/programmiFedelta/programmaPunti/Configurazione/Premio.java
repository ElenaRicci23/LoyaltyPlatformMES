package com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.Configurazione;

import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.ProgrammaPunti;
import jakarta.persistence.*;


@Entity
@Table(name = "premi")
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_premio")
    private String nome;

    @Column(name = "punti_del_premio")
    private int puntiDelPremio;

    @Column(name = "descrizione_premio")
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "programma_punti_id")
    private ProgrammaPunti programmaPunti;

    // Costruttori, getter e setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPuntiDelPremio() {
        return puntiDelPremio;
    }

    public void setPuntiDelPremio(int puntiDelPremio) {
        this.puntiDelPremio = puntiDelPremio;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public ProgrammaPunti getProgrammaPunti() {
        return programmaPunti;
    }

    public void setProgrammaPunti(ProgrammaPunti programmaPunti) {
        this.programmaPunti = programmaPunti;
    }
}
