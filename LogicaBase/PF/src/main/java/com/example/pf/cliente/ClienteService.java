package com.example.pf.cliente;


import com.example.pf.DTO.ClienteDTO;
import com.example.pf.DTO.ClienteTesseraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Cliente cliente = convertClienteToEntity(clienteDTO);
        clienteRepository.save(cliente);
        Tessera tessera = tesseraService.creaTessera(cliente);
        return cliente;
    }
//    public List<ClienteTesseraDTO> getClientiETessere() {
//        List<ClienteTesseraDTO> clientiETessere = new ArrayList<>();
//
//        List<Cliente> clienti = clienteRepository.findAll();
//
//        for (Cliente cliente : clienti) {
//            ClienteTesseraDTO dto = new ClienteTesseraDTO();
//            dto.setNomeCliente(cliente.getNome());
//            if (cliente.getTessera() != null) {
//                // Aggiungi i dettagli della tessera al DTO
//                dto.setDettagliTessera(cliente.getTessera());
//            }
//            clientiETessere.add(dto);
//        }
//
//        return clientiETessere;
//    }






}

