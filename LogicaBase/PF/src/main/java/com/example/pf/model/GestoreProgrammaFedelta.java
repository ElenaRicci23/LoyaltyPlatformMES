package com.example.pf.model;

import com.example.pf.DTO.ProgrammaFedeltaDTO;
import com.example.pf.cliente.Acquisto;
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

    public void aggiornaPuntiFedelta(Cliente cliente, Acquisto acquisto) {
        Tessera tessera = cliente.getTessera();
        if (tessera != null) {
            Set<ProgrammaFedelta> programmiFedelta = tessera.getProgrammiFedelta();
            for (ProgrammaFedelta programmaFedelta : programmiFedelta) {
                if (programmaFedelta instanceof IProgrammaPunti) {
                    IProgrammaPunti programmaPunti = (IProgrammaPunti) programmaFedelta;
                    int puntiDaAggiungere = calcolaPuntiDaAggiungere(acquisto.getImporto());
                    programmaPunti.accumulaPunti(puntiDaAggiungere, acquisto);
                }
            }
        }
    }

    private int calcolaPuntiDaAggiungere(double importo) {
        // In questa implementazione ipotetica, si potrebbe stabilire che ogni tot di importo
        // corrisponde a un certo numero di punti. Ad esempio, 1 punto ogni 10 euro di spesa.
        int puntiPerEuro = 1;
        return (int) (importo / 10) * puntiPerEuro;
    }



}

