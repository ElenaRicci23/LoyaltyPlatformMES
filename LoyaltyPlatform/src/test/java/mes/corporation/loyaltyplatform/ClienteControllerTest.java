package mes.corporation.loyaltyplatform;

import mes.corporation.loyaltyplatform.fedelta.service.TransazioneService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransazioneService transazioneService; // Sostituisci con il nome corretto del tuo servizio

    @Test
    public void testEffettuaTransazione() throws Exception {
        Long clienteId = 1L;
        Long aziendaId = 1L;
        BigDecimal importoSpeso = new BigDecimal("50.00");

        // Configura il comportamento atteso del servizio mock
        Mockito.doNothing().when(transazioneService).processaTransazione(importoSpeso, clienteId, aziendaId);

        // Esegui la richiesta
        ResultActions resultActions = mockMvc.perform(post("/api/clienti/effettuaTransazione/{clienteId}/{aziendaId}", clienteId, aziendaId)
                        .param("importoSpeso", importoSpeso.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Transazione completata con successo")));

        // Verifica che il servizio sia stato chiamato con i parametri corretti
        Mockito.verify(transazioneService).processaTransazione(importoSpeso, clienteId, aziendaId);
    }
}
