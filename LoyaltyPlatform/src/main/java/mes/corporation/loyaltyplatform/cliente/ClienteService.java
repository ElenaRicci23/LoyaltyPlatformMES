package mes.corporation.loyaltyplatform.cliente;

import mes.corporation.loyaltyplatform.DTO.ClienteDTO;
import mes.corporation.loyaltyplatform.tessera.Tessera;
import mes.corporation.loyaltyplatform.tessera.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static mes.corporation.loyaltyplatform.DTO.ClienteDTO.convertClienteToEntity;

/**
 * Questo servizio gestisce le operazioni relative ai clienti.
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TesseraService tesseraService;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, TesseraService tesseraService) {
        this.clienteRepository = clienteRepository;
        this.tesseraService = tesseraService;
    }

    /**
     * Restituisce una lista di tutti i clienti presenti nel sistema.
     *
     * @return Una lista di clienti.
     */
    public List<Cliente> getAllClienti() {
        return clienteRepository.findAll();
    }

    /**
     * Restituisce un cliente in base al suo ID.
     *
     * @param id L'ID del cliente da cercare.
     * @return Il cliente trovato o null se non esiste.
     */
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    /**
     * Aggiorna le informazioni di un cliente esistente.
     *
     * @param id      L'ID del cliente da aggiornare.
     * @param cliente I nuovi dati del cliente.
     * @return Il cliente aggiornato o null se il cliente non esiste.
     */
    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(id).orElse(null);
        if (existingCliente != null) {
            existingCliente.setNome(cliente.getNome());
            existingCliente.setCognome(cliente.getCognome());
            // Aggiungi altri campi da aggiornare se necessario
            return clienteRepository.save(existingCliente);
        }
        return null;
    }

    /**
     * Cancella un cliente dal sistema.
     *
     * @param id L'ID del cliente da cancellare.
     */
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    /**
     * Crea un nuovo cliente nel sistema.
     *
     * @param clienteDTO I dati del cliente da creare.
     * @return Il cliente creato.
     */
    public Cliente createCliente(ClienteDTO clienteDTO) {
        Cliente cliente = convertClienteToEntity(clienteDTO);
        clienteRepository.save(cliente);
        Tessera tessera = tesseraService.creaTessera(cliente);
        return cliente;
    }
}
