package mes.corporation.loyaltyplatform;

import mes.corporation.loyaltyplatform.utenti.DTO.AziendaDTO;
import mes.corporation.loyaltyplatform.utenti.DTO.DatiPersonaliAziendaDTO;
import mes.corporation.loyaltyplatform.utenti.controller.AziendaController;
import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.service.AziendaService;
import mes.corporation.loyaltyplatform.fedelta.model.TipoProgrammaFedelta;
import mes.corporation.loyaltyplatform.fedelta.repository.ProgrammaFedeltaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AziendaControllerTest {

    @Mock
    private AziendaService aziendaService;

    @Mock
    private ProgrammaFedeltaRepository programmaFedeltaRepository;

    @InjectMocks
    private AziendaController aziendaController;

    @Test
    void testRegistrazioneAzienda() {
        AziendaDTO aziendaDTO = new AziendaDTO();
        aziendaDTO.setEmail("Mes@example.com");
        aziendaDTO.setPassword("password456");

        DatiPersonaliAziendaDTO datiPersonaliDTO = new DatiPersonaliAziendaDTO();
        datiPersonaliDTO.setNome("Mes Corporation");
        datiPersonaliDTO.setPartitaIva("5412346745");
        datiPersonaliDTO.setCodiceUnivoco("codiceUnivoco123");
        datiPersonaliDTO.setRagioneSociale("MesCorporation");
        datiPersonaliDTO.setSettore("Informatico");
        datiPersonaliDTO.setIndirizzo("Via Roma,23");
        datiPersonaliDTO.setNumeroStabilimenti(4);
        aziendaDTO.setDatiPersonali(datiPersonaliDTO);

        when(aziendaService.isEmailAlreadyRegistered(any())).thenReturn(false);
        when(aziendaService.isPartitaIvaAlreadyRegistered(any())).thenReturn(false);

        ResponseEntity<String> response = aziendaController.registrazioneAzienda(aziendaDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Azienda registrata con successo.", response.getBody());

        verify(aziendaService, times(1)).registrazione(any());
    }

    @Test
    void testGetAziendaByPartitaIva_AziendaPresente() {
        String partitaIva = "5412346745";
        Azienda azienda = new Azienda();
        azienda.setPartitaIva(partitaIva);

        when(aziendaService.findAziendaByPartitaIva(partitaIva)).thenReturn(azienda);

        ResponseEntity<Azienda> response = aziendaController.getAziendaByPartitaIva(partitaIva);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(azienda, response.getBody());
    }

    @Test
    void testGetAziendaByPartitaIva_AziendaNonPresente() {
        String partitaIva = "5412346745";

        when(aziendaService.findAziendaByPartitaIva(partitaIva)).thenReturn(null);

        ResponseEntity<Azienda> response = aziendaController.getAziendaByPartitaIva(partitaIva);

        assertEquals(404, response.getStatusCodeValue());
    }
}

//    @Test
//    void testAggiungiProgrammaFedelta() {
//        Long aziendaId = 1L;
//        String nome = "Programma Fedeltà Gold";
//        String descrizione = "Descrizione del programma fedeltà Gold";
//        TipoProgrammaFedelta tipoProgrammaFedelta = TipoProgrammaFedelta.PUNTI;
//
//        Azienda azienda = new Azienda();
//        azienda.setId(aziendaId);
//
//        when(aziendaService.getAziendaById(aziendaId)).thenReturn(azienda);
//
//        ResponseEntity<String> response = aziendaController.aggiungiProgrammaFedelta(aziendaId, nome, descrizione, tipoProgrammaFedelta);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals("Programma fedeltà aggiunto con successo all'azienda.", response.getBody());
//
//        verify(aziendaService, times(1)).aggiungiProgrammaFedelta(nome, descrizione, tipoProgrammaFedelta, azienda);
//        verify(aziendaService, times(1)).saveAzienda(azienda);
//    }
//}
