package com.example.loyaltyPlatformSicuro.fedelta.model;

import com.example.loyaltyPlatformSicuro.utenti.model.Azienda;
import com.example.loyaltyPlatformSicuro.utenti.model.Cliente;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Questa classe rappresenta un'entità per la registrazione di transazioni all'interno del programma Fedelta.
 */
@Entity
public class Transazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "azienda_id")
    private Azienda azienda;

    @ManyToOne
    @JoinColumn(name = "programma_fedelta_id")
    private ProgrammaFedelta programmaFedelta;

    private BigDecimal importoSpeso;
    private LocalDateTime dataTransazione;

    /**
     * Ottiene l'ID della transazione.
     *
     * @return L'ID della transazione.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID della transazione.
     *
     * @param id L'ID da impostare.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Ottiene il cliente associato alla transazione.
     *
     * @return Il cliente associato alla transazione.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Imposta il cliente associato alla transazione.
     *
     * @param cliente Il cliente da impostare.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Ottiene l'azienda associata alla transazione.
     *
     * @return L'azienda associata alla transazione.
     */
    public Azienda getAzienda() {
        return azienda;
    }

    /**
     * Imposta l'azienda associata alla transazione.
     *
     * @param azienda L'azienda da impostare.
     */
    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    /**
     * Ottiene il programma Fedelta basato su punti associato alla transazione.
     *
     * @return Il programma Fedelta basato su punti associato alla transazione.
     */
    public ProgrammaFedelta getProgrammaFedelta() {
        return programmaFedelta;
    }

    public void setProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        this.programmaFedelta = programmaFedelta;
    }

    /**
     * Ottiene l'importo speso nella transazione.
     *
     * @return L'importo speso nella transazione.
     */
    public BigDecimal getImportoSpeso() {
        return importoSpeso;
    }

    /**
     * Imposta l'importo speso nella transazione.
     *
     * @param importoSpeso L'importo da impostare.
     */
    public void setImportoSpeso(BigDecimal importoSpeso) {
        this.importoSpeso = importoSpeso;
    }

    /**
     * Ottiene la data della transazione.
     *
     * @return La data della transazione.
     */
    public LocalDateTime getDataTransazione() {
        return dataTransazione;
    }

    /**
     * Imposta la data della transazione.
     *
     * @param dataTransazione La data da impostare.
     */
    public void setDataTransazione(LocalDateTime dataTransazione) {
        this.dataTransazione = dataTransazione;
    }

    // Altri campi e metodi necessari...
}
