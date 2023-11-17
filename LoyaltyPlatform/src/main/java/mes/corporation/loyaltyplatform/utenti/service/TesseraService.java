package mes.corporation.loyaltyplatform.utenti.service;

import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.model.Cliente;
import mes.corporation.loyaltyplatform.utenti.model.Tessera;
import mes.corporation.loyaltyplatform.programmaPunti.PuntiInsufficientiException;
import mes.corporation.loyaltyplatform.utenti.repo.AziendaRepository;
import mes.corporation.loyaltyplatform.utenti.repo.TesseraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;



/**
 * Servizio per la gestione delle tessere dei clienti.
 */
@Service
public class TesseraService {

    @Autowired
    private TesseraRepository tesseraRepository;

    @Autowired
    private AziendaRepository aziendaRepository;

    /**
     * Crea una nuova tessera per un cliente specifico.
     *
     * @param cliente Il cliente per cui creare la tessera.
     * @return La nuova tessera creata.
     */
    public Tessera creaTessera(Cliente cliente) {
        Tessera nuovaTessera = new Tessera(cliente);
        return tesseraRepository.save(nuovaTessera);
    }

    /**
     * Recupera la tessera di un cliente tramite l'ID del cliente.
     *
     * @param clienteId L'ID del cliente.
     * @return La tessera del cliente se trovata, altrimenti null.
     */
    public Tessera getTesseraByClienteId(Long clienteId) {
        return tesseraRepository.findByProprietario_Id(clienteId).orElse(null);
    }

    /**
     * Aggiunge punti alla tessera di un cliente da parte di un'azienda.
     *
     * @param clienteId L'ID del cliente.
     * @param punti     Il numero di punti da aggiungere.
     * @param aziendaId L'ID dell'azienda che aggiunge i punti.
     * @throws Exception Lanciata se la tessera non è trovata per il cliente o l'azienda non è trovata.
     */
    public void aggiungiPuntiATessera(Long clienteId, int punti, Long aziendaId) throws Exception {
        Tessera tessera = getTesseraByClienteId(clienteId);
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(aziendaId);

        if (tessera != null && aziendaOptional.isPresent()) {
            Azienda azienda = aziendaOptional.get();
            tessera.aggiungiPunti(azienda, punti);
            tesseraRepository.save(tessera);
        } else {
            throw new Exception("Tessera non trovata per il cliente con ID: " + clienteId + " o azienda non trovata con ID: " + aziendaId);
        }
    }

    /**
     * Riscatta punti dalla tessera di un cliente per un'azienda.
     *
     * @param clienteId L'ID del cliente.
     * @param punti     Il numero di punti da riscattare.
     * @param azienda   L'azienda per cui riscattare i punti.
     * @throws PuntiInsufficientiException Lanciata se i punti disponibili sulla tessera sono insufficienti.
     */
    public void riscattaPuntiDaTessera(Long clienteId, int punti, Azienda azienda) throws PuntiInsufficientiException {
        Tessera tessera = getTesseraByClienteId(clienteId);
        if (tessera != null) {
            tessera.scalaPunti(azienda, punti);
            tesseraRepository.save(tessera);
        } else {
            throw new PuntiInsufficientiException("Tessera non trovata o punti insufficienti per il cliente con ID: " + clienteId);
        }
    }
}
