package com.example.loyaltyPlatformSicuro.utenti.cliente;

import com.example.loyaltyPlatformSicuro.DTO.ClienteDTO;
import com.example.loyaltyPlatformSicuro.DTO.DatiPersonaliClienteDTO;
import com.example.loyaltyPlatformSicuro.security.auth.RuoloRepository;
import com.example.loyaltyPlatformSicuro.utenti.UtenteService;
import com.example.loyaltyPlatformSicuro.utenti.cliente.tessera.Tessera;
import com.example.loyaltyPlatformSicuro.utenti.cliente.tessera.TesseraService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ClienteService extends UtenteService<Cliente, ClienteRepository> {

    private final ClienteRepository clienteRepository;
    private RuoloRepository ruoloRepository;
    private final TesseraService tesseraService;
    private final ModelMapper modelMapper;

    @Autowired
    public ClienteService(
            ClienteRepository utenteRepository,
            ClienteRepository clienteRepository,
            TesseraService tesseraService,
            ModelMapper modelMapper
    ) {
        super(utenteRepository);
        this.clienteRepository = clienteRepository;
        this.tesseraService = tesseraService;
        this.modelMapper = modelMapper;
    }

    public boolean isEmailAlreadyRegistered(String email) {
        Optional<Cliente> azienda = clienteRepository.findByEmail(email);
        return azienda.isPresent();
    }

    public void saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }
    public void registrazioneConDTO(ClienteDTO clienteDTO) {
        // Usa ModelMapper per mappare ClienteDTO a Cliente
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);

        cliente.setRuolo("CLIENTE");

        // Mappa i dati personali da DatiPersonaliClienteDTO a DatiPersonaliCliente
        DatiPersonaliCliente datiPersonali = modelMapper.map(clienteDTO.getDatiPersonali(), DatiPersonaliCliente.class);

        // Imposta i dati personali nel cliente
        cliente.setDatiPersonali(datiPersonali);

        // Verifica se l'email è già registrata
        if (isEmailAlreadyRegistered(clienteDTO.getEmail())) {
            throw new RuntimeException("L'email è già registrata.");
        }

        // Salva il cliente nel database
        saveCliente(cliente);

        // Dopo aver creato l'oggetto cliente, crea una tessera per lui
        LocalDate dataEmissione = LocalDate.now();
        LocalDate dataScadenza = dataEmissione.plusYears(1);
        Tessera tessera = tesseraService.creaTessera(dataEmissione, dataScadenza, null, cliente.getId());
        cliente.setTessera(tessera);
    }

    public Cliente getClienteById(Long clienteId){
        Optional<Cliente> clienteOptional=clienteRepository.findById(clienteId);
        return clienteOptional.orElse(null);
    }

    public void eliminaClienti() {
        // Aggiungi il codice per eliminare tutti i clienti dal tuo repository
        clienteRepository.deleteAll(); // Supponendo che tu abbia un repository chiamato clienteRepository
    }

}
