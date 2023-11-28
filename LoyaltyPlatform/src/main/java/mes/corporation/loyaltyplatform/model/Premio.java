package mes.corporation.loyaltyplatform.model;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.factory.ProgrammaPunti;

@Entity
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Questa annotazione genera l'ID automaticamente
    private Long id;

    private String nome;
    private int puntidelpremio;
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "programma_punti_id") // Assicurati che il nome sia coerente con la tua struttura del database
    private ProgrammaPunti programmaPunti;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPuntiDelPremio() {
        return puntidelpremio;
    }

    public void setPuntiDelPremio(int puntidelpremio) {
        this.puntidelpremio = puntidelpremio;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getPuntidelpremio() {
        return puntidelpremio;
    }

    public void setPuntidelpremio(int puntidelpremio) {
        this.puntidelpremio = puntidelpremio;
    }

    public ProgrammaPunti getProgrammaPunti() {
        return programmaPunti;
    }

    public void setProgrammaPunti(ProgrammaPunti programmaPunti) {
        this.programmaPunti = programmaPunti;
    }
}

