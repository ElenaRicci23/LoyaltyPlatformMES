package com.example.loyaltyPlatformSicuro.security.auth;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Ruolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ruoli_privilegi",
            joinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilegio_id", referencedColumnName = "id")
    )
    private Collection<Privilegio> privileges;


    // Costruttori, getter e setter

    public Ruolo() {
    }

    public Ruolo(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Collection<Privilegio> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilegio> privileges) {
        this.privileges = privileges;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
