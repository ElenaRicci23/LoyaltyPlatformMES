package mes.corporation.loyaltyplatform.utenti.controller;

import mes.corporation.loyaltyplatform.fedelta.ProgrammaFedelta;
import mes.corporation.loyaltyplatform.fedelta.ProgrammaFedeltaFactory;
import mes.corporation.loyaltyplatform.fedelta.TipoProgrammaFedelta;
import mes.corporation.loyaltyplatform.utenti.DTO.ClienteDTO;
import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.model.Cliente;
import mes.corporation.loyaltyplatform.utenti.model.DatiPersonaliCliente;
import mes.corporation.loyaltyplatform.utenti.service.AziendaService;
import mes.corporation.loyaltyplatform.utenti.service.ClienteService;
import mes.corporation.loyaltyplatform.utenti.service.TesseraService;
import org.slf4j.Logger; // Importa il logger SLF4J
import org.slf4j.LoggerFactory; // Importa il LoggerFactory per creare il logger
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AziendaService aziendaService;

    @Autowired
    private ProgrammaFedeltaFactory programmaFedeltaFactory;

    @Autowired
    private TesseraService tesseraService;

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class); // Crea un logger

    @PostMapping("/registrazione")
    public ResponseEntity<String> registrazioneCliente(@RequestBody ClienteDTO clienteDTO) {
        // Validare i dati del DTO (ad esempio, assicurarsi che l'email sia unica)
        if (clienteService.isEmailAlreadyRegistered(clienteDTO.getEmail())) {
            return ResponseEntity.badRequest().body("L'email è già registrata.");
        }

        // Creare un oggetto DatiPersonaliCliente dal DatiPersonaliClienteDTO
        DatiPersonaliCliente datiPersonaliCliente = new DatiPersonaliCliente();
        datiPersonaliCliente.setNome(clienteDTO.getDatiPersonali().getNome());
        datiPersonaliCliente.setCognome(clienteDTO.getDatiPersonali().getCognome());
        datiPersonaliCliente.setSesso(clienteDTO.getDatiPersonali().getSesso());
        datiPersonaliCliente.setCodiceFiscale(clienteDTO.getDatiPersonali().getCodiceFiscale());
        datiPersonaliCliente.setDataNascita(clienteDTO.getDatiPersonali().getDataNascita());
        datiPersonaliCliente.setResidenza(clienteDTO.getDatiPersonali().getResidenza());
        datiPersonaliCliente.setIndirizzo(clienteDTO.getDatiPersonali().getIndirizzo());
        datiPersonaliCliente.setCellulare(clienteDTO.getDatiPersonali().getCellulare());

        // Creare un oggetto Cliente dal DTO
        Cliente cliente = new Cliente();
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setPassword(clienteDTO.getPassword());

        // Imposta l'oggetto datiPersonaliCliente nell'oggetto cliente
        cliente.setDatiPersonali(datiPersonaliCliente);

        // Registra il cliente nel database utilizzando il servizio
        clienteService.registrazione(cliente);

        return ResponseEntity.ok("Cliente registrato con successo.");
    }

    @PostMapping("/iscrizioneProgrammaFedelta/{clienteId}/{aziendaId}")
    public ResponseEntity<String> iscriviProgrammaFedelta(
            @PathVariable Long clienteId,
            @PathVariable Long aziendaId
    ) {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Azienda azienda = aziendaService.getAziendaById(aziendaId);

        if (cliente != null && azienda != null) {
            TipoProgrammaFedelta tipoProgramma = azienda.getTipoProgrammaFedelta();

            // Utilizza il tipo di programma per selezionare l'implementazione corretta
            ProgrammaFedelta programmaFedelta = programmaFedeltaFactory.creaProgrammaFedelta(tipoProgramma);

            // Iscrivi il cliente al programma fedeltà
            programmaFedelta.registraCliente(cliente);

            return ResponseEntity.ok("Cliente iscritto al programma fedeltà con successo.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
