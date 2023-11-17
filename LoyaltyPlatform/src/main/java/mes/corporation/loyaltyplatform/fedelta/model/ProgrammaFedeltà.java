package mes.corporation.loyaltyplatform.fedelta.model;

import jakarta.persistence.*;

/**
 * Questa classe rappresenta un programma fedeltà generico.
 * Può essere estesa per creare implementazioni specifiche di programmi fedeltà.
 */
public class ProgrammaFedeltà {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;

    @Enumerated(EnumType.STRING)
    private TipoProgrammaFedelta tipoProgramma;

    /**
     * Costruttore per creare un'istanza di ProgrammaFedeltà.
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

}
