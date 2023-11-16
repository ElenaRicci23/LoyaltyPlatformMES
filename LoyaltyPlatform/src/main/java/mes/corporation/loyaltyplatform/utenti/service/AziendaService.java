package mes.corporation.loyaltyplatform.utenti.service;

import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.repo.AziendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Servizio per la gestione delle operazioni relative alle aziende.
 */
@Service
public class AziendaService extends UtenteService<Azienda, AziendaRepository> {

    private final AziendaRepository aziendaRepository;

    @Autowired
    public AziendaService(AziendaRepository aziendaRepository) {
        this.aziendaRepository = aziendaRepository;
    }

    /**
     * Verifica se una partita IVA è già registrata nel sistema.
     *
     * @param partitaIva La partita IVA da verificare.
     * @return True se la partita IVA è già registrata, altrimenti False.
     */
    public boolean isPartitaIvaAlreadyRegistered(String partitaIva) {
        Azienda existingAzienda = aziendaRepository.findByDatiPersonali_PartitaIva(partitaIva);
        return existingAzienda != null;
    }

    /**
     * Trova un'azienda dato il numero di partita IVA.
     *
     * @param partitaIva Il numero di partita IVA da cercare.
     * @return Un'istanza di Azienda se trovata, altrimenti null.
     */
    public Azienda findAziendaByPartitaIva(String partitaIva) {
        return aziendaRepository.findByDatiPersonali_PartitaIva(partitaIva);
    }

    /**
     * Ottiene un'azienda dato il suo ID.
     *
     * @param aziendaId L'ID dell'azienda da ottenere.
     * @return Un'istanza di Azienda se trovata, altrimenti null.
     */
    public Azienda getAziendaById(Long aziendaId) {
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(aziendaId);
        return aziendaOptional.orElse(null); // Restituisce l'azienda se presente, altrimenti null
    }

    /**
     * Metodo di registrazione per le aziende. Esegue controlli e logica di registrazione.
     *
     * @param azienda L'azienda da registrare.
     * @throws RuntimeException Se l'azienda non soddisfa i requisiti di registrazione.
     */
    @Override
    public void registrazione(Azienda azienda) {
        // Verifica se l'azienda ha almeno 2 stabilimenti
        if (azienda.getDatiPersonali().getNumeroStabilimenti() < 2) {
            throw new RuntimeException("L'azienda deve avere almeno 2 stabilimenti per la registrazione.");
        }

        // Altri controlli e logica di registrazione, ad esempio, salvare l'azienda nel database
        saveAzienda(azienda);
    }

    /**
     * Verifica se un'email è già registrata nel sistema.
     *
     * @param email L'email da verificare.
     * @return True se l'email è già registrata, altrimenti False.
     */
    public boolean isEmailAlreadyRegistered(String email) {
        Optional<Azienda> azienda = aziendaRepository.findByEmail(email);
        return azienda.isPresent();
    }

    /**
     * Salva un'azienda nel repository.
     *
     * @param azienda L'azienda da salvare.
     */
    public void saveAzienda(Azienda azienda) {
        aziendaRepository.save(azienda);
    }
}



