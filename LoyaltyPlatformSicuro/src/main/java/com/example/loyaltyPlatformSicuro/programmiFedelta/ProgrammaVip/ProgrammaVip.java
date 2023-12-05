package com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaVip;

import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class ProgrammaVip extends ProgrammaFedelta {
    @Id
    @GeneratedValue
    Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}

