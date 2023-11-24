package com.example.pf.cliente;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Transazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double importo;  // Usa Double invece di double
    private Date dataAcquisto;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
// Costruttori, getter, setter, ecc.
}

