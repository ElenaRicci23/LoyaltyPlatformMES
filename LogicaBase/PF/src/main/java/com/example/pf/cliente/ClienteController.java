package com.example.pf.cliente;


import com.example.pf.DTO.ClienteDTO;
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
        // Chiamare il servizio per creare il cliente utilizzando il DTO
        Cliente cliente = clienteService.createCliente(clienteDTO);

        // Restituisci una risposta di successo con il cliente appena creato
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
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

