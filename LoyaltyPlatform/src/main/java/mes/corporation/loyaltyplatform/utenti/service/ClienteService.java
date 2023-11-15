package mes.corporation.loyaltyplatform.utenti.service;

import mes.corporation.loyaltyplatform.utenti.model.Cliente;
import mes.corporation.loyaltyplatform.utenti.repo.ClienteRepository;
import mes.corporation.loyaltyplatform.utenti.repo.TesseraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClienteService extends UtenteService<Cliente, ClienteRepository> {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TesseraRepository tesseraRepository;
    @Autowired
    private TesseraService tesseraService;
    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);


    public void registrazione(Cliente cliente) {
        clienteRepository.save(cliente);
        tesseraService.creaTessera(cliente);
    }


    public boolean isEmailAlreadyRegistered(String email) {
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        return cliente.isPresent();
    }

    public Cliente getClienteById(Long clienteId) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);
        return clienteOptional.orElse(null); // Restituisce il cliente se presente, altrimenti null
    }

}

