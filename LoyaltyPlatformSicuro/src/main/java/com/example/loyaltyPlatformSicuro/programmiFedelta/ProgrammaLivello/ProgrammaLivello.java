package com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaLivello;


import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProgrammaLivello extends ProgrammaFedelta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}