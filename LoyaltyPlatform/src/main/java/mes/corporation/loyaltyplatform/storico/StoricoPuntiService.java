package mes.corporation.loyaltyplatform.storico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoricoPuntiService {

    @Autowired
    private StoricoPuntiRepository storicoPuntiRepository;

    public List<StoricoPunti> getStoricoPuntiByTesseraId(Long tesseraId) {
        return storicoPuntiRepository.findStoricoPuntiByTesseraId(tesseraId);
    }
}

