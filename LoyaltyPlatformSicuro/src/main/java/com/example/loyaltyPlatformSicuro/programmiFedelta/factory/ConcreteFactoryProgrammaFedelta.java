package com.example.loyaltyPlatformSicuro.programmiFedelta.factory;


import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaLivello.ProgrammaLivello;
import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaMembership.ProgrammaMembership;
import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaVip.ProgrammaVip;
import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaCashBack.ProgrammaCashback;
import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.ProgrammaPunti;
import com.example.loyaltyPlatformSicuro.programmiFedelta.repo.ProgrammaFedeltaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class ConcreteFactoryProgrammaFedelta implements FactoryProgrammaFedelta {

    @Autowired
    private ProgrammaFedeltaRepository programmaFedeltaRepository;

    @Override
    public ProgrammaFedelta creaProgrammaPunti() {
        // Qui crei e restituisci un'istanza di ProgrammaPunti
        return new ProgrammaPunti(); // Assumi che tu abbia un costruttore senza argomenti
    }

    @Override
    public ProgrammaFedelta creaProgrammaCashback() {
        // Qui crei e restituisci un'istanza di ProgrammaCashback
        return new ProgrammaCashback(); // Assumi che tu abbia un costruttore senza argomenti
    }

    @Override
    public ProgrammaFedelta creaProgrammiVip() {
        // Qui crei e restituisci un'istanza di ProgrammaVip
        return new ProgrammaVip(); // Assumi che tu abbia un costruttore senza argomenti
    }

    @Override
    public ProgrammaFedelta creaProgrammiLivello() {
        // Qui crei e restituisci un'istanza di ProgrammaLivello
        return new ProgrammaLivello(); // Assumi che tu abbia un costruttore senza argomenti
    }

    @Override
    public ProgrammaFedelta creaProgrammiMembership() {
        // Qui crei e restituisci un'istanza di ProgrammaMembership
        return new ProgrammaMembership(); // Assumi che tu abbia un costruttore senza argomenti
    }

    @Override
    public List<ProgrammaFedelta> getProgrammiFedeltaByAzienda(Long aziendaId) {
        // Implementazione per recuperare i programmi di fedeltà di un'azienda
        return programmaFedeltaRepository.findByAziendaId(aziendaId);
    }

}
