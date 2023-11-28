package mes.corporation.loyaltyplatform.factory;


import mes.corporation.loyaltyplatform.model.ProgrammaFedelta;
import mes.corporation.loyaltyplatform.model.tipologiaProgrammaFedelta.ProgrammaCashback;
import mes.corporation.loyaltyplatform.model.tipologiaProgrammaFedelta.ProgrammaLivello;
import mes.corporation.loyaltyplatform.model.tipologiaProgrammaFedelta.ProgrammaMembership;
import mes.corporation.loyaltyplatform.model.tipologiaProgrammaFedelta.ProgrammaVip;

public class ConcreteFactoryProgrammaFedelta implements FactoryProgrammaFedelta {

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
}

