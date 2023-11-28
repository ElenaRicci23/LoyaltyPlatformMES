package mes.corporation.loyaltyplatform.tessera;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import mes.corporation.loyaltyplatform.DTO.AcquistoDTO;
import mes.corporation.loyaltyplatform.DTO.PremioDTO;
import mes.corporation.loyaltyplatform.cliente.Cliente;
import mes.corporation.loyaltyplatform.factory.ProgrammaPunti;
import mes.corporation.loyaltyplatform.model.Premio;
import mes.corporation.loyaltyplatform.model.PremioRepository;
import mes.corporation.loyaltyplatform.model.ProgrammaFedelta;
import mes.corporation.loyaltyplatform.repo.ProgrammaFedeltaRepository;
import mes.corporation.loyaltyplatform.transazione.TransazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TesseraService {

    @Autowired
    private TesseraRepository tesseraRepository;
    @Autowired
    private ProgrammaFedeltaRepository programmaFedeltaRepository;
    @Autowired
    private PremioRepository premioRepository;
    @Autowired
    private TransazioneService transazioneService;



    @Autowired
    public TesseraService(TesseraRepository tesseraRepository) {
        this.tesseraRepository = tesseraRepository;
    }


    public Tessera creaTessera(Cliente cliente) {
        Tessera nuovaTessera = new Tessera(cliente);
        nuovaTessera.setCliente(cliente); // Imposta il cliente nella tessera
        return tesseraRepository.save(nuovaTessera);
    }

    public boolean iscriviTesseraAProgrammaFedelta(Long tesseraId, Long programmaFedeltaId, Long aziendaId) {
        Tessera tessera = tesseraRepository.findById(tesseraId).orElse(null);
        ProgrammaFedelta programmaFedelta = programmaFedeltaRepository.findById(programmaFedeltaId).orElse(null);

        if (tessera != null && programmaFedelta != null) {
            // Verifica se la tessera contiene il programma fedeltà
            if (tessera.getProgrammiFedelta().contains(programmaFedelta)) {
                // Il cliente è già iscritto, restituisci un messaggio o lancia un'eccezione
                throw new EntityNotFoundException("La tessera è già iscritta a questo programma fedeltà.");
            } else {
                // Aggiungi il programma fedeltà alla tessera
                tessera.addProgrammaFedelta(programmaFedelta);

                // Aggiungi i punti iniziali associati a questo programma fedeltà
                tessera.getPuntiProgrammaPunti().put((ProgrammaPunti) programmaFedelta, 10);

                // Salva la tessera per aggiornare l'associazione con il programma fedeltà e i punti
                tesseraRepository.save(tessera);

                // Restituisci true per indicare che l'iscrizione è avvenuta con successo
                return true;
            }
        } else {
            // Gestisci il caso in cui la tessera o il programma fedeltà non esistono
            throw new EntityNotFoundException("Tessera o programma fedeltà non trovati.");
        }
    }

    @Transactional
    public List<Object[]> findProgrammiPuntiByTesseraId(Long tesseraId) {
        return tesseraRepository.findProgrammiPuntiByTesseraId(tesseraId);
    }

    public void aggiungiPuntiALProgrammaPunti(AcquistoDTO acquistoDTO) {
        transazioneService.aggiungiPuntiALProgrammaPunti(acquistoDTO);
    }




    @Transactional
    public ResponseEntity<String> riscattaPremio(Long tesseraId, PremioDTO premioDTO) {
        // Recupera la Tessera tramite l'ID
        Tessera tessera = tesseraRepository.findById(tesseraId).orElse(null);

        if (tessera != null) {
            // Trova il programma associato al premio
            ProgrammaPunti programmaPunti = findProgrammaPuntiByPremio(tessera, premioDTO);

            if (programmaPunti != null) {
                // Verifica se il cliente ha punti sufficienti per riscattare il premio
                int puntiRichiesti = premioDTO.getPuntiRichiesti();
                int puntiAttuali = tessera.getPuntiProgrammaPunti().get(programmaPunti);

                if (puntiAttuali >= puntiRichiesti) {
                    // Sottrai i punti richiesti dalla tessera
                    tessera.getPuntiProgrammaPunti().put(programmaPunti, puntiAttuali - puntiRichiesti);

                    // Salva la tessera per persistere le modifiche
                    tesseraRepository.save(tessera);

                    // Ora puoi registrare il premio riscattato nel tuo sistema, se necessario

                    // Restituisci una risposta di successo
                    return ResponseEntity.ok("Premio riscattato con successo.");
                } else {
                    // Gestisci il caso in cui il cliente non ha punti sufficienti
                    return ResponseEntity.badRequest().body("Punti insufficienti per riscattare il premio.");
                }
            } else {
                // Gestisci il caso in cui il premio non è associato a un programma punti valido
                return ResponseEntity.badRequest().body("Premio non valido o non associato a un programma punti.");
            }
        } else {
            // Gestisci il caso in cui la Tessera con l'ID fornito non viene trovata
            return ResponseEntity.notFound().build();
        }
    }

    private ProgrammaPunti findProgrammaPuntiByPremio(Tessera tessera, PremioDTO premioDTO) {
        // Recupera il premio tramite il nome o l'ID del premio dal DTO, a seconda della tua logica
        Premio premio = premioRepository.findPremioByNome(premioDTO.getNome());

        if (premio != null) {
            // Ora puoi accedere al programma punti associato al premio direttamente
            return premio.getProgrammaPunti();
        }

        return null; // Nessun programma punti associato al premio
    }



}
