package mes.corporation.loyaltyplatform.azienda;

import jakarta.persistence.EntityNotFoundException;
import mes.corporation.loyaltyplatform.DTO.PremioDTO;
import mes.corporation.loyaltyplatform.DTO.ProgrammaFedeltaDTO;
import mes.corporation.loyaltyplatform.DTO.ProgrammaPuntiDTO;
import mes.corporation.loyaltyplatform.factory.GestoreProgrammiFedelta;
import mes.corporation.loyaltyplatform.factory.ProgrammaPunti;
import mes.corporation.loyaltyplatform.model.Premio;
import mes.corporation.loyaltyplatform.model.PremioRepository;
import mes.corporation.loyaltyplatform.model.ProgrammaFedelta;
import mes.corporation.loyaltyplatform.repo.ProgrammaPuntiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Questa classe rappresenta il servizio per la gestione delle entità Azienda.
 */
@Service
public class AziendaService {
    @Autowired
    private GestoreProgrammiFedelta gestoreProgrammiFedelta;

    private final AziendaRepository aziendaRepository;
    private final PremioRepository premioRepository;

    @Autowired
    public AziendaService(AziendaRepository aziendaRepository, PremioRepository premioRepository) {
        this.aziendaRepository = aziendaRepository;
        this.premioRepository = premioRepository;
    }

    /**
     * Recupera una lista di tutte le entità Azienda.
     *
     * @return Una lista di entità Azienda.
     */
    public List<Azienda> findAllAziende() {
        return aziendaRepository.findAll();
    }

    /**
     * Trova un'entità Azienda dal suo ID.
     *
     * @param id L'ID dell'entità Azienda da trovare.
     * @return Un Optional contenente l'entità Azienda trovata o vuoto se non è stata trovata.
     */
    public Optional<Azienda> findAziendaById(Long id) {
        return aziendaRepository.findById(id);
    }

    /**
     * Salva un'entità Azienda.
     *
     * @param azienda L'entità Azienda da salvare.
     * @return L'entità Azienda salvata.
     */
    public Azienda saveAzienda(Azienda azienda) {
        return aziendaRepository.save(azienda);
    }

    /**
     * Elimina un'entità Azienda dal suo ID.
     *
     * @param id L'ID dell'entità Azienda da eliminare.
     */
    public void deleteAzienda(Long id) {
        aziendaRepository.deleteById(id);
    }

    /**
     * Aggiunge un ProgrammaFedelta a un'entità Azienda.
     *
     * @param aziendaId        L'ID dell'entità Azienda.
     * @param programmaFedeltaDTO Il ProgrammaFedeltaDTO contenente i dettagli del programma.
     * @return L'entità Azienda aggiornata.
     */
    public Azienda addProgrammaFedelta(Long aziendaId, ProgrammaFedeltaDTO programmaFedeltaDTO) {
        Optional<Azienda> aziendaOpt = findAziendaById(aziendaId);

        if (aziendaOpt.isPresent()) {
            Azienda azienda = aziendaOpt.get();
            ProgrammaFedelta nuovoProgramma = gestoreProgrammiFedelta.creaProgrammaFedelta(programmaFedeltaDTO);
            nuovoProgramma.setTipoProgrammaFedelta(programmaFedeltaDTO.getTipoProgrammaFedelta());
            nuovoProgramma.setNome(programmaFedeltaDTO.getNome());
            nuovoProgramma.setDescrizione(programmaFedeltaDTO.getDescrizione());
            gestoreProgrammiFedelta.salvaProgrammaFedelta(nuovoProgramma);
            azienda.addProgrammaFedelta(nuovoProgramma);
            return aziendaRepository.save(azienda);
        } else {
            return null;
        }
    }

    /**
     * Configura un ProgrammaPunti per un'entità Azienda.
     *
     * @param aziendaId        L'ID dell'entità Azienda.
     * @param programmaPuntiDTO Il ProgrammaPuntiDTO contenente i dettagli del programma.
     * @return L'entità Azienda aggiornata.
     * @throws EntityNotFoundException Se l'azienda non viene trovata.
     */
    public Azienda configuraProgrammaPunti(Long aziendaId, ProgrammaPuntiDTO programmaPuntiDTO) {
        Optional<Azienda> aziendaOpt = findAziendaById(aziendaId);

        if (aziendaOpt.isPresent()) {
            Azienda azienda = aziendaOpt.get();
            gestoreProgrammiFedelta.configuraProgrammaPunti(aziendaId, programmaPuntiDTO);
            return aziendaRepository.save(azienda);
        } else {
            throw new EntityNotFoundException("Azienda non trovata con ID: " + aziendaId);
        }
    }


