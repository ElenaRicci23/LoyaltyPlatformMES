package com.example.pf.cliente;

import jakarta.persistence.*;



/**
 * Rappresenta una tessera di un cliente e tiene traccia dei punti accumulati con le aziende.
 */
@Entity
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Aggiungi una referenza al cliente proprietario
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id") // Questo è importante per la relazione
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tessera(Cliente cliente) {  }

    public Long getId() { return id;  }


    public void setId(Long id) {  this.id = id; }


}