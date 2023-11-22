package com.example.pf.cliente;

import com.example.pf.DTO.ClienteDTO;
import com.example.pf.model.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesseraService {

    @Autowired
    private TesseraRepository tesseraRepository;

    public Tessera creaTessera(Cliente cliente) {
        Tessera nuovaTessera = new Tessera(cliente);
        nuovaTessera.setCliente(cliente); // Imposta il cliente nella tessera
        return tesseraRepository.save(nuovaTessera);
    }




}