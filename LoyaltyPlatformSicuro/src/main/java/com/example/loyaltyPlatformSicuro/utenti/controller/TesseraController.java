package com.example.loyaltyPlatformSicuro.utenti.controller;

import com.example.loyaltyPlatformSicuro.programmaPunti.PuntiInsufficientiException;
import com.example.loyaltyPlatformSicuro.utenti.model.Azienda;
import com.example.loyaltyPlatformSicuro.utenti.model.Cliente;
import com.example.loyaltyPlatformSicuro.utenti.model.Tessera;
import com.example.loyaltyPlatformSicuro.utenti.repository.AziendaRepository;
import com.example.loyaltyPlatformSicuro.utenti.repository.ClienteRepository;
import com.example.loyaltyPlatformSicuro.utenti.service.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Questa classe è responsabile di gestire le richieste relative alle tessere dei clienti.
 */
@RestController
@RequestMapping("/tessera")
public class TesseraController {

    @Autowired
    private TesseraService tesseraService;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AziendaRepository aziendaRepository;

    /**
     * Gestisce una richiesta POST per creare una tessera per un cliente.
     *
     * @param clienteId L'ID del cliente per cui creare la tessera.
     * @return La tessera creata o null se il cliente non esiste.
     */
    @PostMapping("/crea")
    public Tessera creaTesseraPerCliente(@RequestBody Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        // Gestisci l'errore
        return cliente.map(value -> tesseraService.creaTessera(value)).orElse(null);
    }

    /**
     * Gestisce una richiesta GET per ottenere la tessera di un cliente.
     *
     * @param clienteId L'ID del cliente di cui ottenere la tessera.
     * @return La tessera del cliente o null se non esiste.
     */
    @GetMapping("/{clienteId}")
    public Tessera getTesseraDiCliente(@PathVariable Long clienteId) {
        return tesseraService.getTesseraByClienteId(clienteId);
    }

    /**
     * Gestisce una richiesta POST per aggiungere punti alla tessera di un cliente.
     *
     * @param clienteId L'ID del cliente a cui aggiungere i punti.
     * @param punti     Il numero di punti da aggiungere.
     * @param aziendaId L'ID dell'azienda associata all'aggiunta dei punti.
     * @throws Exception Eccezione generica in caso di errore.
     */
    @PostMapping("/{clienteId}/aggiungiPunti")
    public void aggiungiPunti(@PathVariable Long clienteId, @RequestParam int punti, @RequestParam Long aziendaId) throws Exception {
        Tessera tessera = tesseraService.getTesseraByClienteId(clienteId);

        if (tessera != null) {
            tesseraService.aggiungiPuntiATessera(clienteId, punti, aziendaId); // Passa l'ID dell'azienda come parametro
        }
    }

    /**
     * Gestisce una richiesta POST per riscattare punti dalla tessera di un cliente.
     *
     * @param clienteId L'ID del cliente da cui riscattare i punti.
     * @param punti     Il numero di punti da riscattare.
     * @param aziendaId L'ID dell'azienda associata al riscatto dei punti.
     * @throws PuntiInsufficientiException Eccezione specifica per punti insufficienti.
     */
    @PostMapping("/{clienteId}/riscattaPunti")
    public void riscattaPunti(@PathVariable Long clienteId, @RequestParam int punti, @RequestParam Long aziendaId) throws PuntiInsufficientiException {
        Tessera tessera = tesseraService.getTesseraByClienteId(clienteId);
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(aziendaId);

        if (tessera != null && aziendaOptional.isPresent()) {
            tesseraService.riscattaPuntiDaTessera(clienteId, punti, aziendaOptional.get());
        }
    }
}
