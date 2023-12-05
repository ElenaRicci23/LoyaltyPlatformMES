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
            ModelMapper modelMapper // Aggiungi questa riga
    ) {
        this.aziendaRepository = aziendaRepository;
        this.programmaFedeltaRepository = programmaFedeltaRepository;
        this.programmaPuntiRepository = programmaPuntiRepository;
        this.premioRepository = premioRepository;
        this.factory = new ConcreteFactoryProgrammaFedelta();
        this.modelMapper = modelMapper;
    }

    public ProgrammaFedelta creaProgrammaFedelta(ProgrammaFedeltaDTO dto, Long aziendaId) {
        TipoProgrammaFedelta tipo = dto.getTipoProgrammaFedelta();
        String nome = dto.getNome();
        String descrizione = dto.getDescrizione();

        ProgrammaFedelta programmaFedelta = switch (tipo) {
            case PUNTI -> {
                ProgrammaFedelta programmaPunti = factory.creaProgrammaPunti();
                programmaPunti.setNome(nome);
                programmaPunti.setDescrizione(descrizione);
                programmaPunti.setTipoProgrammaFedelta(tipo);
                yield programmaPunti;
            }
            case CASHBACK -> {
                ProgrammaFedelta programmaCashback = factory.creaProgrammaCashback();
                programmaCashback.setNome(nome);
                programmaCashback.setDescrizione(descrizione);
                programmaCashback.setTipoProgrammaFedelta(tipo);

                yield programmaCashback;
            }
            case LIVELLI -> {
                ProgrammaFedelta programmaLivello = factory.creaProgrammiLivello();
                programmaLivello.setNome(nome);
                programmaLivello.setDescrizione(descrizione);
                programmaLivello.setTipoProgrammaFedelta(tipo);

                yield programmaLivello;
            }
            case MEMBERSHIP -> {
                ProgrammaFedelta programmaMembership = factory.creaProgrammiMembership();
                programmaMembership.setNome(nome);
                programmaMembership.setDescrizione(descrizione);
                programmaMembership.setTipoProgrammaFedelta(tipo);
                yield programmaMembership;
            }
            case VIP -> {
                ProgrammaFedelta programmaVip = factory.creaProgrammiVip();
                programmaVip.setNome(nome);
                programmaVip.setDescrizione(descrizione);
                programmaVip.setTipoProgrammaFedelta(tipo);
                yield programmaVip;
            }
            default -> throw new IllegalArgumentException("Tipo di programma fedeltà non supportato");
        };
        // Associa il programmaFedelta all'azienda specifica
        Azienda azienda = trovaAziendaPerId(aziendaId);
        programmaFedelta.setAzienda(azienda);

        // Salva il programmaFedelta nel repository
        salvaProgrammaFedelta(programmaFedelta);

        return programmaFedelta;
    }


    private Azienda trovaAziendaPerId(Long aziendaId) {
        // Supponiamo di avere un repository reale per le aziende
        // Sostituisci 'AziendaRepository' con il nome del tuo repository effettivo
        Azienda azienda = aziendaRepository.findById(aziendaId).orElse(null);

        if (azienda == null) {
            throw new IllegalArgumentException("Azienda non trovata per l'ID fornito: " + aziendaId);
        }

        return azienda;
    }





    public void salvaProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        programmaFedeltaRepository.save(programmaFedelta);

        if (programmaFedelta instanceof ProgrammaPunti programmaPunti) {
            programmaPuntiRepository.save(programmaPunti);
        }
    }

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

    public List<ProgrammaFedelta> getProgrammiFedeltaByAzienda(Azienda azienda) {
        // Implementa la logica per recuperare i programmi fedeltà dell'azienda
        return programmaFedeltaRepository.findByAzienda(azienda);
    }



    private List<Premio> convertiPremiDTOInPremi (List < PremioDTO > premiDTO) {
            List<Premio> premi = new ArrayList<>();
            for (PremioDTO premioDTO : premiDTO) {
                Premio premio = new Premio();
                premio.setNome(premioDTO.getNome());
                premio.setPuntiDelPremio(premioDTO.getPuntiDelPremio());
                premio.setDescrizione(premioDTO.getDescrizione());
                premi.add(premio);
            }
            return premi;
        }

    public List<ProgrammaFedeltaDTO> convertToDTOList(List<ProgrammaFedelta> programmiFedeltaList) {
        return programmiFedeltaList.stream()
                .map(programmaFedelta -> {
                    ProgrammaFedeltaDTO programmaFedeltaDTO = modelMapper.map(programmaFedelta, ProgrammaFedeltaDTO.class);
                    programmaFedeltaDTO.setId(programmaFedelta.getId());
                    return programmaFedeltaDTO;
                })
                .collect(Collectors.toList());
    }


}