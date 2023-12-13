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

/**
 * Servizio per la gestione delle aziende.
 */
@Service
public class AziendaService extends UtenteService<Azienda, AziendaRepository> {

    private final AziendaRepository aziendaRepository;
    private final RuoloRepository ruoloRepository;
    private final GestoreProgrammiFedelta gestoreProgrammiFedelta;

    private ModelMapper modelMapper;

    @Autowired
    public AziendaService(AziendaRepository aziendaRepository, RuoloRepository ruoloRepository, GestoreProgrammiFedelta gestoreProgrammiFedelta) {
        super(aziendaRepository);
        this.aziendaRepository = aziendaRepository;
        this.ruoloRepository = ruoloRepository;
        this.gestoreProgrammiFedelta = gestoreProgrammiFedelta;
        this.modelMapper = new ModelMapper();

    }

    /**
     * Verifica se un'email è già registrata.
     *
     * @param email l'email da verificare
     * @return true se l'email è già registrata, altrimenti false
     */
    public boolean isEmailAlreadyRegistered(String email) {
        Optional<Azienda> azienda = aziendaRepository.findByEmail(email);
        return azienda.isPresent();
    }

    /**
     * Verifica se una Partita IVA è già registrata.
     *
     * @param partitaIva la Partita IVA da verificare
     * @return true se la Partita IVA è già registrata, altrimenti false
     */
    public boolean isPartitaIvaAlreadyRegistered(String partitaIva) {
        Azienda existingAzienda = aziendaRepository.findByDatiPersonali_PartitaIva(partitaIva);
        return existingAzienda != null;
    }

    /**
     * Salva un'azienda nel repository.
     *
     * @param azienda l'azienda da salvare
     */
    public void saveAzienda(Azienda azienda) {
        aziendaRepository.save(azienda);
    }

    /**
     * Converte un'azienda in un DTO.
     *
     * @param azienda l'azienda da convertire
     * @return il DTO corrispondente
     */
    public AziendaDTO convertToDto(Azienda azienda) {
        return modelMapper.map(azienda, AziendaDTO.class);
    }

    /**
     * Converte un DTO in un'azienda.
     *
     * @param aziendaDTO il DTO da convertire
     * @return l'azienda corrispondente
     */
    public Azienda convertToEntity(AziendaDTO aziendaDTO) {
        return modelMapper.map(aziendaDTO, Azienda.class);
    }

    /**
     * Converte un oggetto DatiPersonaliAzienda in un DTO.
     *
     * @param datiPersonali gli dati personali da convertire
     * @return il DTO corrispondente
     */
    public DatiPersonaliAziendaDTO convertToDto(DatiPersonaliAzienda datiPersonali) {
        return modelMapper.map(datiPersonali, DatiPersonaliAziendaDTO.class);
    }

    /**
     * Converte un DTO in un oggetto DatiPersonaliAzienda.
     *
     * @param datiPersonaliDTO il DTO da convertire
     * @return gli dati personali corrispondenti
     */
    public DatiPersonaliAzienda convertToEntity(DatiPersonaliAziendaDTO datiPersonaliDTO) {
        return modelMapper.map(datiPersonaliDTO, DatiPersonaliAzienda.class);
    }

    /**
     * Registra un'azienda utilizzando un oggetto AziendaDTO.
     *
     * @param aziendaDTO l'oggetto AziendaDTO contenente i dati dell'azienda da registrare
     * @throws RuntimeException se l'azienda non soddisfa i requisiti di registrazione o se l'email o la Partita IVA sono già registrate
     */
    public void registrazioneConDTO(AziendaDTO aziendaDTO) {
        if (aziendaDTO.getDatiPersonali().getNumeroStabilimenti() < 2) {
            throw new RuntimeException("L'azienda deve avere almeno 2 stabilimenti per la registrazione.");
        }
        if (isEmailAlreadyRegistered(aziendaDTO.getEmail())) {
            throw new RuntimeException("L'email è già registrata.");
        }
        if (isPartitaIvaAlreadyRegistered(aziendaDTO.getDatiPersonali().getPartitaIva())) {
            throw new RuntimeException("La partita IVA è già registrata.");
        }

        Azienda azienda = modelMapper.map(aziendaDTO, Azienda.class);
        azienda.setRuolo("AZIENDA");

        DatiPersonaliAzienda datiPersonali = modelMapper.map(aziendaDTO.getDatiPersonali(), DatiPersonaliAzienda.class);

        azienda.setDatiPersonali(datiPersonali);

        saveAzienda(azienda);
    }


