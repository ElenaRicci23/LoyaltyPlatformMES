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

    public void registrazioneConDTO(AziendaDTO aziendaDTO) {

         //Verifica se l'azienda ha almeno 2 stabilimenti
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

        DatiPersonaliAzienda datiPersonali = new DatiPersonaliAzienda();
        DatiPersonaliAziendaDTO datiPersonaliDTO = aziendaDTO.getDatiPersonali();
        datiPersonali.setNome(datiPersonaliDTO.getNome());
        datiPersonali.setPartitaIva(datiPersonaliDTO.getPartitaIva());
        datiPersonali.setCodiceUnivoco(datiPersonaliDTO.getCodiceUnivoco());
        datiPersonali.setRagioneSociale(datiPersonaliDTO.getRagioneSociale());
        datiPersonali.setSettore(datiPersonaliDTO.getSettore());
        datiPersonali.setIndirizzo(datiPersonaliDTO.getIndirizzo());
        datiPersonali.setNumeroStabilimenti(datiPersonaliDTO.getNumeroStabilimenti());

        // Crea l'oggetto azienda e imposta i dati
        Azienda azienda = new Azienda();
        azienda.setEmail(aziendaDTO.getEmail());
        azienda.setPassword(aziendaDTO.getPassword());
        azienda.setDatiPersonali(datiPersonali);
        azienda.setRuolo("AZIENDA");
//        azienda.setUtente(azienda);

        // Altri controlli e logica di registrazione, ad esempio, salvare l'azienda nel database
        saveAzienda(azienda);
    }
    public AziendaDTO getAziendaDTOById(Long aziendaId) {
        Azienda azienda = aziendaRepository.findById(aziendaId)
                .orElseThrow(() -> new EntityNotFoundException("Azienda non trovata"));

        // Creare un oggetto AziendaDTO per trasferire i dati
        AziendaDTO aziendaDTO = new AziendaDTO();
        aziendaDTO.setId(azienda.getId());
        aziendaDTO.setEmail(azienda.getEmail());
        aziendaDTO.setPassword(azienda.getPassword());

        // Creare un oggetto DatiPersonaliAziendaDTO per i dati personali
        DatiPersonaliAzienda datiPersonali = azienda.getDatiPersonali();
        DatiPersonaliAziendaDTO datiPersonaliDTO = new DatiPersonaliAziendaDTO();
        datiPersonaliDTO.setNome(datiPersonali.getNome());
        datiPersonaliDTO.setPartitaIva(datiPersonali.getPartitaIva());
        datiPersonaliDTO.setCodiceUnivoco(datiPersonali.getCodiceUnivoco());
        datiPersonaliDTO.setRagioneSociale(datiPersonali.getRagioneSociale());
        datiPersonaliDTO.setSettore(datiPersonali.getSettore());
        datiPersonaliDTO.setIndirizzo(datiPersonali.getIndirizzo());
        datiPersonaliDTO.setNumeroStabilimenti(datiPersonali.getNumeroStabilimenti());

        aziendaDTO.setDatiPersonali(datiPersonaliDTO);

        return aziendaDTO;
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

}

