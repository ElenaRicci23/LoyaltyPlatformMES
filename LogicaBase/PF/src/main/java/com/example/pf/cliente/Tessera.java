package com.example.pf.cliente;

import com.example.pf.model.ProgrammaFedelta;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


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


    @ManyToMany
    @JoinTable(
            name = "tessera_programma_fedelta",
            joinColumns = @JoinColumn(name = "tessera_id"),
            inverseJoinColumns = @JoinColumn(name = "programma_fedelta_id")
    )
    private Set<ProgrammaFedelta> programmiFedelta = new HashSet<>();



    public void addProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        programmiFedelta.add(programmaFedelta);
    }

    public void removeProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        programmiFedelta.remove(programmaFedelta);
    }



    public Tessera() {

    }


    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente) {this.cliente = cliente; }

    public Tessera(Cliente cliente) {  }

    public Long getId() { return id;  }
    public void setId(Long id) {  this.id = id; }



}