package com.example.pf.cliente;

import jakarta.persistence.*;

@Entity
public class Acquisto {
    @Id
    @GeneratedValue
    private Long id;

    private double importo;  // L'importo dell'acquisto

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

