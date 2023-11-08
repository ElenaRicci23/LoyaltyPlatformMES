package mes.corporation.loyaltyplatform.utenti.service;

import mes.corporation.loyaltyplatform.utenti.model.Cliente;
import mes.corporation.loyaltyplatform.utenti.repo.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteService extends UtenteService<Cliente, ClienteRepository> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public boolean login(Cliente cliente) {
        Cliente existingCliente = clienteRepository.findByEmail(cliente.getEmail());
        if (existingCliente != null && existingCliente.getPassword().equals(cliente.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public void registrazione(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public boolean isEmailAlreadyRegistered(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        return cliente != null;
    }
}

