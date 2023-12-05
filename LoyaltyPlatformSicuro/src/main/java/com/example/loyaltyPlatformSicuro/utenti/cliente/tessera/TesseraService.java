package com.example.loyaltyPlatformSicuro.utenti.cliente.tessera;

import com.example.loyaltyPlatformSicuro.DTO.PremioDTO;
import com.example.loyaltyPlatformSicuro.DTO.StoricoPuntiDTO;
import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.Configurazione.Premio;
import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.ProgrammaPunti;
import com.example.loyaltyPlatformSicuro.programmiFedelta.repo.ProgrammaFedeltaRepository;
import com.example.loyaltyPlatformSicuro.utenti.azienda.AziendaRepository;
import com.example.loyaltyPlatformSicuro.utenti.cliente.Cliente;
import com.example.loyaltyPlatformSicuro.utenti.cliente.ClienteRepository;
import com.example.loyaltyPlatformSicuro.utenti.transazione.Transazione;
import com.example.loyaltyPlatformSicuro.utenti.transazione.TransazioneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TesseraService {



    private final TesseraRepository tesseraRepository;
    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    private final StoricoPuntiRepository storicoPuntiRepository;
    private final AziendaRepository aziendaRepository;
    private final TransazioneRepository transazioneRepository;
    private final ProgrammaFedeltaRepository programmaFedeltaRepository;

    @Autowired
    public TesseraService(
            TesseraRepository tesseraRepository,
            ClienteRepository clienteRepository,
            ModelMapper modelMapper, StoricoPuntiRepository storicoPuntiRepository, AziendaRepository aziendaRepository,
            TransazioneRepository transazioneRepository,
            ProgrammaFedeltaRepository programmaFedeltaRepository) {
        this.tesseraRepository = tesseraRepository;
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.storicoPuntiRepository = storicoPuntiRepository;
        this.aziendaRepository = aziendaRepository;
        this.transazioneRepository = transazioneRepository;
        this.programmaFedeltaRepository = programmaFedeltaRepository;
    }

    public Tessera creaTessera(LocalDate dataEmissione, LocalDate dataScadenza, String codiceBarre, Long clienteId) {
        Tessera tessera = new Tessera();
        tessera.setDataEmissione(dataEmissione);
        tessera.setDataScadenza(dataScadenza);
        tessera.setCodiceBarre();

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente non trovato con ID: " + clienteId));

        tessera.setCliente(cliente);
        tesseraRepository.save(tessera);
        return tessera;
    }

    public Tessera aggiornaTessera(Tessera tessera) {
        return tesseraRepository.save(tessera);
    }

    public Tessera trovaTesseraPerId(Long tesseraId) {
        return tesseraRepository.findById(tesseraId).orElse(null);
    }

    public boolean iscriviTesseraAProgrammaFedelta(Long tesseraId, Long programmaFedeltaId, Long aziendaId) {
        Tessera tessera = tesseraRepository.findById(tesseraId).orElse(null);
        ProgrammaFedelta programmaFedelta = programmaFedeltaRepository.findById(programmaFedeltaId).orElse(null);

        if (tessera != null && programmaFedelta != null) {
            if (tessera.getProgrammiFedelta().contains(programmaFedelta)) {
                throw new EntityNotFoundException("La tessera è già iscritta a questo programma fedeltà.");
            } else {
                tessera.addProgrammaFedelta(programmaFedelta);
                tessera.getPuntiProgrammaPunti().put((ProgrammaPunti) programmaFedelta, 10);
                tesseraRepository.save(tessera);
                return true;
            }
        } else {
            throw new EntityNotFoundException("Tessera o programma fedeltà non trovati.");
        }
    }

    public int aggiungiPuntiProgrammaFedelta(Transazione transazione) {
        Tessera tessera = getTesseraClienteByTransazione(transazione);
        if (tessera != null) {
            String nomeAzienda = transazione.getNomeAzienda();
            double importo = transazione.getImporto();

            Optional<Map.Entry<ProgrammaPunti, Integer>> programmaEntry = tessera.getPuntiProgrammaPunti().entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().getAzienda().getDatiPersonali().getNome().equals(nomeAzienda))
                    .findFirst();

            if (programmaEntry.isPresent()) {
                ProgrammaPunti programmaPunti = programmaEntry.get().getKey();
                int puntiAttuali = programmaEntry.get().getValue();

                double tassoConversione = (programmaPunti.getTassoConversione());
                int puntiGuadagnati = (int) (importo * tassoConversione);

                tessera.getPuntiProgrammaPunti().put(programmaPunti, puntiAttuali + puntiGuadagnati);
                tesseraRepository.save(tessera);
                registraStoricoPunti(tessera, programmaPunti, puntiGuadagnati, 0); // 0 indica punti guadagnati

                return puntiGuadagnati;
            } else {
                throw new EntityNotFoundException("Programma per l'Azienda non trovato.");
            }
        } else {
            throw new EntityNotFoundException("Tessera non trovata.");
        }
    }


    private Tessera getTesseraClienteByTransazione(Transazione transazione) {
        Optional<Transazione> transazioneOptional = transazioneRepository.findById(transazione.getId());

        if (transazioneOptional.isPresent()) {
            Transazione transazioneTrovata = transazioneOptional.get();
            Cliente cliente = transazioneTrovata.getCliente();
            if (cliente != null) {
                return cliente.getTessera();
            } else {
                throw new EntityNotFoundException("Il cliente associato alla transazione non ha una tessera.");
            }
        } else {
            throw new EntityNotFoundException("Transazione non trovata con ID: " + transazione.getId());
        }
    }

    public List<Premio> getPremiPerProgrammaPuntiSeAssociato(Long tesseraId, Long programmaFedeltaId) {
        Tessera tessera = tesseraRepository.findById(tesseraId).orElse(null);

        if (tessera != null) {
            ProgrammaPunti programmaPunti = programmaFedeltaRepository.findById(programmaFedeltaId)
                    .filter(ProgrammaPunti.class::isInstance)
                    .map(ProgrammaPunti.class::cast)
                    .orElse(null);

            if (programmaPunti != null && tessera.getProgrammiFedelta().contains(programmaPunti)) {
                return programmaPunti.getPremi();
            } else {
                throw new EntityNotFoundException("Tessera non associata al programma punti con ID: " + programmaFedeltaId);
            }
        } else {
            throw new EntityNotFoundException("Tessera non trovata con ID: " + tesseraId);
        }
    }
    public boolean riscattaPremio(Long tesseraId, Long programmaFedeltaId, PremioDTO premioDTO) {
        Tessera tessera = tesseraRepository.findById(tesseraId).orElse(null);
        ProgrammaPunti programmaPunti = programmaFedeltaRepository.findById(programmaFedeltaId)
                .filter(ProgrammaPunti.class::isInstance)
                .map(ProgrammaPunti.class::cast)
                .orElse(null);

        if (tessera != null && programmaPunti != null) {
            // Verifica se la tessera è associata al programma punti
            if (!tessera.getProgrammiFedelta().contains(programmaPunti)) {
                throw new EntityNotFoundException("La tessera non è associata a questo programma fedeltà.");
            }

            // Ottieni i dati dal DTO
            String nomePremio = premioDTO.getNome();
            String descrizionePremio = premioDTO.getDescrizione();
            int puntiDelPremio = premioDTO.getPuntiDelPremio();

            // Cerca il premio specifico all'interno del programma punti utilizzando i dati forniti dall'utente
            Optional<Premio> premioOptional = programmaPunti.getPremi().stream()
                    .filter(premio -> premio.getNome().equals(nomePremio) && premio.getDescrizione().equals(descrizionePremio) && premio.getPuntiDelPremio() == puntiDelPremio)
                    .findFirst();

            if (premioOptional.isPresent()) {
                Premio premio = premioOptional.get();
                int puntiNecessari = premio.getPuntiDelPremio();

                // Verifica se la tessera ha abbastanza punti
                int puntiAttuali = tessera.getPuntiProgrammaPunti().getOrDefault(programmaPunti, 0);
                if (puntiAttuali >= puntiNecessari) {
                    // Sottrai i punti necessari
                    tessera.getPuntiProgrammaPunti().put(programmaPunti, puntiAttuali - puntiNecessari);
                    tesseraRepository.save(tessera);
                    registraStoricoPunti(tessera, programmaPunti, puntiNecessari, -1); // -1 indica punti utilizzati

                    return true;
                } else {
                    throw new EntityNotFoundException("Punti insufficienti per il riscatto.");
                }
            } else {
                throw new EntityNotFoundException("Premio non trovato nel programma punti.");
            }
        } else {
            throw new EntityNotFoundException("Tessera o programma fedeltà non trovati.");
        }
    }


    private void registraStoricoPunti(Tessera tessera, ProgrammaPunti programmaPunti, int punti, int operazione) {
        StoricoPunti storicoPunti = new StoricoPunti();
        storicoPunti.setTessera(tessera);
        storicoPunti.setProgrammaFedelta(programmaPunti);
        storicoPunti.setPunti(punti);
        storicoPunti.setOperazione(operazione);
        storicoPunti.setDataOperazione(LocalDateTime.now());

        storicoPuntiRepository.save(storicoPunti);
    }


    public List<StoricoPuntiDTO> getStoricoPuntiByTesseraId(Long tesseraId) {
        List<StoricoPunti> storicoPuntiList = storicoPuntiRepository.findByTesseraId(tesseraId);

        // Mappa gli oggetti StoricoPunti in StoricoPuntiDTO
        return storicoPuntiList.stream()
                .map(this::mapToStoricoPuntiDTO)
                .collect(Collectors.toList());
    }

    private StoricoPuntiDTO mapToStoricoPuntiDTO(StoricoPunti storicoPunti) {
        return modelMapper.map(storicoPunti, StoricoPuntiDTO.class);
    }



}
