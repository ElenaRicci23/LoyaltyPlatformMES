package com.example.loyaltyPlatformSicuro.programmiFedelta.factory;


import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;

import java.util.List;

public interface FactoryProgrammaFedelta {
    ProgrammaFedelta creaProgrammaPunti();
    ProgrammaFedelta creaProgrammaCashback();

    ProgrammaFedelta creaProgrammiVip();

    ProgrammaFedelta creaProgrammiLivello();

    ProgrammaFedelta creaProgrammiMembership();

    List<ProgrammaFedelta> getProgrammiFedeltaByAzienda(Long aziendaId);


}


