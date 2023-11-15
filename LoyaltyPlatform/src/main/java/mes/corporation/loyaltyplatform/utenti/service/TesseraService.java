package mes.corporation.loyaltyplatform.utenti.service;

import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.model.Cliente;
import mes.corporation.loyaltyplatform.utenti.model.Tessera;
import mes.corporation.loyaltyplatform.utenti.programmaPunti.PuntiInsufficientiException;
import mes.corporation.loyaltyplatform.utenti.repo.AziendaRepository;
import mes.corporation.loyaltyplatform.utenti.repo.TesseraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TesseraService {

    @Autowired
    private TesseraRepository tesseraRepository;

    @Autowired
    private AziendaRepository aziendaRepository;

    // Crea una nuova tessera per un cliente specifico
    public Tessera creaTessera(Cliente cliente) {
        Tessera nuovaTessera = new Tessera(cliente);
        return tesseraRepository.save(nuovaTessera);
    }

    // Recupera la tessera di un cliente tramite l'ID del cliente
    public Tessera getTesseraByClienteId(Long clienteId) {
        return tesseraRepository.findByProprietario_Id(clienteId).orElse(null);
    }


    // Modifica il metodo per accettare l'ID dell'azienda invece dell'oggetto Azienda
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