    /**
     * Ottiene un oggetto AziendaDTO dato l'ID dell'azienda.
     *
     * @param aziendaId l'ID dell'azienda da recuperare
     * @return l'oggetto AziendaDTO corrispondente
     * @throws EntityNotFoundException se l'azienda non è trovata
     */

    public AziendaDTO getAziendaDTOById(Long aziendaId) {
        Azienda azienda = aziendaRepository.findById(aziendaId)
                .orElseThrow(() -> new EntityNotFoundException("Azienda non trovata"));

        return modelMapper.map(azienda, AziendaDTO.class);
    }

    /**
     * Ottiene un'azienda dato l'ID.
     *
     * @param id l'ID dell'azienda da recuperare
     * @return l'azienda corrispondente, o null se non è trovata
     */

    public Azienda getAziendaById(Long id) {
        return aziendaRepository.findAziendaById(id);
    }

    /**
     * Aggiunge un programma fedeltà a un'azienda.
     *
     * @param aziendaId          l'ID dell'azienda a cui aggiungere il programma fedeltà
     * @param programmaFedeltaDTO l'oggetto ProgrammaFedeltaDTO contenente i dati del programma fedeltà
     * @throws EntityNotFoundException se l'azienda non è trovata
     */

    public void aggiungiProgrammaFedeltaAdAzienda(Long aziendaId, ProgrammaFedeltaDTO programmaFedeltaDTO) {
        Azienda azienda = aziendaRepository.findById(aziendaId).orElseThrow(() -> new EntityNotFoundException("Azienda non trovata"));
        ProgrammaFedelta nuovoProgramma = gestoreProgrammiFedelta.creaProgrammaFedelta(programmaFedeltaDTO, aziendaId);

        azienda.addProgrammaFedelta(nuovoProgramma);
        aziendaRepository.save(azienda);
    }

    /**
     * Configura un programma punti per un'azienda.
     *
     * @param programmaPuntiDTO l'oggetto ProgrammaPuntiDTO contenente le configurazioni del programma punti
     * @param aziendaId         l'ID dell'azienda per cui configurare il programma punti
     * @param programmaPuntiId  l'ID del programma punti da configurare
     */

    public void configuraProgrammaPunti(ProgrammaPuntiDTO programmaPuntiDTO, Long aziendaId, Long programmaPuntiId) {
        gestoreProgrammiFedelta.configuraProgrammaPunti(programmaPuntiDTO, aziendaId, programmaPuntiId);
    }

    /**
     * Trova un'azienda dato il suo ID.
     *
     * @param aziendaId l'ID dell'azienda da cercare
     * @return l'azienda corrispondente, o null se non è trovata
     */

    public Azienda findById(Long aziendaId) {
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(aziendaId);
        return aziendaOptional.orElse(null);
    }
    /**
     * Ottiene una lista di tutte le aziende.
     *
     * @return una lista di tutte le aziende
     */


    public List<Azienda> getAllAziende() {
        return aziendaRepository.findAll();
    }

    /**
     * Elimina un'azienda dato il suo ID.
     *
     * @param aziendaId l'ID dell'azienda da eliminare
     * @throws EntityNotFoundException se l'azienda non è trovata
     */

    public void eliminaAzienda(Long aziendaId) {
        Azienda azienda = findById(aziendaId);
        if (azienda != null) {
            aziendaRepository.delete(azienda);
        } else {
            throw new EntityNotFoundException("Azienda non trovata");
        }
    }

    /**
     * Aggiorna i dati di un'azienda dato l'ID e un oggetto AziendaDTO.
     *
     * @param aziendaId  l'ID dell'azienda da aggiornare
     * @param aziendaDTO l'oggetto AziendaDTO contenente i nuovi dati dell'azienda
     * @throws EntityNotFoundException se l'azienda non è trovata
     */



    public void aggiornaAzienda(Long aziendaId, AziendaDTO aziendaDTO) {
        Azienda azienda = findById(aziendaId);
        if (azienda != null) {
            modelMapper.map(aziendaDTO, azienda);
            aziendaRepository.save(azienda);
        } else {
            throw new EntityNotFoundException("Azienda non trovata");
        }
    }

    /**
     * Elimina tutte le aziende.
     */

    public void eliminaAziende() {
        aziendaRepository.deleteAll();
    }
}
