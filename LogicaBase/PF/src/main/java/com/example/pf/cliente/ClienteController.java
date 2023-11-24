package com.example.pf.cliente;


import com.example.pf.DTO.AcquistoDTO;
import com.example.pf.DTO.ClienteDTO;
import com.example.pf.DTO.ProgrammaFedeltaDTO;
import com.example.pf.model.GestoreProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
    private final ClienteService clienteService;
    private final GestoreProgrammaFedelta gestoreProgrammaFedelta;

    private final ClienteRepository clienteRepository;


    @Autowired
    public ClienteController(ClienteService clienteService, TesseraService tesseraService, GestoreProgrammaFedelta gestoreProgrammaFedelta, ClienteRepository clienteRepository) {
        this.clienteService = clienteService;
        this.gestoreProgrammaFedelta = gestoreProgrammaFedelta;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> getAllClienti() {
        List<Cliente> clienti = clienteService.getAllClienti();
        return new ResponseEntity<>(clienti, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/registra")
    public ResponseEntity<Cliente> createCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            // Chiamare il servizio per creare il cliente
            Cliente nuovoCliente = clienteService.createCliente(clienteDTO);

            if (nuovoCliente != null) {
                // Il cliente è stato creato con successo, restituisci una risposta di successo
                return new ResponseEntity<>(nuovoCliente, HttpStatus.CREATED);
            } else {
                // Gestisci il caso in cui la creazione del cliente non sia riuscita
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            // Gestisci eventuali eccezioni qui e restituisci una risposta di errore appropriata
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/{clienteId}/iscrivi-programma-fedelta")
    public ResponseEntity<?> iscriviClienteAProgrammaFedelta(
            @PathVariable Long clienteId,
            @RequestBody ProgrammaFedeltaDTO programmaFedeltaDTO) {

        // Trova il cliente
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        // Chiamare il servizio di GestoreProgrammaFedelta per iscrivere il cliente al programma fedeltà
        boolean iscrizioneRiuscita = gestoreProgrammaFedelta.iscriviClienteAProgrammaFedelta(cliente, programmaFedeltaDTO);

        if (iscrizioneRiuscita) {
            return ResponseEntity.ok("Iscrizione avvenuta con successo");
        } else {
            return ResponseEntity.badRequest().body("Impossibile iscriversi a questo programma fedeltà");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente updatedCliente = clienteService.updateCliente(id, cliente);
        if (updatedCliente != null) {
            return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

