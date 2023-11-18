package mes.corporation.loyaltyplatform.fedelta.model;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.utenti.model.Azienda;

/**
 * Questa classe rappresenta un programma fedeltà generico.
 * Può essere estesa per creare implementazioni specifiche di programmi fedeltà.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public class ProgrammaFedeltà {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;

    @Enumerated(EnumType.STRING)
    private TipoProgrammaFedelta tipoProgramma;

    @ManyToOne
    @JoinColumn(name = "azienda_id")
    private Azienda azienda;

    /**
     * Costruttore per creare un'istanza di ProgrammaFedeltà generco.
     *
     * @param nome           Il nome del programma fedeltà.
     * @param tipoProgramma  Il tipo di programma fedeltà.
     * @param descrizione         Il valore associato al programma fedeltà.
     */
    public ProgrammaFedeltà(String nome, String descrizione, TipoProgrammaFedelta tipoProgramma) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipoProgramma = tipoProgramma;
    }

    /**
     * Costruttore per creare un'istanza di ProgrammaFedeltà per aziende.
     *
     * @param nome           Il nome del programma fedeltà.
     * @param tipoProgramma  Il tipo di programma fedeltà.
     * @param descrizione         Il valore associato al programma fedeltà.
     */
    public ProgrammaFedeltà(String nome, String descrizione, TipoProgrammaFedelta tipoProgramma, Azienda azienda) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipoProgramma = tipoProgramma;
        this.azienda = azienda;
    }

    public ProgrammaFedeltà() {
    }

    /**
     * Restituisce il nome del programma fedeltà.
     *
     * @return Il nome del programma fedeltà.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce il tipo di programma fedeltà.
     *
     * @return Il tipo di programma fedeltà.
     */
    public TipoProgrammaFedelta getTipoProgramma() {
        return tipoProgramma;
    }

    public Long getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setTipoProgramma(TipoProgrammaFedelta tipoProgramma) {
        this.tipoProgramma = tipoProgramma;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }
}
