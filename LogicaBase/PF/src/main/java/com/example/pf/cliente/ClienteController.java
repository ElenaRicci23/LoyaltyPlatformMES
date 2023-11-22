package com.example.pf.cliente;


import com.example.pf.DTO.ClienteDTO;
import com.example.pf.DTO.ClienteTesseraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.pf.DTO.ClienteDTO.convertClienteToDTO;
import static com.example.pf.DTO.ClienteDTO.convertClienteToEntity;


@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
    private final ClienteService clienteService;
    private final TesseraService tesseraService;

    @Autowired
    public ClienteController(ClienteService clienteService, TesseraService tesseraService) {
        this.clienteService = clienteService;
        this.tesseraService = tesseraService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> getAllClienti() {
        List<Cliente> clienti = clienteService.getAllClienti();
        return new ResponseEntity<>(clienti, HttpStatus.OK);
    }
//    @GetMapping("/clienti-tessere")
//    public ResponseEntity<List<ClienteTesseraDTO>> getClientiETessere() {
//        List<ClienteTesseraDTO> clientiETessere = clienteService.getClientiETessere();
//        return new ResponseEntity<>(clientiETessere, HttpStatus.OK);
//    }



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

