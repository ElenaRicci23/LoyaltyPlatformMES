package com.example.pf.model;

import com.example.pf.DTO.ProgrammaFedeltaDTO;
import com.example.pf.azienda.Premio;
import com.example.pf.cliente.Cliente;
import com.example.pf.cliente.ClienteRepository;
import com.example.pf.cliente.Tessera;
import com.example.pf.factory.FactoryProgrammaPunti;
import com.example.pf.factory.IProgrammaPunti;
import com.example.pf.repo.ProgrammaFedeltaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class GestoreProgrammaFedelta {

    private final FactoryProgrammaPunti factory;
    private final ProgrammaFedeltaRepository programmaFedeltaRepository;

    private final ClienteRepository clienteRepository;


    @Autowired
    public GestoreProgrammaFedelta(FactoryProgrammaPunti factory, ProgrammaFedeltaRepository programmaFedeltaRepository, ClienteRepository clienteRepository) {
        this.factory = factory;
        this.programmaFedeltaRepository = programmaFedeltaRepository; // Inizializza il repository
        this.clienteRepository = clienteRepository;
    }

    public IProgrammaPunti creaProgrammaPunti(String nome, String descrizione, TipoProgrammaFedelta tipoProgrammaFedelta) {
        if (tipoProgrammaFedelta == TipoProgrammaFedelta.PUNTI) {
            return factory.creaProgrammaPunti(nome, descrizione);
        } else {
            // Gestisci il caso in cui la tipologia non è valida (ad esempio, se non è "PUNTI")
            System.out.println("Non gestiamo per ora altri tipi di programmi WORK IN PROGRESS!");
            return null;
        }
    }
    public boolean iscriviClienteAProgrammaFedelta(Cliente cliente, ProgrammaFedeltaDTO programmaFedeltaDTO) {
        // Trova il programma fedeltà desiderato in base al nome o a un altro identificatore
        ProgrammaFedelta programmaFedeltaDesiderato = programmaFedeltaRepository.findByNome(programmaFedeltaDTO.getNome());

        if (programmaFedeltaDesiderato != null && programmaFedeltaDesiderato.getTipoProgrammaFedelta() == TipoProgrammaFedelta.PUNTI) {
            // Associa il programma fedeltà al cliente attraverso la tessera
            cliente.getTessera().addProgrammaFedelta(programmaFedeltaDesiderato);

            // Salva il cliente per aggiornare l'associazione con il programma fedeltà
            clienteRepository.save(cliente);

            return true; // Iscrizione avvenuta con successo
        } else {
            return false; // Il programma fedeltà non è di tipo "PUNTI" o non è stato trovato
        }
    }
    public void configurazioneProgrammaFedelta(IProgrammaPunti programmaPunti, Set<Premio> premi, double tassoConversione, int puntiBonus, Set<Premio> premiPersonalizzati, String messaggioPersonalizzato, String occasioneSpeciale, String eccezione, Cliente cliente, String attivita, String parametro, String valore) {
        if (programmaPunti != null) {
            // Esegui la configurazione specifica del programma fedeltà

            if (premi != null) {
                for (Premio premio : premi) {
                    programmaPunti.aggiungiPremio(premio);
                }
            }

            if (tassoConversione >= 0) {
                programmaPunti.impostaTassoConversione(tassoConversione);
            }

            if (puntiBonus >= 0) {
                programmaPunti.impostaPuntiBonus(puntiBonus);
            }

            if (premiPersonalizzati != null) {
                for (Premio premio : premiPersonalizzati) {
                    programmaPunti.aggiungiPremioPersonalizzato(premio);
                }
            }

            if (messaggioPersonalizzato != null) {
                programmaPunti.personalizzaMessaggiComunicazioni(messaggioPersonalizzato);
            }

            if (occasioneSpeciale != null) {
                programmaPunti.offriRicompenseSpeciali(occasioneSpeciale);
            }

            if (eccezione != null) {
                programmaPunti.gestisciEccezioni(eccezione);
            }

            if (cliente != null && attivita != null) {
                programmaPunti.registraAttivitaCliente(cliente, attivita);
            }

            if (parametro != null && valore != null) {
                programmaPunti.configuraParametriAvanzati(parametro, valore);
            }

            // Altre configurazioni specifiche, se necessario...
        } else {
            // Gestisci il caso in cui il programma fedeltà non è valido
            System.out.println("Programma fedeltà non valido.");
        }
    }







}

