package mes.corporation.loyaltyplatform.fedelta.model;

/**
 * Questa interfaccia definisce un factory per creare diverse implementazioni di {@link ProgrammaFedelta}.
 * Utilizza questa interfaccia per ottenere istanze specifiche di programmi fedeltà in base al tipo specificato.
 */
public interface ProgrammaFedeltaFactory {
    /**
     * Crea un'istanza di {@link ProgrammaFedelta} in base al tipo specificato.
     *
     * @param tipoProgramma Il tipo di programma fedeltà desiderato.
     * @return Un'istanza di {@link ProgrammaFedelta} specifica per il tipo fornito.
     */
    ProgrammaFedelta creaProgrammaFedelta(TipoProgrammaFedelta tipoProgramma);
}

