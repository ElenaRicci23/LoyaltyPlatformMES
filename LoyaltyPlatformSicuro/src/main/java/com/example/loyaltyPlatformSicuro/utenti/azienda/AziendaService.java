package com.example.loyaltyPlatformSicuro.utenti.azienda;


import com.example.loyaltyPlatformSicuro.DTO.AziendaDTO;
import com.example.loyaltyPlatformSicuro.DTO.DatiPersonaliAziendaDTO;
import com.example.loyaltyPlatformSicuro.DTO.ProgrammaFedeltaDTO;
import com.example.loyaltyPlatformSicuro.DTO.ProgrammaPuntiDTO;
import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.programmiFedelta.factory.GestoreProgrammiFedelta;
import com.example.loyaltyPlatformSicuro.security.auth.RuoloRepository;
import com.example.loyaltyPlatformSicuro.utenti.UtenteService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AziendaService extends UtenteService<Azienda, AziendaRepository> {

    private final AziendaRepository aziendaRepository;
    private final RuoloRepository ruoloRepository;

    private final GestoreProgrammiFedelta gestoreProgrammiFedelta;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public AziendaService(AziendaRepository aziendaRepository, RuoloRepository ruoloRepository, GestoreProgrammiFedelta gestoreProgrammiFedelta) {
        super(aziendaRepository);
        this.aziendaRepository = aziendaRepository;
        this.ruoloRepository = ruoloRepository;
        this.gestoreProgrammiFedelta = gestoreProgrammiFedelta;
    }


    public boolean isEmailAlreadyRegistered(String email) {
        Optional<Azienda> azienda = aziendaRepository.findByEmail(email);
        return azienda.isPresent();
    }

    public boolean isPartitaIvaAlreadyRegistered(String partitaIva) {
        Azienda existingAzienda = aziendaRepository.findByDatiPersonali_PartitaIva(partitaIva);
        return existingAzienda != null;
    }

    public void saveAzienda(Azienda azienda) {
        aziendaRepository.save(azienda);
    }

    public AziendaDTO convertToDto(Azienda azienda) {
        return modelMapper.map(azienda, AziendaDTO.class);
    }

    public Azienda convertToEntity(AziendaDTO aziendaDTO) {
        return modelMapper.map(aziendaDTO, Azienda.class);
    }

    public DatiPersonaliAziendaDTO convertToDto(DatiPersonaliAzienda datiPersonali) {
        return modelMapper.map(datiPersonali, DatiPersonaliAziendaDTO.class);
    }

    public DatiPersonaliAzienda convertToEntity(DatiPersonaliAziendaDTO datiPersonaliDTO) {
        return modelMapper.map(datiPersonaliDTO, DatiPersonaliAzienda.class);
    }


    public void registrazioneConDTO(AziendaDTO aziendaDTO) {
        // Verifica se l'azienda ha almeno 2 stabilimenti
        if (aziendaDTO.getDatiPersonali().getNumeroStabilimenti() < 2) {
            throw new RuntimeException("L'azienda deve avere almeno 2 stabilimenti per la registrazione.");
        }
        // Verifica se l'email è già registrata
        if (isEmailAlreadyRegistered(aziendaDTO.getEmail())) {
            throw new RuntimeException("L'email è già registrata.");
        }
        // Verifica se la partita IVA è già registrata
        if (isPartitaIvaAlreadyRegistered(aziendaDTO.getDatiPersonali().getPartitaIva())) {
            throw new RuntimeException("La partita IVA è già registrata.");
        }

        // Utilizza il modelMapper per mappare l'AziendaDTO a un oggetto Azienda
        Azienda azienda = modelMapper.map(aziendaDTO, Azienda.class);
        azienda.setRuolo("AZIENDA");

        // Utilizza il modelMapper anche per mappare i DatiPersonaliAziendaDTO a DatiPersonaliAzienda
        DatiPersonaliAzienda datiPersonali = modelMapper.map(aziendaDTO.getDatiPersonali(), DatiPersonaliAzienda.class);

        azienda.setDatiPersonali(datiPersonali);

        saveAzienda(azienda);
    }

    public AziendaDTO getAziendaDTOById(Long aziendaId) {
        Azienda azienda = aziendaRepository.findById(aziendaId)
                .orElseThrow(() -> new EntityNotFoundException("Azienda non trovata"));

        return modelMapper.map(azienda, AziendaDTO.class);
    }

    public Azienda getAziendaById(Long id) {
        return aziendaRepository.findAziendaById(id);
    }


    public void aggiungiProgrammaFedeltaAdAzienda(Long aziendaId, ProgrammaFedeltaDTO programmaFedeltaDTO) {
        Azienda azienda = aziendaRepository.findById(aziendaId).orElseThrow(() -> new EntityNotFoundException("Azienda non trovata"));
        // Usa il GestoreProgrammiFedelta per creare il nuovo programma fedeltà
        ProgrammaFedelta nuovoProgramma = gestoreProgrammiFedelta.creaProgrammaFedelta(programmaFedeltaDTO, aziendaId);

        azienda.addProgrammaFedelta(nuovoProgramma);
        aziendaRepository.save(azienda);
    }
    public void configuraProgrammaPunti(ProgrammaPuntiDTO programmaPuntiDTO, Long aziendaId, Long programmaPuntiId) {
        // Chiamare il metodo di configurazione del programma punti dal GestoreProgrammiFedelta
        gestoreProgrammiFedelta.configuraProgrammaPunti(programmaPuntiDTO, aziendaId, programmaPuntiId);
    }

    public Azienda findById(Long aziendaId) {
        // Utilizza il repository o il meccanismo di accesso ai dati per cercare l'azienda per ID
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(aziendaId);
        return aziendaOptional.orElse(null);
    }


    public List<Azienda> getAllAziende() {
        return aziendaRepository.findAll();
    }


    public void eliminaAzienda(Long aziendaId) {
        Azienda azienda = findById(aziendaId);
        if (azienda != null) {
            aziendaRepository.delete(azienda);
        } else {
            throw new EntityNotFoundException("Azienda non trovata");
        }
    }

    public void aggiornaAzienda(Long aziendaId, AziendaDTO aziendaDTO) {
        Azienda azienda = findById(aziendaId);
        if (azienda != null) {
            modelMapper.map(aziendaDTO, azienda); // Utilizza modelMapper per copiare i dati da aziendaDTO a azienda
            aziendaRepository.save(azienda);
        } else {
            throw new EntityNotFoundException("Azienda non trovata");
        }
    }
    public void eliminaAziende() {
        // Aggiungi il codice per eliminare tutte le aziende dal tuo repository
        aziendaRepository.deleteAll(); // Supponendo che tu abbia un repository chiamato aziendaRepository
    }


}

