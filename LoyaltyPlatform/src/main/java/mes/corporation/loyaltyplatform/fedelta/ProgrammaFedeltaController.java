package mes.corporation.loyaltyplatform.fedelta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/programma-fedelta")
public class ProgrammaFedeltaController {
    @Autowired
    private ProgrammaFedeltaService programmaFedeltaService;

    // Aggiungi qui i metodi del controller per gestire le richieste relative al programma fedeltà
}

