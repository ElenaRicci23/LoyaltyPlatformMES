package com.example.pf.cliente;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Cliente {
    @Id
    @GeneratedValue
    private Long id;




    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
