package com.example.loyaltyPlatformSicuro.programmiFedelta.factory;

import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaLivello.ProgrammaLivello;
import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaMembership.ProgrammaMembership;
import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaVip.ProgrammaVip;
import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaCashBack.ProgrammaCashback;
import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.ProgrammaPunti;
import com.example.loyaltyPlatformSicuro.programmiFedelta.repo.ProgrammaFedeltaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component; // Aggiunto l'import per @Component

import java.util.List;
/**
 * Questa classe è un componente Spring che implementa l'interfaccia FactoryProgrammaFedelta.
 * È responsabile per la creazione di vari tipi di programmi fedeltà e il recupero dei programmi fedeltà
 * associati a una specifica azienda.
 */
@Component
public class ConcreteFactoryProgrammaFedelta implements FactoryProgrammaFedelta {

    private final ProgrammaFedeltaRepository programmaFedeltaRepository;

    @Autowired
    public ConcreteFactoryProgrammaFedelta(ProgrammaFedeltaRepository programmaFedeltaRepository) {
        this.programmaFedeltaRepository = programmaFedeltaRepository;
    }

    @Override
    public ProgrammaFedelta creaProgrammaPunti() {
        return new ProgrammaPunti();
    }

    @Override
    public ProgrammaFedelta creaProgrammaCashback() {
        return new ProgrammaCashback();
    }

    @Override
    public ProgrammaFedelta creaProgrammiVip() {
        return new ProgrammaVip();
    }

    @Override
    public ProgrammaFedelta creaProgrammiLivello() {
        return new ProgrammaLivello();
    }

    @Override
    public ProgrammaFedelta creaProgrammiMembership() {
        return new ProgrammaMembership();
    }

    @Override
    public List<ProgrammaFedelta> getProgrammiFedeltaByAzienda(Long aziendaId) {
        return programmaFedeltaRepository.findByAziendaId(aziendaId);
    }
}
