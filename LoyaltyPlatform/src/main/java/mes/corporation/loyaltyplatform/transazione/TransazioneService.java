package mes.corporation.loyaltyplatform.transazione;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import mes.corporation.loyaltyplatform.DTO.AcquistoDTO;
import mes.corporation.loyaltyplatform.factory.ProgrammaPunti;
import mes.corporation.loyaltyplatform.storico.StoricoPunti;
import mes.corporation.loyaltyplatform.storico.StoricoPuntiRepository;
import mes.corporation.loyaltyplatform.tessera.TesseraRepository;
import mes.corporation.loyaltyplatform.tessera.Tessera;
import mes.corporation.loyaltyplatform.tessera.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class TransazioneService {

    @Autowired
    private TesseraRepository tesseraRepository;
    @Autowired
    private StoricoPuntiRepository storicoPuntiRepository;

    @Transactional
    public void aggiungiPuntiALProgrammaPunti(AcquistoDTO acquistoDTO) {
        Long tesseraId = acquistoDTO.getTesseraId();
        String nomeAzienda = acquistoDTO.getNomeAzienda();
        BigDecimal importo = acquistoDTO.getImporto();

        // Recupera la Tessera tramite l'ID
        Tessera tessera = tesseraRepository.findById(tesseraId).orElse(null);

        if (tessera != null) {
            // Trova il programma associato al nome dell'Azienda specificato
            Optional<Map.Entry<ProgrammaPunti, Integer>> programmaEntry = tessera.getPuntiProgrammaPunti().entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().getAzienda().getNome().equals(nomeAzienda))
                    .findFirst();

            if (programmaEntry.isPresent()) {
                ProgrammaPunti programmaPunti = programmaEntry.get().getKey();
                int puntiAttuali = programmaEntry.get().getValue();

                // Calcola i punti guadagnati in base all'importo e al tasso di conversione del programma
                BigDecimal tassoConversione = BigDecimal.valueOf(programmaPunti.getTassoConversione());
                int puntiGuadagnati = importo.multiply(tassoConversione).intValue();

                // Aggiorna i punti per il programma
                tessera.getPuntiProgrammaPunti().put(programmaPunti, puntiAttuali + puntiGuadagnati);

                // Salva la Tessera per persistere le modifiche
                tesseraRepository.save(tessera);

                // Ora puoi registrare lo storico dei punti
                StoricoPunti storicoPunti = new StoricoPunti();
                storicoPunti.setTessera(tessera);
                storicoPunti.setImporto(importo.doubleValue());
                storicoPunti.setPuntiAggiunti(puntiGuadagnati);
                storicoPunti.setData(LocalDateTime.now());

                // Salva lo storico dei punti
                storicoPuntiRepository.save(storicoPunti);
            } else {
                // Gestisci il caso in cui il nome dell'Azienda specificato non viene trovato
                throw new EntityNotFoundException("Programma per l'Azienda non trovato.");
            }
        } else {
            // Gestisci il caso in cui la Tessera con l'ID fornito non viene trovata
            throw new EntityNotFoundException("Tessera non trovata.");
        }
    }

}

