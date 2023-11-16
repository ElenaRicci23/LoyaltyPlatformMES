package mes.corporation.loyaltyplatform.fedelta.model;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.model.Cliente;

import java.math.BigDecimal;


/**
 * Questa classe rappresenta un'implementazione di {@link ProgrammaFedelta} basata su punti
 * per il programma fedeltà.
 */
@Entity
public class ProgrammaFedeltaAzienda implements ProgrammaFedelta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "azienda_id")
    private Azienda azienda;

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
    @Override
    public void registraCliente(Cliente cliente) {
        // Implementa la registrazione del cliente per il programma fedeltà basato su punti
    }


}

