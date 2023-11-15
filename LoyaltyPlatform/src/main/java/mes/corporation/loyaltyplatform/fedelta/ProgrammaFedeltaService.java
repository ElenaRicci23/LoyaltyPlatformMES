package mes.corporation.loyaltyplatform.fedelta;

import jakarta.persistence.EntityNotFoundException;
import mes.corporation.loyaltyplatform.utenti.model.Cliente;
import mes.corporation.loyaltyplatform.utenti.repo.AziendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/*
*ProgrammaFedeltaService è una classe di servizio, ma nel codice fornito non ha implementazioni specifiche.
* Può essere utilizzata per gestire operazioni legate ai programmi fedeltà, ma al momento non ha nessuna logica specifica.
*
* */
@Service
public class ProgrammaFedeltaService implements ProgrammaFedelta {
    @Autowired
    private ProgrammaFedeltaPuntiRepository programmaFedeltaPuntiRepository;

    @Autowired
    private AziendaRepository aziendaRepository;

    @Autowired
    private ProgrammaFedeltaFactory programmaFedeltaFactory; // Aggiungi l'iniezione del servizio di factory

    @Override
    public void registraCliente(Cliente cliente) {
    }

    @Override
    public int calcolaPunti(BigDecimal importoSpeso) {      return 0;     }

    public ProgrammaFedelta getProgrammaFedeltaByAziendaId(Long aziendaId) {
        return programmaFedeltaPuntiRepository.findByAziendaId(aziendaId)
                .orElseThrow(() -> new EntityNotFoundException("Programma Fedeltà non trovato per l'ID Azienda: " + aziendaId));
    }
}


