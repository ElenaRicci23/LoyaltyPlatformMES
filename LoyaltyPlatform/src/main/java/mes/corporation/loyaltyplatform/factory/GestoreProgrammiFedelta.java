package mes.corporation.loyaltyplatform.factory;

import jakarta.persistence.EntityNotFoundException;
import mes.corporation.loyaltyplatform.DTO.ProgrammaFedeltaDTO;
import mes.corporation.loyaltyplatform.DTO.ProgrammaPuntiDTO;
import mes.corporation.loyaltyplatform.model.ProgrammaFedelta;
import mes.corporation.loyaltyplatform.model.TipoProgrammaFedelta;
import mes.corporation.loyaltyplatform.repo.ProgrammaFedeltaRepository;
import mes.corporation.loyaltyplatform.repo.ProgrammaPuntiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GestoreProgrammiFedelta {

    private FactoryProgrammaFedelta factory;
    @Autowired
    private ProgrammaFedeltaRepository programmaFedeltaRepository;
    @Autowired
    private ProgrammaPuntiRepository programmaPuntiRepository;

    public GestoreProgrammiFedelta() {
        this.factory = new ConcreteFactoryProgrammaFedelta();
    }

    public ProgrammaFedelta creaProgrammaFedelta(ProgrammaFedeltaDTO dto) {
        TipoProgrammaFedelta tipo = dto.getTipoProgrammaFedelta();

        return switch (tipo) {
            case PUNTI -> factory.creaProgrammaPunti();
            case CASHBACK -> factory.creaProgrammaCashback();
            case LIVELLI -> factory.creaProgrammiLivello();
            case MEMBERSHIP -> factory.creaProgrammiMembership();
            case VIP -> factory.creaProgrammiVip();
            default -> throw new IllegalArgumentException("Tipo di programma fedeltà non supportato");
        };
    }

    public void salvaProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        // Ora chiama il metodo salvaProgrammaFedelta con l'oggetto programmaFedelta
        programmaFedeltaRepository.save(programmaFedelta);
    }

    public ProgrammaPunti configuraProgrammaPunti(Long aziendaId, ProgrammaPuntiDTO programmaPuntiDTO) {
        // Trova il ProgrammaPunti associato all'aziendaId utilizzando il metodo findByAziendaId
        ProgrammaPunti programmaPunti = programmaPuntiRepository.findByAziendaId(aziendaId);

        if (programmaPunti != null) {
            // Imposta il tasso di conversione per il ProgrammaPunti
            programmaPunti.setTassoConversione(programmaPuntiDTO.getTassoConversione());

            // Salva il ProgrammaPunti con il tasso di conversione aggiornato
            programmaPuntiRepository.save(programmaPunti);

            // Puoi restituire il ProgrammaPunti configurato se necessario
            return programmaPunti;
        } else {
            throw new EntityNotFoundException("ProgrammaPunti non trovato per l'azienda con ID: " + aziendaId);
        }
    }




}




