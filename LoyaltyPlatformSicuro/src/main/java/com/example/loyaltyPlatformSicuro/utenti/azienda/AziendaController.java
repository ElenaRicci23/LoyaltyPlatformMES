package com.example.loyaltyPlatformSicuro.utenti.azienda;


import com.example.loyaltyPlatformSicuro.DTO.AziendaDTO;
import com.example.loyaltyPlatformSicuro.DTO.ProgrammaFedeltaDTO;
import com.example.loyaltyPlatformSicuro.DTO.ProgrammaPuntiDTO;
import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.programmiFedelta.factory.GestoreProgrammiFedelta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/azienda")
public class AziendaController {

    private final AziendaService aziendaService;

    private final GestoreProgrammiFedelta gestoreProgrammiFedelta;

    private final ModelMapper modelMapper;


    @Autowired
    public AziendaController(AziendaService aziendaService, GestoreProgrammiFedelta gestoreProgrammiFedelta, ModelMapper modelMapper) {
        this.aziendaService = aziendaService;
        this.gestoreProgrammiFedelta = gestoreProgrammiFedelta;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/registrazione", consumes = "application/json")
    public ResponseEntity<?> registrazioneAzienda(@RequestBody AziendaDTO aziendaDTO) {
        try {
            aziendaService.registrazioneConDTO(aziendaDTO);
            return ResponseEntity.ok(Map.of("message", "Registrazione completata con successo!", "redirect", "/benvenuto"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Errore nella registrazione: " + e.getMessage()));
        }
    }


    @GetMapping("/{aziendaId}/dashboardAzienda")
    public ModelAndView dashboardAzienda(@PathVariable Long aziendaId) {
        Azienda azienda = aziendaService.getAziendaById(aziendaId);

        // Verifica se l'azienda esiste e se l'utente ha i diritti per accedere a questa dashboard
        if (azienda != null) {
            ModelAndView modelAndView = new ModelAndView("azienda/dashboardAzienda");
            modelAndView.addObject("azienda", azienda);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("errore");
            modelAndView.addObject("messaggio", "Azienda non trovata.");
            return modelAndView;
        }
    }

    @PostMapping(path = "/{aziendaId}/aggiungi-programma-fedelta", consumes = "application/json")
    public ResponseEntity<?> aggiungiProgrammaFedelta(@PathVariable Long aziendaId, @RequestBody ProgrammaFedeltaDTO programmaFedeltaDTO) {
        aziendaService.aggiungiProgrammaFedeltaAdAzienda(aziendaId, programmaFedeltaDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Programma fedeltà aggiunto con successo.");
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/{aziendaId}/{programmaPuntiId}/configura", consumes = "application/json")
    public ResponseEntity<?> configuraProgrammaPunti(@PathVariable Long aziendaId,@PathVariable Long programmaPuntiId,@RequestBody ProgrammaPuntiDTO programmaPuntiDTO) {
        try {
            aziendaService.configuraProgrammaPunti(programmaPuntiDTO, aziendaId, programmaPuntiId);
            return ResponseEntity.ok(Map.of("message", "Programma punti configurato con successo."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Errore nella configurazione del programma punti: " + e.getMessage()));
        }
    }
    @GetMapping(path="/{aziendaId}/programmi-fedelta")
    public ResponseEntity<List<ProgrammaFedeltaDTO>> getProgrammiFedeltaByAzienda(@PathVariable Long aziendaId) {
        Azienda azienda = aziendaService.findById(aziendaId);
        if (azienda == null) {
            return ResponseEntity.notFound().build();
        }
        List<ProgrammaFedelta> programmiFedelta = gestoreProgrammiFedelta.getProgrammiFedeltaByAzienda(azienda);

        // Converti la lista di ProgrammaFedelta in una lista di ProgrammaFedeltaDTO
        List<ProgrammaFedeltaDTO> programmiFedeltaDTOList = gestoreProgrammiFedelta.convertToDTOList(programmiFedelta);

        return ResponseEntity.ok(programmiFedeltaDTOList);
    }
    @GetMapping("/{aziendaId}")
    public ResponseEntity<AziendaDTO> getAziendaById(@PathVariable Long aziendaId) {
        AziendaDTO aziendaDTO = aziendaService.getAziendaDTOById(aziendaId);
        if (aziendaDTO != null) {
            return new ResponseEntity<>(aziendaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/tutte")
    public ResponseEntity<List<AziendaDTO>> getAllAziende() {
        List<Azienda> aziende = aziendaService.getAllAziende();
        List<AziendaDTO> aziendeDTO = aziende.stream()
                .map(azienda -> modelMapper.map(azienda, AziendaDTO.class ))
                .collect(Collectors.toList());

        if (!aziendeDTO.isEmpty()) {
            return ResponseEntity.ok(aziendeDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }







}


