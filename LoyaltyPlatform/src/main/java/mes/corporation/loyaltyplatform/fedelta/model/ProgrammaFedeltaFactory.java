package mes.corporation.loyaltyplatform.fedelta.model;

/**
 * Questa interfaccia definisce un factory per creare diverse implementazioni di {@link ProgrammaFedeltà}.
 * Utilizza questa interfaccia per ottenere istanze specifiche di programmi fedeltà in base al tipo specificato.
 */
public interface ProgrammaFedeltaFactory {
    /**
     * Crea un'istanza di {@link ProgrammaFedeltà} in base al tipo specificato.
     *
     * @param tipoProgramma Il tipo di programma fedeltà desiderato.
     * @return Un'istanza di {@link ProgrammaFedeltà} specifica per il tipo fornito.
     */
    ProgrammaFedeltà creaProgrammaFedelta(TipoProgrammaFedelta tipoProgramma);
}

