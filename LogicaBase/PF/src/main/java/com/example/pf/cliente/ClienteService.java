package com.example.pf.cliente;


import com.example.pf.DTO.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.pf.DTO.ClienteDTO.convertClienteToEntity;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TesseraRepository tesseraRepository;
    @Autowired
    private TesseraService tesseraService;
    @Autowired
    public ClienteService(ClienteRepository clienteRepository, TesseraService tesseraService) {
        this.clienteRepository = clienteRepository;
        this.tesseraService = tesseraService;
    }

    public List<Cliente> getAllClienti() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(id).orElse(null);
        if (existingCliente != null) {
            existingCliente.setNome(cliente.getNome());
            existingCliente.setCognome(cliente.getCognome());
            // Aggiungi altri campi da aggiornare se necessario
            return clienteRepository.save(existingCliente);
        }
        return null;
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente createCliente(ClienteDTO clienteDTO) {
        // Converti il DTO in un'entità Cliente
        Cliente cliente = convertClienteToEntity(clienteDTO);
        // Salva il cliente nel database
        clienteRepository.save(cliente);

        // Ora che il cliente è stato salvato, puoi creare una tessera associata ad esso
        tesseraService.creaTessera(cliente);
        return cliente;
    }



}

