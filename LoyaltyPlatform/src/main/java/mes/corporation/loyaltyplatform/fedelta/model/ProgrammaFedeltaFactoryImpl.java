package mes.corporation.loyaltyplatform.fedelta.model;

import org.springframework.stereotype.Service;


/**
 * Questa classe è un'implementazione di {@link ProgrammaFedeltaFactory} che fornisce
 * la creazione di diverse implementazioni di programmi fedeltà in base al tipo specificato.
 */
@Service
public class ProgrammaFedeltaFactoryImpl implements ProgrammaFedeltaFactory {

    /**
     * Crea un'istanza di {@link ProgrammaFedeltà} in base al tipo specificato.
     *
     * @param tipoProgramma Il tipo di programma fedeltà desiderato.
     * @return Un'istanza di {@link ProgrammaFedeltà} specifica per il tipo fornito.
     * @throws IllegalArgumentException Se il tipo di programma fedeltà specificato non è supportato.
     */
    @Override
    public ProgrammaFedeltà creaProgrammaFedelta(TipoProgrammaFedelta tipoProgramma) {
        // Qui dovresti implementare la logica per creare l'implementazione corretta
        // del programma fedeltà in base al tipo specificato (tipoProgramma).

        // Esempio di implementazione:
        if (tipoProgramma == TipoProgrammaFedelta.PUNTI) {
            return new ProgrammaFedeltà();
        } else {
            throw new IllegalArgumentException("Tipo di programma fedeltà non supportato: " + tipoProgramma);
        }
    }
}
