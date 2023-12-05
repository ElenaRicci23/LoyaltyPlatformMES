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

    public TransazioneService(SistemaPagamento sistemaPagamento, ClienteService clienteService, ClienteService clienteService1, TesseraRepository tesseraRepository, TransazioneRepository transazioneRepository) {
        this.sistemaPagamento = sistemaPagamento;
        this.clienteService = clienteService1;
        this.tesseraRepository = tesseraRepository;
        this.transazioneRepository = transazioneRepository;
    }

    public PagamentoSimulato effettuaAcquistoCliente(AcquistoDTO acquistoDTO) {
        // Ottieni il cliente dal servizio clienteService
        Long clienteId = acquistoDTO.getClienteId();
        Cliente cliente = clienteService.getClienteById(clienteId);

        if (cliente == null) {
            throw new RuntimeException("Cliente non trovato.");
        }

        // Chiamata al simulatore di pagamento per ottenere la simulazione di pagamento
        double importo = acquistoDTO.getImportoProdotto();
        String nomeAzienda = acquistoDTO.getNomeAziendaAcquisto();

        // Ottieni la simulazione del pagamento
        PagamentoSimulato pagamento = sistemaPagamento.effettuaPagamento(importo, nomeAzienda);

        // Crea un oggetto Transazione con i dettagli della simulazione
        Transazione transazione = new Transazione();
        transazione.setDataTransazione(LocalDate.parse(pagamento.getDataTransazione()));
        transazione.setImporto(pagamento.getImportoPagato());
        transazione.setNomeAzienda(pagamento.getNomeAzienda());
        transazione.setCliente(cliente); // dove 'cliente' è l'oggetto Cliente associato all'acquisto




        // Salva la transazione nella repository delle transazioni
        transazioneRepository.save(transazione);

        // Restituisci la simulazione di pagamento all'utente
        return pagamento;
    }


    public Transazione getUltimaTransazioneCliente(Long clienteId) {
        List<Transazione> transazioni = transazioneRepository.findTransazioniByClienteIdOrderByDataTransazioneDesc(clienteId);

        if (!transazioni.isEmpty()) {
            return transazioni.get(0); // Ritorna la prima transazione (l'ultima in base alla data) trovata
        } else {
            // Gestisci il caso in cui il cliente non abbia transazioni
            throw new EntityNotFoundException("Nessuna transazione trovata per il cliente con ID: " + clienteId);
        }
    }

}
