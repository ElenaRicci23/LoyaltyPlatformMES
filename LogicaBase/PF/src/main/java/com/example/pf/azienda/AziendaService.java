package com.example.pf.azienda;



import com.example.pf.factory.IProgrammaPunti;
import com.example.pf.factory.ProgrammaPunti;
import com.example.pf.model.GestoreProgrammaFedelta;
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

    @Autowired
    public AziendaService(AziendaRepository aziendaRepository) {
        this.aziendaRepository = aziendaRepository;
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

//    public Azienda configureProgrammaFedelta(Long aziendaId, double tassoConversione, /* altri parametri personalizzati */) {
//        Optional<Azienda> aziendaOpt = findAziendaById(aziendaId);
//
//        if (aziendaOpt.isPresent()) {
//            Azienda azienda = aziendaOpt.get();
//            // Trova il programma fedeltà da configurare (potresti avere più programmi)
//            // Ad esempio, puoi ciclare attraverso i programmi esistenti o avere un metodo per selezionare il programma desiderato
//
//            // Configura il programma fedeltà con i parametri desiderati
//            if (azienda.getProgrammaFedelta() != null) {
//                ProgrammaPunti programmaPunti = azienda.getProgrammaFedelta();
//                programmaPunti.setTassoConversione(tassoConversione);
//                // Imposta altri parametri personalizzati, se necessario
//                // programmaPunti.setAltroParametro(altroValore);
//                // ...
//            }
//
//            // Salva l'azienda con il programma fedeltà configurato
//            return aziendaRepository.save(azienda);
//        } else {
//            // Gestisci il caso in cui l'azienda non viene trovata
//            return null;
//        }
//    }

}





