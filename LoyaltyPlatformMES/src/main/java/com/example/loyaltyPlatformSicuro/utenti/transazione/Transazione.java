package com.example.loyaltyPlatformSicuro.utenti.transazione;

import com.example.loyaltyPlatformSicuro.utenti.cliente.Cliente;
import jakarta.persistence.*;

import java.time.LocalDate;
/**
 * Entità che rappresenta una transazione nel sistema.
 */
@Entity
@Table(name = "transazioni") // Specifica il nome della tabella nel database
public class Transazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_transazione")
    private LocalDate dataTransazione;

    private double importo;

    @Column(name = "nome_azienda")
    private String nomeAzienda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Getter e Setter per gli attributi

    /**
     * Restituisce il cliente associato a questa transazione.
     *
     * @return Il cliente associato alla transazione.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Imposta il cliente associato a questa transazione.
     *
     * @param cliente Il cliente da associare alla transazione.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Restituisce l'ID della transazione.
     *
     * @return L'ID della transazione.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID della transazione.
     *
     * @param id L'ID della transazione da impostare.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce la data della transazione.
     *
     * @return La data della transazione.
     */
    public LocalDate getDataTransazione() {
        return dataTransazione;
    }

    /**
     * Imposta la data della transazione.
     *
     * @param dataTransazione La data della transazione da impostare.
     */
    public void setDataTransazione(LocalDate dataTransazione) {
        this.dataTransazione = dataTransazione;
    }

    /**
     * Restituisce l'importo della transazione.
     *
     * @return L'importo della transazione.
     */
    public double getImporto() {
        return importo;
    }

    /**
     * Imposta l'importo della transazione.
     *
     * @param importo L'importo della transazione da impostare.
     */
    public void setImporto(double importo) {
        this.importo = importo;
    }

    /**
     * Restituisce il nome dell'azienda associata a questa transazione.
     *
     * @return Il nome dell'azienda.
     */
    public String getNomeAzienda() {
        return nomeAzienda;
    }

    /**
     * Imposta il nome dell'azienda associata a questa transazione.
     *
     * @param nomeAzienda Il nome dell'azienda da impostare.
     */
    public void setNomeAzienda(String nomeAzienda) {
        this.nomeAzienda = nomeAzienda;
    }
}
