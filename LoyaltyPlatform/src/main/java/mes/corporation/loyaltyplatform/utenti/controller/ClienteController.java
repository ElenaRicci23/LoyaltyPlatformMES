package mes.corporation.loyaltyplatform.utenti.controller;

import mes.corporation.loyaltyplatform.utenti.DTO.ClienteDTO;
import mes.corporation.loyaltyplatform.utenti.model.Cliente;
import mes.corporation.loyaltyplatform.utenti.model.DatiPersonaliCliente;
import mes.corporation.loyaltyplatform.utenti.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

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


}

//{
//        "email": "mario@example.com",
//        "password": "password123",
//        "datiPersonali": {
//        "nome": "Mario",
//        "cognome": "Rossi",
//        "sesso": "M",
//        "codiceFiscale": "MRIRSS234OIUH08",
//        "dataNascita": "1996-02-02",
//        "residenza": "Torino",
//        "indirizzo": "Via Cavour,49",
//        "cellulare": "3345671235"
//        }
//        }
