package mes.corporation.loyaltyplatform.utenti.service;


import mes.corporation.loyaltyplatform.utenti.model.Utente;
import mes.corporation.loyaltyplatform.utenti.repo.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Un servizio astratto per la gestione degli utenti.
 *
 * @param <T> Il tipo di utente gestito dal servizio.
 * @param <R> Il repository associato al tipo di utente.
 */
public abstract class UtenteService<T extends Utente, R extends UtenteRepository<T>> {

    /**
     * Il repository associato al tipo di utente.
     */
    protected R utenteRepository;

    /**
     * Metodo astratto per la registrazione di un utente.
     *
     * @param utente L'utente da registrare.
     */
    public abstract void registrazione(T utente);
}
