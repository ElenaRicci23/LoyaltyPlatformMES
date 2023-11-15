package mes.corporation.loyaltyplatform.utenti.controller;

import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.model.Cliente;
import mes.corporation.loyaltyplatform.utenti.model.Tessera;
import mes.corporation.loyaltyplatform.utenti.programmaPunti.PuntiInsufficientiException;
import mes.corporation.loyaltyplatform.utenti.repo.AziendaRepository;
import mes.corporation.loyaltyplatform.utenti.repo.ClienteRepository;
import mes.corporation.loyaltyplatform.utenti.service.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tessera")
public class TesseraController {

    @Autowired
    private TesseraService tesseraService;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AziendaRepository aziendaRepository;

    @PostMapping("/crea")
    public Tessera creaTesseraPerCliente(@RequestBody Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        // Gestisci l'errore
        return cliente.map(value -> tesseraService.creaTessera(value)).orElse(null);
    }

    @GetMapping("/{clienteId}")
    public Tessera getTesseraDiCliente(@PathVariable Long clienteId) {
        return tesseraService.getTesseraByClienteId(clienteId);
    }

    @PostMapping("/{clienteId}/aggiungiPunti")
    public void aggiungiPunti(@PathVariable Long clienteId, @RequestParam int punti, @RequestParam Long aziendaId) throws Exception {
        Tessera tessera = tesseraService.getTesseraByClienteId(clienteId);

        if (tessera != null) {
            tesseraService.aggiungiPuntiATessera(clienteId, punti, aziendaId); // Passa l'ID dell'azienda come parametro
        }
    }




    @PostMapping("/{clienteId}/riscattaPunti")
    public void riscattaPunti(@PathVariable Long clienteId, @RequestParam int punti, @RequestParam Long aziendaId) throws PuntiInsufficientiException {
        Tessera tessera = tesseraService.getTesseraByClienteId(clienteId);
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(aziendaId);

        if (tessera != null && aziendaOptional.isPresent()) {
            tesseraService.riscattaPuntiDaTessera(clienteId, punti, aziendaOptional.get());
        }
    }



}
