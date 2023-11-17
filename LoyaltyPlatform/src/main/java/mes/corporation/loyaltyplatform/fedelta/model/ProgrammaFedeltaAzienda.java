package mes.corporation.loyaltyplatform.fedelta.model;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.model.Cliente;

import java.math.BigDecimal;


/**
 * Questa classe rappresenta un'implementazione di ProgrammaFedeltà basata su punti
 * per il programma fedeltà.
 */
@Entity
public class ProgrammaFedeltaAzienda extends ProgrammaFedeltà {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;

    // Tipo di programma fedeltà associato all'azienda
    @Enumerated(EnumType.STRING)
    private TipoProgrammaFedelta tipoProgrammaFedelta;

    @ManyToOne
    @JoinColumn(name = "azienda_id")
    private Azienda azienda;

    public ProgrammaFedeltaAzienda(String nome, String descrizione, TipoProgrammaFedelta tipoProgrammaFedelta, Azienda azienda) {
        super(nome, descrizione, tipoProgrammaFedelta);
        this.azienda = azienda;
    }

    public ProgrammaFedeltaAzienda() {
        super();
    }

    /**
     * Ottiene l'ID dell'implementazione del programma fedeltà.
     *
     * @return L'ID dell'implementazione.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID dell'implementazione del programma fedeltà.
     *
     * @param id L'ID da impostare.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Ottiene il nome del programma fedeltà.
     *
     * @return Il nome del programma fedeltà.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del programma fedeltà.
     *
     * @param nome Il nome da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Ottiene la descrizione del programma fedeltà.
     *
     * @return La descrizione del programma fedeltà.
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta la descrizione del programma fedeltà.
     *
     * @param descrizione La descrizione da impostare.
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Ottiene l'azienda associata al programma fedeltà.
     *
     * @return L'azienda associata al programma fedeltà.
     */
    public Azienda getAzienda() {
        return azienda;
    }

    /**
     * Imposta l'azienda associata al programma fedeltà.
     *
     * @param azienda L'azienda da impostare.
     */
    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    /**
     * Registra un cliente nel programma fedeltà basato su punti.
     *
     * @param cliente Il cliente da registrare.
     */
    public void registraCliente(Cliente cliente) {
        // Implementa la registrazione del cliente per il programma fedeltà basato su punti
    }


    /**
     * Restituisce il tipo di programma fedeltà associato all'azienda.
     *
     * @return Il tipo di programma fedeltà.
     */
    public TipoProgrammaFedelta getTipoProgrammaFedelta() {
        return tipoProgrammaFedelta;
    }

    /**
     * Imposta il tipo di programma fedeltà associato all'azienda.
     *
     * @param tipoProgrammaFedelta Il tipo di programma fedeltà.
     */
    public void setTipoProgrammaFedelta(TipoProgrammaFedelta tipoProgrammaFedelta) {
        this.tipoProgrammaFedelta = tipoProgrammaFedelta;
    }


}

