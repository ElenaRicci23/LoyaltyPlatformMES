package com.example.loyaltyPlatformSicuro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }


    @GetMapping("/loginAzienda")
    public String showLoginAziendaForm() {
        return "azienda/loginAzienda"; // Aggiornato per riflettere il nuovo percorso del template
    }


    @GetMapping("/loginCliente")
    public String showLoginClienteForm() {
        return "cliente/loginCliente"; // Assicurati di avere un template loginCliente.html nel tuo progetto
    }


    @GetMapping("/registrazione-azienda")
    public String registrazioneAzienda() {
        return "azienda/registrazioneAzienda";
    }
    @GetMapping("/benvenuto")
    public String benvenuto() {
        return "benvenuto";
    }


    @GetMapping("/registrazione-cliente")
    public String registrazioneCliente() {
        return "cliente/registrazioneCliente";
    }

    @GetMapping("{aziendaId}/configuraProgrammaFedelta")
    public String configuraProgrammaFedelta(@PathVariable Long aziendaId) {
        return "azienda/configurazioneProgramma";
    }





}

