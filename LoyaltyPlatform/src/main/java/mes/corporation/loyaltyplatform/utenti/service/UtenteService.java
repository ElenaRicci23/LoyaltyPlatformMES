package mes.corporation.loyaltyplatform.utenti.service;


import mes.corporation.loyaltyplatform.utenti.model.Utente;
import mes.corporation.loyaltyplatform.utenti.repo.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class UtenteService<T extends Utente, R extends UtenteRepository<T>> {
    @Autowired

    protected R utenteRepository;


    public abstract void registrazione(T utente);
}