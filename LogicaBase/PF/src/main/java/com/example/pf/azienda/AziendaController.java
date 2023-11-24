package com.example.pf.azienda;


import com.example.pf.DTO.AziendaDTO;
import com.example.pf.DTO.ProgrammaFedeltaConfigurazioneDTO;
import com.example.pf.DTO.ProgrammaFedeltaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.pf.DTO.AziendaDTO.convertToDTO;
import static com.example.pf.DTO.AziendaDTO.convertToEntity;

@RestController
@RequestMapping("/api/aziende")
public class AziendaController {

    private final AziendaService aziendaService;

    private final AziendaRepository aziendaRepository;


    @Autowired
    public AziendaController(AziendaService aziendaService, AziendaRepository aziendaRepository) {
        this.aziendaService = aziendaService;
        this.aziendaRepository = aziendaRepository;
    }

    @PostMapping("/aggiungi")
    public ResponseEntity<AziendaDTO> createAzienda(@RequestBody AziendaDTO aziendaDTO) {
        Azienda azienda = convertToEntity(aziendaDTO);
        Azienda savedAzienda = aziendaService.saveAzienda(azienda);
        AziendaDTO savedAziendaDTO = convertToDTO(savedAzienda);
        return new ResponseEntity<>(savedAziendaDTO, HttpStatus.CREATED);
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

    @PostMapping("/{aziendaId}/programmi_fedelta")
    public ResponseEntity<ProgrammaFedeltaDTO> addProgrammaFedelta(@PathVariable Long aziendaId, @RequestBody ProgrammaFedeltaDTO programmaFedeltaDto) {
        Azienda updatedAzienda = aziendaService.addProgrammaFedelta(aziendaId, programmaFedeltaDto.getNome(), programmaFedeltaDto.getTipoProgrammaFedelta(), programmaFedeltaDto.getDescrizione());
        if (updatedAzienda != null) {
            return ResponseEntity.ok(programmaFedeltaDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