    /**
     * Aggiunge un premio a un ProgrammaPunti associato a un'entità Azienda.
     *
     * @param aziendaId        L'ID dell'entità Azienda.
     * @param programmaPuntiId L'ID del ProgrammaPunti a cui aggiungere il premio.
     * @param premioDTO        Il PremioDTO contenente i dettagli del premio da aggiungere.
     * @return L'entità Azienda aggiornata.
     */
    public Azienda aggiungiPremio(Long aziendaId, Long programmaPuntiId, PremioDTO premioDTO) {
        Optional<Azienda> aziendaOpt = findAziendaById(aziendaId);

        if (aziendaOpt.isPresent()) {
            Azienda azienda = aziendaOpt.get();

            ProgrammaPunti programmaPunti = azienda.getProgrammiPunti().stream()
                    .filter(pp -> pp.getId().equals(programmaPuntiId))
                    .findFirst()
                    .orElse(null);

            if (programmaPunti != null) {
                Premio nuovoPremio = new Premio();
                nuovoPremio.setNome(premioDTO.getNome());
                nuovoPremio.setDescrizione(premioDTO.getDescrizione());
                nuovoPremio.setPuntiDelPremio(premioDTO.getPuntiRichiesti());
                nuovoPremio.setProgrammaPunti(programmaPunti);
                Premio premioSalvato = premioRepository.save(nuovoPremio);
                programmaPunti.aggiungiPremio(premioSalvato);
                return aziendaRepository.save(azienda);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Modifica un premio associato a un ProgrammaPunti di un'entità Azienda.
     *
     * @param aziendaId        L'ID dell'entità Azienda.
     * @param programmaPuntiId L'ID del ProgrammaPunti a cui appartiene il premio da modificare.
     * @param premioId         L'ID del premio da modificare.
     * @param premioDTO        Il PremioDTO contenente i nuovi dettagli del premio.
     * @return L'entità Azienda aggiornata.
     */
    public Azienda modificaPremio(Long aziendaId, Long programmaPuntiId, Long premioId, PremioDTO premioDTO) {
        Optional<Azienda> aziendaOpt = findAziendaById(aziendaId);

        if (aziendaOpt.isPresent()) {
            Azienda azienda = aziendaOpt.get();

            ProgrammaPunti programmaPunti = azienda.getProgrammiPunti().stream()
                    .filter(pp -> pp.getId().equals(programmaPuntiId))
                    .findFirst()
                    .orElse(null);

            if (programmaPunti != null) {
                Premio premioDaModificare = programmaPunti.getPremi().stream()
                        .filter(p -> p.getId().equals(premioId))
                        .findFirst()
                        .orElse(null);

                if (premioDaModificare != null) {
                    premioDaModificare.setNome(premioDTO.getNome());
                    premioDaModificare.setDescrizione(premioDTO.getDescrizione());
                    premioDaModificare.setPuntiDelPremio(premioDTO.getPuntiRichiesti());
                    return aziendaRepository.save(azienda);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Rimuove un premio da un ProgrammaPunti associato a un'entità Azienda.
     *
     * @param aziendaId        L'ID dell'entità Azienda.
     * @param programmaPuntiId L'ID del ProgrammaPunti a cui appartiene il premio da rimuovere.
     * @param premioId         L'ID del premio da rimuovere.
     * @param premioDTO        Il PremioDTO contenente i dettagli del premio da rimuovere (non utilizzato).
     * @return L'entità Azienda aggiornata.
     */
    public Azienda rimuoviPremio(Long aziendaId, Long programmaPuntiId, Long premioId, PremioDTO premioDTO) {
        Optional<Azienda> aziendaOpt = findAziendaById(aziendaId);

        if (aziendaOpt.isPresent()) {
            Azienda azienda = aziendaOpt.get();

            ProgrammaPunti programmaPunti = azienda.getProgrammiPunti().stream()
                    .filter(pp -> pp.getId().equals(programmaPuntiId))
                    .findFirst()
                    .orElse(null);

            if (programmaPunti != null) {
                programmaPunti.rimuoviPremio(premioId);
                return aziendaRepository.save(azienda);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Ottiene una lista di Premi associati a un'entità Azienda.
     *
     * @param aziendaId L'ID dell'entità Azienda.
     * @return Una lista di Premi associati all'entità Azienda.
     */
    public List<Premio> getPremiAzienda(Long aziendaId) {
        Optional<Azienda> aziendaOpt = aziendaRepository.findById(aziendaId);

        if (aziendaOpt.isPresent()) {
            Azienda azienda = aziendaOpt.get();
            List<Premio> premi = new ArrayList<>();

            for (ProgrammaPunti programmaPunti : azienda.getProgrammiPunti()) {
                premi.addAll(programmaPunti.getPremi());
            }

            return premi;
        } else {
            return Collections.emptyList();
        }
    }

}
