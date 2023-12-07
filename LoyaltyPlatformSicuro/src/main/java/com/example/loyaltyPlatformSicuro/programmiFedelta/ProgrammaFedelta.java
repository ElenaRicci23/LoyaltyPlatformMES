package com.example.loyaltyPlatformSicuro.programmiFedelta;

import com.example.loyaltyPlatformSicuro.utenti.azienda.Azienda;
import jakarta.persistence.*;


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

    // Costruttore vuoto
    public ProgrammaFedelta() {
    }

    // Costruttore con parametri
    public ProgrammaFedelta(Long id, String nome, String descrizione, TipoProgrammaFedelta tipoProgrammaFedelta) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipoProgrammaFedelta = tipoProgrammaFedelta;
    }

    // Getter e setter per gli attributi della classe

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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TipoProgrammaFedelta getTipoProgrammaFedelta() {
        return tipoProgrammaFedelta;
    }

    public void setTipoProgrammaFedelta(TipoProgrammaFedelta tipoProgrammaFedelta) {
        this.tipoProgrammaFedelta = tipoProgrammaFedelta;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }
}
