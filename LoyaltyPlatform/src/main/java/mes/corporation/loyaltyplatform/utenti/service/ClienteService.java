package mes.corporation.loyaltyplatform.utenti.service;

import mes.corporation.loyaltyplatform.utenti.model.Cliente;
import mes.corporation.loyaltyplatform.utenti.repo.ClienteRepository;
import mes.corporation.loyaltyplatform.utenti.repo.TesseraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;



/**
 * Servizio per la gestione delle operazioni relative ai clienti.
 */
@Service
public class ClienteService extends UtenteService<Cliente, ClienteRepository> {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TesseraRepository tesseraRepository;
    @Autowired
    private TesseraService tesseraService;
    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    /**
     * Registra un nuovo cliente e crea una tessera associata.
     *
     * @param cliente Il cliente da registrare.
     */
    public void registrazione(Cliente cliente) {
        clienteRepository.save(cliente);
        tesseraService.creaTessera(cliente);
    }

    /**
     * Verifica se un'email è già registrata nel sistema.
     *
     * @param email L'email da verificare.
     * @return True se l'email è già registrata, altrimenti False.
     */
    public boolean isEmailAlreadyRegistered(String email) {
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        return cliente.isPresent();
    }

    /**
     * Ottiene un cliente dato il suo ID.
     *
     * @param clienteId L'ID del cliente da ottenere.
     * @return Un'istanza di Cliente se trovata, altrimenti null.
     */
    public Cliente getClienteById(Long clienteId) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);
        return clienteOptional.orElse(null); // Restituisce il cliente se presente, altrimenti null
    }
}


