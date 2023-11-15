package mes.corporation.loyaltyplatform.fedelta;

import org.springframework.stereotype.Service;


@Service
public class ProgrammaFedeltaFactoryImpl implements ProgrammaFedeltaFactory {

    @Override
    public ProgrammaFedelta creaProgrammaFedelta(TipoProgrammaFedelta tipoProgramma) {
        // Qui dovresti implementare la logica per creare l'implementazione corretta
        // del programma fedeltà in base al tipo specificato (tipoProgramma).

        // Esempio di implementazione:
        if (tipoProgramma == TipoProgrammaFedelta.PUNTI) {
            return new ProgrammaFedeltaPunti();
        } else {
            throw new IllegalArgumentException("Tipo di programma fedeltà non supportato: " + tipoProgramma);
        }
    }
}
