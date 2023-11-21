package com.example.pf.azienda;


import com.example.pf.factory.IProgrammaPunti;
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

    public Azienda creaAzienda(Azienda azienda) {
        azienda.setNome(azienda.getNome());
        azienda.setEmail(azienda.getEmail());
        azienda.setCodiceUnivoco(azienda.getCodiceUnivoco());
        azienda.setIndirizzo(azienda.getIndirizzo());
        azienda.setPartitaIva(azienda.getPartitaIva());
        azienda.setNumeroStabilimenti(azienda.getNumeroStabilimenti());
        azienda.setRagioneSociale(azienda.getRagioneSociale());
        azienda.setSettore(azienda.getSettore());
        return aziendaRepository.save(azienda);
    }

    public Azienda addProgrammaFedelta(Long aziendaId, String nome, String descrizione) {
        Optional<Azienda> aziendaOpt = findAziendaById(aziendaId);

        if (aziendaOpt.isPresent()) {
            Azienda azienda = aziendaOpt.get();
            IProgrammaPunti programmaPunti = gestoreProgrammaFedelta.creaProgrammaPunti(nome, descrizione);
            if (programmaPunti instanceof ProgrammaFedelta programmaFedelta) {
                azienda.addProgrammaFedelta(programmaFedelta);
                return aziendaRepository.save(azienda);
            } else {
                // Gestisci il caso in cui non è possibile creare il programma di fedeltà
                return null;
            }
        } else {
            // Gestisci il caso in cui l'azienda non viene trovata
            return null;
        }
    }



}





