package mes.corporation.loyaltyplatform.fedelta.model;


import mes.corporation.loyaltyplatform.utenti.model.Cliente;

import java.math.BigDecimal;

/**
 * Questa interfaccia definisce il contratto per il programma fedeltà.
 * Implementare questa interfaccia per creare diverse strategie di programma fedeltà.
 */
public interface ProgrammaFedelta {
    /**
     * Registra un cliente nel programma fedeltà.
     *
     * @param cliente Il cliente da registrare.
     */
    void registraCliente(Cliente cliente);

    /**
     * Calcola i punti fedeltà basati sull'importo speso da un cliente.
     *
     * @param importoSpeso L'importo speso dal cliente.
     * @return Il numero di punti fedeltà calcolati.
     */
    int calcolaPunti(BigDecimal importoSpeso);
}

