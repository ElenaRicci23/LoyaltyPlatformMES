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
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Questa classe fornisce servizi relativi alle tessere dei clienti.
 */
@Service
@Transactional
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
            StoricoPuntiRepository storicoPuntiRepository,
            AziendaRepository aziendaRepository,
            TransazioneRepository transazioneRepository,
            ProgrammaFedeltaRepository programmaFedeltaRepository) {
        this.tesseraRepository = tesseraRepository;
        this.clienteRepository = clienteRepository;
        this.storicoPuntiRepository = storicoPuntiRepository;
        this.aziendaRepository = aziendaRepository;
        this.transazioneRepository = transazioneRepository;
        this.programmaFedeltaRepository = programmaFedeltaRepository;
        this.modelMapper = new ModelMapper();

    }

    /**
     * Crea una nuova tessera per un cliente.
     *
     * @param dataEmissione La data di emissione della tessera
     * @param dataScadenza  La data di scadenza della tessera
     * @param codiceBarre    Il codice a barre associato alla tessera
     * @param clienteId     L'ID del cliente a cui associare la tessera
     * @return La tessera creata
     */

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

    /**
     * Aggiorna una tessera esistente.
     *
     * @param tessera La tessera da aggiornare
     * @return La tessera aggiornata
     */

    public Tessera aggiornaTessera(Tessera tessera) {
        return tesseraRepository.save(tessera);
    }
    /**
     * Trova una tessera per ID.
     *
     * @param tesseraId L'ID della tessera da trovare
     * @return La tessera trovata o null se non esiste
     */

    public Tessera trovaTesseraPerId(Long tesseraId) {
        return tesseraRepository.findById(tesseraId).orElse(null);
    }
    /**
     * Iscrive una tessera a un programma fedeltà specifico di un'azienda.
     *
     * @param tesseraId         L'ID della tessera da iscrivere al programma fedeltà.
     * @param programmaFedeltaId L'ID del programma fedeltà a cui iscrivere la tessera.
     * @param aziendaId         L'ID dell'azienda associata al programma fedeltà.
     * @return True se l'iscrizione è avvenuta con successo, altrimenti False.
     * @throws EntityNotFoundException Se la tessera o il programma fedeltà non sono trovati.
     * @throws EntityNotFoundException Se la tessera è già iscritta a questo programma fedeltà.
     */

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

    /**
     * Aggiunge i punti di un'operazione di transazione a una tessera associata a un programma fedeltà specifico di un'azienda.
     *
     * @param transazione La transazione contenente i dettagli dell'operazione.
     * @return Il numero di punti guadagnati e aggiunti alla tessera.
     * @throws EntityNotFoundException Se la tessera non è trovata o se il programma fedeltà associato all'azienda non è trovato.
     */


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

    /**
     * Restituisce la tessera associata al cliente di una transazione specifica.
     *
     * @param transazione La transazione di cui si desidera ottenere la tessera del cliente associato.
     * @return La tessera del cliente associato alla transazione.
     * @throws EntityNotFoundException Se la transazione non è trovata o se il cliente associato alla transazione non ha una tessera.
     */


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

    /**
     * Ottiene la lista dei premi associati a un programma punti di una tessera, se la tessera è associata al programma punti specificato.
     *
     * @param tesseraId L'ID della tessera di cui si desidera ottenere i premi.
     * @param programmaFedeltaId L'ID del programma fedeltà di cui si desidera ottenere i premi.
     * @return La lista dei premi associati al programma punti della tessera.
     * @throws EntityNotFoundException Se la tessera non è trovata, se il programma punti non è associato alla tessera o se il programma punti non è trovato.
     */



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

    /**
     * Riscatta un premio utilizzando i punti disponibili sulla tessera associata a un programma fedeltà.
     *
     * @param tesseraId L'ID della tessera associata al cliente.
     * @param programmaFedeltaId L'ID del programma fedeltà a cui la tessera è associata.
     * @param premioDTO DTO contenente i dati del premio da riscattare.
     * @return true se il premio è stato riscattato con successo, false altrimenti.
     * @throws EntityNotFoundException Se la tessera o il programma fedeltà non sono trovati, se la tessera non è associata al programma fedeltà specificato, se il premio non è trovato nel programma punti o se i punti sono insufficienti per il riscatto.
     */

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

    /**
     * Registra un'operazione di storico dei punti sulla tessera associata a un programma fedeltà.
     *
     * @param tessera La tessera a cui associare l'operazione di storico dei punti.
     * @param programmaPunti Il programma fedeltà a cui è associata la tessera.
     * @param punti Il numero di punti coinvolti nell'operazione (positivo per punti guadagnati, negativo per punti utilizzati).
     * @param operazione Il tipo di operazione (0 per punti guadagnati, -1 per punti utilizzati).
     */




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

    public void eliminaPunti() {
       tesseraRepository.deleteAll();
    }




}
