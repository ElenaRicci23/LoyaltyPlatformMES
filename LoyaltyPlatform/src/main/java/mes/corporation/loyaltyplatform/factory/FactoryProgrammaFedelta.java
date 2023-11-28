package mes.corporation.loyaltyplatform.factory;

import mes.corporation.loyaltyplatform.model.ProgrammaFedelta;

public interface FactoryProgrammaFedelta {
    ProgrammaFedelta creaProgrammaPunti();
    ProgrammaFedelta creaProgrammaCashback();

    ProgrammaFedelta creaProgrammiVip();

    ProgrammaFedelta creaProgrammiLivello();

    ProgrammaFedelta creaProgrammiMembership();

}


