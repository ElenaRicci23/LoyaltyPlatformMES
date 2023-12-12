package com.example.loyaltyPlatformSicuro.security.auth;

import jakarta.persistence.*;

import java.util.Collection;
/**
 * Questa classe rappresenta un ruolo nel sistema.
 */
@Entity
@Table(name = "ruoli") // Specifica il nome della tabella nel database
public class Ruolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // Specifica che il campo "nome" è unico e non può essere nullo
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ruoli_privilegi",
            joinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilegio_id", referencedColumnName = "id")
    )
    private Collection<Privilegio> privileges;

    /**
     * Costruttore vuoto per la classe Ruolo.
     */
    public Ruolo() {
    }

    /**
     * Costruttore con parametri per la classe Ruolo.
     *
     * @param nome Il nome del ruolo.
     */
    public Ruolo(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce l'ID del ruolo.
     *
     * @return L'ID del ruolo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID del ruolo.
     *
     * @param id L'ID del ruolo da impostare.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce la collezione di privilegi associati al ruolo.
     *
     * @return La collezione di privilegi associati al ruolo.
     */
    public Collection<Privilegio> getPrivileges() {
        return privileges;
    }

    /**
     * Imposta la collezione di privilegi associati al ruolo.
     *
     * @param privileges La collezione di privilegi da associare al ruolo.
     */
    public void setPrivileges(Collection<Privilegio> privileges) {
        this.privileges = privileges;
    }

    /**
     * Restituisce il nome del ruolo.
     *
     * @return Il nome del ruolo.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del ruolo.
     *
     * @param nome Il nome del ruolo da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
