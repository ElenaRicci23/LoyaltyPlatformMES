package mes.corporation.loyaltyplatform.utenti.service;

import jakarta.transaction.Transactional;

import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.repo.AziendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AziendaService extends UtenteService<Azienda,AziendaRepository> {


    private final AziendaRepository aziendaRepository;


    @Autowired
    public AziendaService(AziendaRepository aziendaRepository) {
        this.aziendaRepository = aziendaRepository;

    }

    // Metodo per verificare se una partita IVA è già registrata
    public boolean isPartitaIvaAlreadyRegistered(String partitaIva) {
        Azienda existingAzienda = aziendaRepository.findByDatiPersonali_PartitaIva(partitaIva);
        return existingAzienda != null;
    }

    // Metodo per trovare un'azienda per numero di partita IVA
    public Azienda findAziendaByPartitaIva(String partitaIva) {
        return aziendaRepository.findByDatiPersonali_PartitaIva(partitaIva);
    }
    public Azienda getAziendaById(Long aziendaId) {
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(aziendaId);
        return aziendaOptional.orElse(null); // Restituisce l'azienda se presente, altrimenti null
    }


    @Override
    public void registrazione(Azienda azienda) {
        // Verifica se l'azienda ha almeno 2 stabilimenti
        if (azienda.getDatiPersonali().getNumeroStabilimenti() < 2) {
            throw new RuntimeException("L'azienda deve avere almeno 2 stabilimenti per la registrazione.");
        }

        // Altri controlli e logica di registrazione, ad esempio, salvare l'azienda nel database
        aziendaRepository.save(azienda);
    }

    public boolean isEmailAlreadyRegistered(String email) {
        Optional<Azienda> azienda = aziendaRepository.findByEmail(email);
        return azienda.isPresent();
    }
    public void saveAzienda(Azienda azienda) {
        aziendaRepository.save(azienda);
    }



}



