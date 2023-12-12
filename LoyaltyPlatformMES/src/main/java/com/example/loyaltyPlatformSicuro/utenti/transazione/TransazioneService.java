package com.example.loyaltyPlatformSicuro.utenti.transazione;

import com.example.loyaltyPlatformSicuro.DTO.AcquistoDTO;
import com.example.loyaltyPlatformSicuro.integrazioni.PagamentoSimulato;
import com.example.loyaltyPlatformSicuro.integrazioni.SistemaPagamento;
import com.example.loyaltyPlatformSicuro.utenti.cliente.Cliente;
import com.example.loyaltyPlatformSicuro.utenti.cliente.ClienteService;
import com.example.loyaltyPlatformSicuro.utenti.cliente.tessera.TesseraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Servizio per la gestione delle transazioni.
 */

@Service
@Lazy
public class TransazioneService {
    @Autowired
    private SistemaPagamento sistemaPagamento;

    @Autowired
    private final ClienteService clienteService;

    @Autowired
    private final TesseraRepository tesseraRepository;

    @Autowired
    private TransazioneRepository transazioneRepository;

    @Autowired
    public TransazioneService(SistemaPagamento sistemaPagamento, ClienteService clienteService, TesseraRepository tesseraRepository, TransazioneRepository transazioneRepository) {
        this.sistemaPagamento = sistemaPagamento;
        this.clienteService = clienteService;
        this.tesseraRepository = tesseraRepository;
        this.transazioneRepository = transazioneRepository;
    }

    /**
     * Effettua un acquisto per un cliente e registra la transazione.
     *
     * @param acquistoDTO I dettagli dell'acquisto da effettuare.
     * @return La simulazione del pagamento.
     * @throws RuntimeException se il cliente non viene trovato.
     */

    public PagamentoSimulato effettuaAcquistoCliente(AcquistoDTO acquistoDTO) {
        Long clienteId = acquistoDTO.getClienteId();
        Cliente cliente = clienteService.getClienteById(clienteId);

        if (cliente == null) {
            throw new RuntimeException("Cliente non trovato.");
        }

        double importo = acquistoDTO.getImportoProdotto();
        String nomeAzienda = acquistoDTO.getNomeAziendaAcquisto();

        PagamentoSimulato pagamento = sistemaPagamento.effettuaPagamento(importo, nomeAzienda);

        Transazione transazione = new Transazione();
        transazione.setDataTransazione(LocalDate.parse(pagamento.getDataTransazione()));
        transazione.setImporto(pagamento.getImportoPagato());
        transazione.setNomeAzienda(pagamento.getNomeAzienda());
        transazione.setCliente(cliente);

        transazioneRepository.save(transazione);

        return pagamento;
    }

    /**
     * Restituisce l'ultima transazione effettuata da un cliente.
     *
     * @param clienteId L'ID del cliente.
     * @return L'ultima transazione del cliente.
     * @throws EntityNotFoundException se il cliente non ha transazioni.
     */

    public Transazione getUltimaTransazioneCliente(Long clienteId) {
        List<Transazione> transazioni = transazioneRepository.findTransazioniByClienteIdOrderByDataTransazioneDesc(clienteId);

        if (!transazioni.isEmpty()) {
            return transazioni.get(0); // Ritorna la prima transazione (l'ultima in base alla data) trovata
        } else {
            throw new EntityNotFoundException("Nessuna transazione trovata per il cliente con ID: " + clienteId);
        }
    }

    /**
     * Elimina tutte le transazioni nel repository delle transazioni.
     */

    public void eliminaTransazioni() {
        // Aggiungi il codice per eliminare tutte le transazioni dal tuo repository
        transazioneRepository.deleteAll(); // Supponendo che tu abbia un repository chiamato transazioneRepository
    }

}
