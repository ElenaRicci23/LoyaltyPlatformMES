package com.example.loyaltyPlatformSicuro.utenti.controller;

import com.example.loyaltyPlatformSicuro.fedelta.service.TransazioneService;
import com.example.loyaltyPlatformSicuro.utenti.DTO.ClienteDTO;
import com.example.loyaltyPlatformSicuro.utenti.DTO.DatiPersonaliClienteDTO;
import com.example.loyaltyPlatformSicuro.utenti.model.Cliente;
import com.example.loyaltyPlatformSicuro.utenti.model.DatiPersonaliCliente;
import com.example.loyaltyPlatformSicuro.utenti.service.AziendaService;
import com.example.loyaltyPlatformSicuro.utenti.service.ClienteService;
import com.example.loyaltyPlatformSicuro.utenti.service.TesseraService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger; // Importa il logger SLF4J
import org.slf4j.LoggerFactory; // Importa il LoggerFactory per creare il logger
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


/**
 * Questa classe è responsabile di gestire le richieste relative ai clienti utenti.
 */
@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AziendaService aziendaService;

    @Autowired
    private TesseraService tesseraService;

    @Autowired
    private TransazioneService transazioneService;

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class); // Crea un logger

    /**
     * Gestisce una richiesta POST per la registrazione di un cliente.
     *
     * @param clienteDTO I dati del cliente da registrare.
     * @return Una ResponseEntity con un messaggio di successo o di errore.
     */
    @PostMapping("/registrazione")
    public ResponseEntity<String> registrazioneCliente(@RequestBody ClienteDTO clienteDTO) {
        // Validare i dati del DTO (ad esempio, assicurarsi che l'email sia unica)
        if (clienteService.isEmailAlreadyRegistered(clienteDTO.getEmail())) {
            return ResponseEntity.badRequest().body("L'email è già registrata.");
        }

        // Creare un oggetto DatiPersonaliCliente dal DatiPersonaliClienteDTO
        DatiPersonaliCliente datiPersonaliCliente = new DatiPersonaliCliente();
        DatiPersonaliClienteDTO datiPersonaliDTO = clienteDTO.getDatiPersonali();
        datiPersonaliCliente.setNome(datiPersonaliDTO.getNome());
        datiPersonaliCliente.setCognome(datiPersonaliDTO.getCognome());
        datiPersonaliCliente.setSesso(datiPersonaliDTO.getSesso());
        datiPersonaliCliente.setCodiceFiscale(datiPersonaliDTO.getCodiceFiscale());
        datiPersonaliCliente.setDataNascita(datiPersonaliDTO.getDataNascita());
        datiPersonaliCliente.setResidenza(datiPersonaliDTO.getResidenza());
        datiPersonaliCliente.setIndirizzo(datiPersonaliDTO.getIndirizzo());
        datiPersonaliCliente.setCellulare(datiPersonaliDTO.getCellulare());

        // Creare un oggetto Cliente dal DTO
        Cliente cliente = new Cliente();
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setPassword(clienteDTO.getPassword());

        // Imposta l'oggetto datiPersonaliCliente nell'oggetto cliente
        cliente.setDatiPersonali(datiPersonaliCliente);

        // Registra il cliente nel database utilizzando il servizio
        clienteService.registrazione(cliente);

        return ResponseEntity.ok("Cliente registrato con successo.");
    }

    /**
     * Gestisce una richiesta POST per effettuare una transazione e accumulare punti Fedelta.
     *
     * @param clienteId     L'ID del cliente che effettua la transazione.
     * @param aziendaId     L'ID dell'azienda presso cui viene effettuata la transazione.
     * @param importoSpeso  L'importo speso nella transazione.
     * @return Una ResponseEntity con un messaggio di successo o di errore.
     */
    @PostMapping("/effettuaTransazione/{clienteId}/{aziendaId}")
    @Transactional // Indica che il metodo è gestito come una transazione
    public ResponseEntity<String> effettuaTransazione(
            @PathVariable Long clienteId,
            @PathVariable Long aziendaId,
            @RequestParam BigDecimal importoSpeso
    ) {
        try {
            // Esegui la transazione utilizzando il servizio TransazioneService
            transazioneService.processaTransazione(importoSpeso, clienteId, aziendaId);

            return ResponseEntity.ok("Transazione completata con successo. Punti Fedelta accumulati.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Errore durante l'elaborazione della transazione: " + e.getMessage());
        }
    }

/* POST /api/clienti/effettuaTransazione/123/456?importoSpeso=50.0
 richiesta di transazione per il cliente con ID 123 presso l'azienda con ID 456, con un importo speso di 50.0.
 */
}
