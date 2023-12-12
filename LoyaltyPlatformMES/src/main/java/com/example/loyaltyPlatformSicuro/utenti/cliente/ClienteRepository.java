package com.example.loyaltyPlatformSicuro.utenti.cliente;

import com.example.loyaltyPlatformSicuro.utenti.UtenteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository per la gestione delle operazioni sui dati del cliente.
 */
@Repository
public interface ClienteRepository extends UtenteRepository<Cliente> {


    Optional<Cliente> findByEmail(String email);

    Cliente findByDatiPersonali_Nome(String nome);
}
