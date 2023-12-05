package com.example.loyaltyPlatformSicuro.utenti.cliente;

import com.example.loyaltyPlatformSicuro.DTO.ClienteDTO;
import com.example.loyaltyPlatformSicuro.DTO.DatiPersonaliClienteDTO;
import com.example.loyaltyPlatformSicuro.security.auth.RuoloRepository;
import com.example.loyaltyPlatformSicuro.utenti.UtenteService;
import com.example.loyaltyPlatformSicuro.utenti.cliente.tessera.Tessera;
import com.example.loyaltyPlatformSicuro.utenti.cliente.tessera.TesseraService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service

public class ClienteService extends UtenteService<Cliente, ClienteRepository> {

    private final ClienteRepository clienteRepository;

    private RuoloRepository ruoloRepository;
    private final TesseraService tesseraService; // Inietta il servizio della tessera


    public ClienteService(ClienteRepository utenteRepository, ClienteRepository clienteRepository, TesseraService tesseraService) {
        super(utenteRepository);
        this.clienteRepository = clienteRepository;
        this.tesseraService = tesseraService;
    }

    public boolean isEmailAlreadyRegistered(String email) {
        Optional<Cliente> azienda = clienteRepository.findByEmail(email);
        return azienda.isPresent();
    }

    public void saveCliente(Cliente cliente) {
       clienteRepository.save(cliente);
    }
    public void registrazioneConDTO(ClienteDTO clienteDTO) {

        DatiPersonaliCliente datiPersonali = new DatiPersonaliCliente();
        // Verifica se l'email è già registrata
        if (isEmailAlreadyRegistered(clienteDTO.getEmail())) {
            throw new RuntimeException("L'email è già registrata.");
        }
        DatiPersonaliClienteDTO datiPersonaliDTO = clienteDTO.getDatiPersonali();
        datiPersonali.setNome(datiPersonaliDTO.getNome());
        datiPersonali.setCognome(datiPersonaliDTO.getCognome());
        datiPersonali.setCodiceFiscale(datiPersonaliDTO.getCodiceFiscale());
        datiPersonali.setCellulare(datiPersonaliDTO.getCellulare());
        datiPersonali.setIndirizzo(datiPersonaliDTO.getIndirizzo());
        datiPersonali.setDataNascita(datiPersonaliDTO.getDataNascita());
        datiPersonali.setResidenza(datiPersonaliDTO.getResidenza());
        datiPersonali.setSesso(datiPersonaliDTO.getSesso());

        // Crea l'oggetto azienda e imposta i dati
        Cliente cliente = new Cliente();
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setPassword(clienteDTO.getPassword());
        cliente.setDatiPersonali(datiPersonali);
        cliente.setRuolo("CLIENTE");
//        cliente.setUtente(cliente);
        saveCliente(cliente);
        // Dopo aver creato l'oggetto cliente, crea una tessera per lui
        LocalDate dataEmissione = LocalDate.now(); // Imposta la data di emissione a oggi
        LocalDate dataScadenza = dataEmissione.plusYears(1); // Imposta la data di scadenza a 1 anno dalla data di emissione
        Tessera tessera = tesseraService.creaTessera(dataEmissione, dataScadenza, null,cliente.getId()); // Passa null per il codice a barre (lo genereremo nella tesseraService)

        // Imposta la tessera creata per il cliente
        cliente.setTessera(tessera);

        // Altri controlli e logica di registrazione, ad esempio, salvare l'azienda nel database

    }
 public Cliente getClienteById(Long clienteid){
        Optional<Cliente> clienteOptional=clienteRepository.findById(clienteid);
        return clienteOptional.orElse(null);
 }


    public Long getTesseraIdByClienteId(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente non trovato con ID: " + clienteId));

        Tessera tessera = cliente.getTessera();

        if (tessera != null) {
            return tessera.getId();
        }

        // Gestisci il caso in cui il cliente non ha una tessera restituendo un valore di default o generando un'eccezione, a seconda delle tue esigenze.
        throw new EntityNotFoundException("Tessera non trovata per il cliente con ID: " + clienteId);
    }

}
