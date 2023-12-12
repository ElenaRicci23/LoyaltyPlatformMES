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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Questa classe è un servizio Spring che gestisce i programmi di fedeltà, inclusa la creazione, la configurazione
 * e il recupero dei programmi associati a un'azienda specifica.
 */
@Service
public class GestoreProgrammiFedelta {

    private final FactoryProgrammaFedelta factory;
    private final AziendaRepository aziendaRepository;
    private final ProgrammaFedeltaRepository programmaFedeltaRepository;
    private final ProgrammaPuntiRepository programmaPuntiRepository;
    private final PremioRepository premioRepository;
    private  ModelMapper modelMapper;



    /**
     * Costruttore della classe che inietta le dipendenze necessarie.
     *
     * @param aziendaRepository         Il repository delle aziende.
     * @param programmaFedeltaRepository Il repository dei programmi di fedeltà.
     * @param programmaPuntiRepository   Il repository dei programmi punti.
     * @param premioRepository           Il repository dei premi.
     * @param factory                   Un'istanza di FactoryProgrammaFedelta per la creazione dei programmi.
     */
    @Autowired
    public GestoreProgrammiFedelta(
            AziendaRepository aziendaRepository,
            ProgrammaFedeltaRepository programmaFedeltaRepository,
            ProgrammaPuntiRepository programmaPuntiRepository,
            PremioRepository premioRepository,
            ConcreteFactoryProgrammaFedelta factory) {
        this.aziendaRepository = aziendaRepository;
        this.programmaFedeltaRepository = programmaFedeltaRepository;
        this.programmaPuntiRepository = programmaPuntiRepository;
        this.premioRepository = premioRepository;
        this.factory = factory;
        this.modelMapper = modelMapper;
    }

    /**
     * Crea un programma di fedeltà sulla base dei dati forniti e lo associa a un'azienda specifica.
     *
     * @param dto       I dati del programma di fedeltà da creare.
     * @param aziendaId L'ID dell'azienda a cui associare il programma di fedeltà.
     * @return Il programma di fedeltà creato.
     */
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

    /**
     * Trova un'azienda per ID.
     *
     * @param aziendaId L'ID dell'azienda da cercare.
     * @return L'azienda trovata.
     * @throws IllegalArgumentException Se l'azienda non viene trovata per l'ID fornito.
     */
    @Transactional
    public Azienda trovaAziendaPerId(Long aziendaId) {
        return aziendaRepository.findById(aziendaId)
                .orElseThrow(() -> new IllegalArgumentException("Azienda non trovata per l'ID fornito: " + aziendaId));
    }

    /**
     * Salva un programma di fedeltà nel repository.
     *
     * @param programmaFedelta Il programma di fedeltà da salvare.
     */
    @Transactional
    public void salvaProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        programmaFedeltaRepository.save(programmaFedelta);

        if (programmaFedelta instanceof ProgrammaPunti) {
            ProgrammaPunti programmaPunti = (ProgrammaPunti) programmaFedelta;
            programmaPuntiRepository.save(programmaPunti);
        }
    }

    /**
     * Configura un programma punti specifico associato a un'azienda, inclusa la modifica del tasso di conversione e
     * l'aggiunta di premi.
     *
     * @param programmaPuntiDTO I dati per la configurazione del programma punti.
     * @param aziendaId         L'ID dell'azienda a cui appartiene il programma punti.
     * @param programmaPuntiId  L'ID del programma punti da configurare.
     * @throws IllegalArgumentException Se il programma punti non viene trovato per l'ID specificato
     *                                  o se non appartiene all'azienda specificata.
     */
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

    /**
     * Recupera i programmi di fedeltà associati a un'azienda specifica.
     *
     * @param azienda L'azienda per cui recuperare i programmi di fedeltà.
     * @return Una lista di programmi di fedeltà associati all'azienda.
     */
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

    /**
     * Converte una lista di programmi di fedeltà in una lista di DTO.
     *
     * @param programmiFedeltaList La lista di programmi di fedeltà da convertire.
     * @return Una lista di DTO rappresentanti i programmi di fedeltà.
     */
    @Transactional(readOnly = true)
    public List<ProgrammaFedeltaDTO> convertToDTOList(List<ProgrammaFedelta> programmiFedeltaList) {
        return programmiFedeltaList.stream()
                .map(programmaFedelta -> modelMapper.map(programmaFedelta, ProgrammaFedeltaDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Elimina tutti i programmi di fedeltà dal repository.
     */
    public void eliminaProgrammi() {
        programmaFedeltaRepository.deleteAll();
    }

}

