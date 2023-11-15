package mes.corporation.loyaltyplatform.fedelta;


import mes.corporation.loyaltyplatform.utenti.model.Cliente;

import java.math.BigDecimal;

public interface ProgrammaFedelta {
    void registraCliente(Cliente cliente);
    int calcolaPunti(BigDecimal importoSpeso);

}

