package com.example.pf.azienda;


import com.example.pf.DTO.ProgrammaFedeltaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aziende")
public class AziendaController {

    private final AziendaService aziendaService;

    @Autowired
    public AziendaController(AziendaService aziendaService) {
        this.aziendaService = aziendaService;
    }

    @PostMapping("/aggiungi")
    public ResponseEntity<Azienda> createAzienda(@RequestBody Azienda azienda) {
        Azienda azienda1=aziendaService.creaAzienda(azienda);
        Azienda savedAzienda = aziendaService.saveAzienda(azienda);
        return ResponseEntity.ok(savedAzienda);
    }

    @GetMapping("/")
    public ResponseEntity<List<Azienda>> getAllAziende() {
        return ResponseEntity.ok(aziendaService.findAllAziende());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Azienda> getAziendaById(@PathVariable Long id) {
        return aziendaService.findAziendaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{aziendaId}/programmi-fedelta")
    public ResponseEntity<Azienda> addProgrammaFedelta(@PathVariable Long aziendaId, @RequestBody ProgrammaFedeltaDTO programmaFedeltaDto) {
        Azienda updatedAzienda = aziendaService.addProgrammaFedelta(aziendaId, programmaFedeltaDto.getNome(), programmaFedeltaDto.getDescrizione());
        if (updatedAzienda != null) {
            return ResponseEntity.ok(updatedAzienda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
