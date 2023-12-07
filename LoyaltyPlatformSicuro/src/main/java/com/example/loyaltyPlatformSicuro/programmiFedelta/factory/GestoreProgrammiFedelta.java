package com.example.loyaltyPlatformSicuro.programmiFedelta.factory;


import com.example.loyaltyPlatformSicuro.DTO.PremioDTO;
import com.example.loyaltyPlatformSicuro.DTO.ProgrammaFedeltaDTO;
import com.example.loyaltyPlatformSicuro.DTO.ProgrammaPuntiDTO;
import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.programmiFedelta.TipoProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.Configurazione.Premio;
import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.ProgrammaPunti;
import com.example.loyaltyPlatformSicuro.programmiFedelta.repo.PremioRepository;
import com.example.loyaltyPlatformSicuro.programmiFedelta.repo.ProgrammaFedeltaRepository;
import com.example.loyaltyPlatformSicuro.programmiFedelta.repo.ProgrammaPuntiRepository;
import com.example.loyaltyPlatformSicuro.utenti.azienda.Azienda;
import com.example.loyaltyPlatformSicuro.utenti.azienda.AziendaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestoreProgrammiFedelta {

    private final FactoryProgrammaFedelta factory;
    private final AziendaRepository aziendaRepository;
    private final ProgrammaFedeltaRepository programmaFedeltaRepository;
    private final ProgrammaPuntiRepository programmaPuntiRepository;
    private final PremioRepository premioRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GestoreProgrammiFedelta(
            AziendaRepository aziendaRepository,
            ProgrammaFedeltaRepository programmaFedeltaRepository,
            ProgrammaPuntiRepository programmaPuntiRepository,
            PremioRepository premioRepository,
            ModelMapper modelMapper,
            ConcreteFactoryProgrammaFedelta factory) {
        this.aziendaRepository = aziendaRepository;
        this.programmaFedeltaRepository = programmaFedeltaRepository;
        this.programmaPuntiRepository = programmaPuntiRepository;
        this.premioRepository = premioRepository;
        this.factory = factory;
        this.modelMapper = modelMapper;
    }


    @Transactional
    public ProgrammaFedelta creaProgrammaFedelta(ProgrammaFedeltaDTO dto, Long aziendaId) {
        TipoProgrammaFedelta tipo = dto.getTipoProgrammaFedelta();
        String nome = dto.getNome();
        String descrizione = dto.getDescrizione();

        ProgrammaFedelta programmaFedelta;
        if (tipo == TipoProgrammaFedelta.PUNTI) {
            programmaFedelta = factory.creaProgrammaPunti();
        } else if (tipo == TipoProgrammaFedelta.CASHBACK) {
            programmaFedelta = factory.creaProgrammaCashback();
        } else if (tipo == TipoProgrammaFedelta.LIVELLI) {
            programmaFedelta = factory.creaProgrammiLivello();
        } else if (tipo == TipoProgrammaFedelta.MEMBERSHIP) {
            programmaFedelta = factory.creaProgrammiMembership();
        } else if (tipo == TipoProgrammaFedelta.VIP) {
            programmaFedelta = factory.creaProgrammiVip();
        } else {
            throw new IllegalArgumentException("Tipo di programma fedeltà non supportato");
        }

        // Associa il programmaFedelta all'azienda specifica
        Azienda azienda = trovaAziendaPerId(aziendaId);
        programmaFedelta.setAzienda(azienda);

        // Salva il programmaFedelta nel repository
        salvaProgrammaFedelta(programmaFedelta);

        return programmaFedelta;
    }

    @Transactional
    public Azienda trovaAziendaPerId(Long aziendaId) {

        return aziendaRepository.findById(aziendaId)
                .orElseThrow(() -> new IllegalArgumentException("Azienda non trovata per l'ID fornito: " + aziendaId));
    }

    @Transactional
    public void salvaProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        programmaFedeltaRepository.save(programmaFedelta);

        if (programmaFedelta instanceof ProgrammaPunti) {
            ProgrammaPunti programmaPunti = (ProgrammaPunti) programmaFedelta;
            programmaPuntiRepository.save(programmaPunti);
        }
    }


    @Transactional
    public void configuraProgrammaPunti(ProgrammaPuntiDTO programmaPuntiDTO, Long aziendaId, Long programmaPuntiId) {
        double tassoConversione = programmaPuntiDTO.getTassoConversione();
        List<PremioDTO> premiDTO = programmaPuntiDTO.getPremi();
        List<Premio> premi = convertiPremiDTOInPremi(premiDTO); // Converte PremioDTO in Premio
        Azienda azienda = trovaAziendaPerId(aziendaId);

        // Trova il programma punti specifico dell'azienda per ID
        ProgrammaPunti programmaPunti = programmaPuntiRepository.findById(programmaPuntiId)
                .orElseThrow(() -> new IllegalArgumentException("Programma punti non trovato per l'ID specificato."));

        // Verifica che il programma punti appartenga all'azienda specifica
        if (!azienda.getProgrammiFedelta().contains(programmaPunti)) {
            throw new IllegalArgumentException("Il programma punti specificato non appartiene all'azienda.");
        }

        // Aggiungi i premi al programma punti
        for (Premio premio : premi) {
            programmaPunti.aggiungiPremio(premio);
        }

        // Imposta il nuovo tasso di conversione
        programmaPunti.setTassoConversione(tassoConversione);

        // Salvataggio del programma punti nel repository
        programmaPuntiRepository.save(programmaPunti);
        premioRepository.saveAll(premi);
    }

    @Transactional(readOnly = true)
    public List<ProgrammaFedelta> getProgrammiFedeltaByAzienda(Azienda azienda) {
        // Implementa la logica per recuperare i programmi fedeltà dell'azienda
        return programmaFedeltaRepository.findByAzienda(azienda);
    }

    private List<Premio> convertiPremiDTOInPremi(List<PremioDTO> premiDTO) {
        return premiDTO.stream()
                .map(premioDTO -> modelMapper.map(premioDTO, Premio.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProgrammaFedeltaDTO> convertToDTOList(List<ProgrammaFedelta> programmiFedeltaList) {
        return programmiFedeltaList.stream()
                .map(programmaFedelta -> modelMapper.map(programmaFedelta, ProgrammaFedeltaDTO.class))
                .collect(Collectors.toList());
    }
    public void eliminaProgrammi() {
        // Aggiungi il codice per eliminare tutti i programmi fedeltà dal tuo repository
        programmaFedeltaRepository.deleteAll(); // Supponendo che tu abbia un repository chiamato programmaFedeltaRepository
    }


}