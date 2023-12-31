package com.example.loyaltyPlatformSicuro.utenti;



import com.example.loyaltyPlatformSicuro.DTO.AuthenticationDTO;
import com.example.loyaltyPlatformSicuro.security.auth.RuoloEnum;
import com.example.loyaltyPlatformSicuro.utenti.azienda.Azienda;
import com.example.loyaltyPlatformSicuro.utenti.cliente.Cliente;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Classe astratta che fornisce servizi di base per gestire utenti.
 *
 * @param <T> Il tipo di utente (Cliente, Azienda, ecc.).
 * @param <R> Il tipo di repository per l'utente.
 */

//@Service


public abstract class UtenteService<T extends Utente, R extends UtenteRepository<T>> {


    protected R utenteRepository;

    /**
     * Costruttore della classe UtenteService.
     *
     * @param utenteRepository Il repository specifico per l'utente.
     */

    public UtenteService(R utenteRepository) {
        this.utenteRepository = utenteRepository;
    }
    /**
     * Verifica le credenziali di autenticazione fornite e restituisce il ruolo dell'utente se le credenziali sono valide.
     *
     * @param authenticationDTO I dati di autenticazione forniti.
     * @return Il ruolo dell'utente (AZIENDA, CLIENTE, COMMESSO, ADMIN) se le credenziali sono valide, altrimenti null.
     */

    public RuoloEnum verificaCredenziali(AuthenticationDTO authenticationDTO) {
        String email = authenticationDTO.getEmail();
        String password = authenticationDTO.getPassword();

        // Ottieni l'utente associato all'indirizzo email dal repository
        Optional<T> utenteOptional = utenteRepository.findByEmail(email);

        if (utenteOptional.isPresent()) {
            T utente = utenteOptional.get();

            // Verifica il tipo dell'utente per distinguere tra Cliente e Azienda
            if (utente instanceof Azienda azienda) {
                // L'utente è un'azienda

                // Esegui la verifica della password per l'azienda
                if (azienda.getPassword().equals(password)) {
                    return RuoloEnum.AZIENDA;
                }
            } else if (utente instanceof Cliente cliente) {
                // L'utente è un cliente

                // Esegui la verifica della password per il cliente
                if (cliente.getPassword().equals(password)) {
                    return RuoloEnum.CLIENTE;
                }
            }
        }

        // Nessun utente con quell'email trovato o credenziali non valide
        return null;
    }
    /**
     * Ottiene l'ID dell'azienda associata all'indirizzo email fornito.
     *
     * @param email L'indirizzo email dell'azienda.
     * @return L'ID dell'azienda se trovato, altrimenti null.
     */

    public Long getAziendaid(String email) {
        Optional<T> utenteOptional = utenteRepository.findByEmail(email);
        if (utenteOptional.isPresent()) {
            T utente = utenteOptional.get();
            if (utente instanceof Azienda azienda) {
                return azienda.getId();
            }
        }
        return null; // Restituisci null se l'utente non è un'azienda o se non è stato trovato
    }


    /**
     * Ottiene l'ID del cliente associato all'indirizzo email fornito.
     *
     * @param email L'indirizzo email del cliente.
     * @return L'ID del cliente se trovato, altrimenti null.
     */


    public Long getClienteid(String email) {
        Optional<T> utenteOptional = utenteRepository.findByEmail(email);
        if (utenteOptional.isPresent()) {
            T utente = utenteOptional.get();
            if (utente instanceof Cliente cliente) {
                return cliente.getId();
            }
        }
        return null; // Restituisci null se l'utente non è un'azienda o se non è stato trovato
    }
    /**
     * Gestisce il processo di login per un utente, restituendo l'URL della dashboard appropriata in base al ruolo.
     *
     * @param authenticationDTO I dati di autenticazione forniti.
     * @return L'URL della dashboard in base al ruolo se le credenziali sono valide, altrimenti lancia un'eccezione.
     * @throws IllegalArgumentException Se le credenziali non sono valide.
     */


    public String login(AuthenticationDTO authenticationDTO) {
        RuoloEnum ruolo = verificaCredenziali(authenticationDTO);

        if (ruolo != null) {
            String email = authenticationDTO.getEmail();

            // Ottieni l'ID dell'utente in base all'indirizzo email
            Long userId = null;
            if (ruolo == RuoloEnum.AZIENDA) {
                userId = getAziendaid(email);
            } else if (ruolo == RuoloEnum.CLIENTE) {
                userId = getClienteid(email);
            }

                if (userId != null) {
                    return switch (ruolo) {
                        case AZIENDA ->
                            // Reindirizza all'URL della dashboard dell'azienda con l'ID
                                "/api/azienda/" + userId + "/dashboardAzienda";
                        case CLIENTE ->
                            // Reindirizza all'URL della dashboard del cliente con l'ID
                                "/api/cliente/" + userId + "/dashboardCliente";
                        case COMMESSO ->
                            // Reindirizza alla dashboard del commesso (gestisci come preferisci)
                                "/dashboardCommesso";
                        case ADMIN ->
                            // Reindirizza alla dashboard dell'admin (gestisci come preferisci)
                                "/dashboardAdmin";
                    };
                }
            }
            throw new IllegalArgumentException("Credenziali non valide.");

    }
}