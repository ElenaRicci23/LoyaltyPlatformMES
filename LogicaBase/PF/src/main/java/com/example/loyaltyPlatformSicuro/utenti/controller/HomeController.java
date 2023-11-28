package com.example.loyaltyPlatformSicuro.utenti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("/")
    public String index() {
        return "Ciao a tutti ";
    }

    @GetMapping("/api")
    public String indexApi(){
        return "Area Api";
    }
    @GetMapping("/api/clienti")
    public String indexClienti(){
        return "Area Clienti";
    }
}
