package com.example.loyaltyPlatformSicuro.utenti.cliente;

import com.example.loyaltyPlatformSicuro.DTO.ClienteDTO;
import com.example.loyaltyPlatformSicuro.security.auth.RuoloRepository;
import com.example.loyaltyPlatformSicuro.utenti.UtenteService;
import com.example.loyaltyPlatformSicuro.utenti.cliente.tessera.Tessera;
import com.example.loyaltyPlatformSicuro.utenti.cliente.tessera.TesseraService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
/**
 * Servizio per la gestione delle operazioni relative ai clienti.
 */
@Service
public class ClienteService extends UtenteService<Cliente, ClienteRepository> {

    private final ClienteRepository clienteRepository;
    private RuoloRepository ruoloRepository;
    private final TesseraService tesseraService;
    private  ModelMapper modelMapper;
    /**
     * Costruttore del servizio ClienteService.
     *
     * @param utenteRepository   Il repository generico degli utenti.
     * @param clienteRepository  Il repository specifico dei clienti.
     * @param tesseraService     Il servizio per la gestione delle tessere.
     */
    @Autowired
    public ClienteService(
            ClienteRepository utenteRepository,
            ClienteRepository clienteRepository,
            TesseraService tesseraService
    ) {
        super(utenteRepository);
        this.clienteRepository = clienteRepository;
        this.tesseraService = tesseraService;
    }
    /**
     * Verifica se un'email è già registrata per un cliente.
     *
     * @param email  L'email da verificare.
     * @return       True se l'email è già registrata, altrimenti false.
     */

    public boolean isEmailAlreadyRegistered(String email) {
        Optional<Cliente> azienda = clienteRepository.findByEmail(email);
        return azienda.isPresent();
    }

    /**
     * Salva un cliente nel repository.
     *
     * @param cliente  Il cliente da salvare.
     */


    public void saveCliente(Cliente cliente) {
       clienteRepository.save(cliente);
    }

    /**
     * Registra un nuovo cliente utilizzando un oggetto ClienteDTO.
     *
     * @param clienteDTO  Il DTO del cliente da registrare.
     * @throws RuntimeException se l'email è già registrata.
     */
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

    /**
     * Converte un oggetto ClienteDTO in un oggetto Cliente.
     *
     * @param clienteDTO  Il DTO del cliente da convertire.
     * @return           Il cliente convertito.
     */


    public Cliente convertiDTOInCliente(ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        return cliente;
    }

    /**
     * Ottiene un cliente per ID.
     *
     * @param clienteId  L'ID del cliente da recuperare.
     * @return           Il cliente corrispondente o null se non esiste.
     */


    public Cliente getClienteById(Long clienteId){
        Optional<Cliente> clienteOptional=clienteRepository.findById(clienteId);
        return clienteOptional.orElse(null);
    }
    /**
     * Elimina tutti i clienti dal repository.
     */

    public void eliminaClienti() {
        // Aggiungi il codice per eliminare tutti i clienti dal tuo repository
        clienteRepository.deleteAll(); // Supponendo che tu abbia un repository chiamato clienteRepository
    }

}
