package com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaMembership;


import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class ProgrammaMembership extends ProgrammaFedelta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

