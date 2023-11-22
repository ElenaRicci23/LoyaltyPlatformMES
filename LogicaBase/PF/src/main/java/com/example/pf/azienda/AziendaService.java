package com.example.pf.azienda;


import com.example.pf.configurazione.ProgrammaPuntiConfigurazioneService;
import com.example.pf.factory.IProgrammaPunti;
import com.example.pf.factory.ProgrammaPunti;
import com.example.pf.model.GestoreProgrammaFedelta;
import com.example.pf.model.ProgrammaFedelta;
import com.example.pf.model.TipoProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AziendaService {
    @Autowired
    private GestoreProgrammaFedelta gestoreProgrammaFedelta;

    private final AziendaRepository aziendaRepository;
    private final ProgrammaPuntiConfigurazioneService configurazioneService;



    @Autowired
    public AziendaService(AziendaRepository aziendaRepository, ProgrammaPuntiConfigurazioneService configurazioneService) {
        this.aziendaRepository = aziendaRepository;
        this.configurazioneService = configurazioneService;
    }

    public List<Azienda> findAllAziende() {
        return aziendaRepository.findAll();
    }

    public Optional<Azienda> findAziendaById(Long id) {
        return aziendaRepository.findById(id);
    }

    public Azienda saveAzienda(Azienda azienda) {
        return aziendaRepository.save(azienda);
    }

    public void deleteAzienda(Long id) {
        aziendaRepository.deleteById(id);
    }


    public Azienda addProgrammaFedelta(Long aziendaId, String nome, TipoProgrammaFedelta tipoProgrammaFedelta, String descrizione) {
        Optional<Azienda> aziendaOpt = findAziendaById(aziendaId);

        if (aziendaOpt.isPresent()) {
            Azienda azienda = aziendaOpt.get();

            // Verifica se la tipologia fornita è valida
            if (tipoProgrammaFedelta != null) {
                IProgrammaPunti programmaPunti = gestoreProgrammaFedelta.creaProgrammaPunti(nome, descrizione, tipoProgrammaFedelta);

                // Verifica se il programma creato è effettivamente di tipo PUNTI
                if (programmaPunti instanceof ProgrammaPunti programmaPuntiPunti) {
                    azienda.addProgrammaFedelta(programmaPuntiPunti);
                    return aziendaRepository.save(azienda);
                } else {
                    // Gestisci il caso in cui non è possibile creare il programma di fedeltà di tipo PUNTI
                    return null;
                }
            } else {
                // Gestisci il caso in cui la tipologia fornita non è valida
                return null;
            }
        } else {
            // Gestisci il caso in cui l'azienda non viene trovata
            return null;
        }
    }


    // Metodo per configurare le regole del programma di punti
    public void configurazioneProgrammaPunti(Long aziendaId, Long programmaFedeltaId, int puntiPerAcquisto, int sogliaPremio) {
        // Trova l'azienda per ID
        Optional<Azienda> aziendaOpt = aziendaRepository.findById(aziendaId);

        if (aziendaOpt.isPresent()) {
            Azienda azienda = aziendaOpt.get();

            // Trova il programma fedeltà per ID all'interno dell'azienda
            Optional<ProgrammaFedelta> programmaFedeltaOpt = azienda.getProgrammiFedelta().stream()
                    .filter(pf -> pf.getId().equals(programmaFedeltaId))
                    .findFirst();

            if (programmaFedeltaOpt.isPresent()) {
                ProgrammaFedelta programmaFedelta = programmaFedeltaOpt.get();

                // Verifica se il programma fedeltà è di tipo ProgrammaPunti
                if (programmaFedelta instanceof IProgrammaPunti) {
                    IProgrammaPunti programmaPunti = (IProgrammaPunti) programmaFedelta;

                    // Ora puoi configurare il programma fedeltà con i valori desiderati
                    configurazioneService.impostaPuntiPerAcquisto(puntiPerAcquisto);
                    configurazioneService.impostaSogliaPremio(sogliaPremio);
                    // Puoi chiamare altri metodi di configurazione se necessario
                } else {
                    // Gestisci il caso in cui il programma fedeltà non è di tipo ProgrammaPunti
                    throw new RuntimeException("Il programma fedeltà non è di tipo ProgrammaPunti.");
                }
            } else {
                // Gestisci il caso in cui la tipologia fornita non è valida
                throw new RuntimeException("La tipologia fornita non è valida.");
            }
        } else {
            // Gestisci il caso in cui l'azienda non viene trovata
            throw new RuntimeException("L'azienda specificata non è stata trovata.");
        }
    }



    public Azienda getAziendaById(Long aziendaId) {
        Optional<Azienda> aziendaOpt = findAziendaById(aziendaId);
        // Gestisci il caso in cui l'azienda non viene trovata
        return aziendaOpt.orElse(null);
    }


}





