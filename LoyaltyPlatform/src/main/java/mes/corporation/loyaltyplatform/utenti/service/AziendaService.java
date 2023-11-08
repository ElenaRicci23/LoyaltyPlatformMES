package mes.corporation.loyaltyplatform.utenti.service;

import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.repo.AziendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AziendaService extends UtenteService<Azienda,AziendaRepository> {

    @Autowired
    private AziendaRepository aziendaRepository;

    @Override
    public boolean login(Azienda azienda) {
        Azienda existingAzienda = aziendaRepository.findByEmail(azienda.getEmail());
        if (existingAzienda != null && existingAzienda.getPassword().equals(azienda.getPassword())) {
            return true;
        }
        return false;
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
        Azienda azienda = aziendaRepository.findByEmail(email);
        return azienda != null;
    }

}



