package com.example.loyaltyPlatformSicuro.utenti.transazione;

import com.example.loyaltyPlatformSicuro.utenti.cliente.Cliente;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataTransazione;
    private double importo;
    private String nomeAzienda;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataTransazione() {
        return dataTransazione;
    }

    public void setDataTransazione(LocalDate dataTransazione) {
        this.dataTransazione = dataTransazione;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public String getNomeAzienda() {
        return nomeAzienda;
    }

    public void setNomeAzienda(String nomeAzienda) {
        this.nomeAzienda = nomeAzienda;
    }
}
