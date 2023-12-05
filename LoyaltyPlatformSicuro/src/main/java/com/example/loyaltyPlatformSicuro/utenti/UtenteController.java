package com.example.loyaltyPlatformSicuro.utenti;

import com.example.loyaltyPlatformSicuro.DTO.AuthenticationDTO;
import com.example.loyaltyPlatformSicuro.utenti.azienda.AziendaService;
import com.example.loyaltyPlatformSicuro.utenti.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtenteController {

    private final AziendaService aziendaService;
    private final ClienteService clienteService;

    @Autowired
    public UtenteController(AziendaService aziendaService, ClienteService clienteService) {
        this.aziendaService = aziendaService;
        this.clienteService = clienteService;
    }

    @PostMapping(path = "/loginAzienda", consumes = "application/json")
    public ResponseEntity<String> loginAzienda(@RequestBody AuthenticationDTO authenticationDTO) {
        try {
            String redirectUrl = aziendaService.login(authenticationDTO);
            return ResponseEntity.ok("Redirect:" + redirectUrl);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Errore: " + e.getMessage());
        }
    }

    @PostMapping(path = "/loginCliente", consumes = "application/json")
    public ResponseEntity<String> loginCliente(@RequestBody AuthenticationDTO authenticationDTO) {
        try {
            String redirectUrl = clienteService.login(authenticationDTO);
            return ResponseEntity.ok("Redirect:" + redirectUrl);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Errore: " + e.getMessage());
        }
    }

}
