package com.example.loyaltyPlatformSicuro.integrazioni;


import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Questa classe rappresenta un servizio per effettuare simulazioni di pagamento.
 * È utilizzata per simulare chiamate all'API di pagamento di terze parti e generare simulazioni di pagamento.
 */
@Service
public class SistemaPagamento {
    /**
     * Effettua una simulazione di pagamento con l'importo specificato e il nome dell'azienda.
     *
     * @param importo      L'importo del pagamento da simulare.
     * @param nomeAzienda  Il nome dell'azienda coinvolta nella simulazione di pagamento.
     * @return Un oggetto PagamentoSimulato che rappresenta la simulazione di pagamento.
     */
    public PagamentoSimulato effettuaPagamento(double importo, String nomeAzienda) {
        // Simulare una chiamata all'API di pagamento di terze parti
        // Verifica i dettagli del pagamento e genera una simulazione di pagamento

        // In questo esempio, generiamo una simulazione di pagamento
        PagamentoSimulato pagamento = new PagamentoSimulato();
        pagamento.setConferma(true);
        pagamento.setMessaggio("Pagamento effettuato con successo");
        pagamento.setDataTransazione(String.valueOf(LocalDate.now()));
        pagamento.setImportoPagato(importo);
        pagamento.setNomeAzienda(nomeAzienda);

        return pagamento;
    }
}


