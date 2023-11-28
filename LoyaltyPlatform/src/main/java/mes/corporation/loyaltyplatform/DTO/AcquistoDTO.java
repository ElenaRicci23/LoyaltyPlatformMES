package mes.corporation.loyaltyplatform.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AcquistoDTO {
    private Long tesseraId;
    private BigDecimal importo;
    private LocalDateTime dataAcquisto;
    private String nomeAzienda;

    public Long getTesseraId() {
        return tesseraId;
    }

    public void setTesseraId(Long tesseraId) {
        this.tesseraId = tesseraId;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public LocalDateTime getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(LocalDateTime dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public String getNomeAzienda() {
        return nomeAzienda;
    }

    public void setNomeAzienda(String nomeAzienda) {
        this.nomeAzienda = nomeAzienda;
    }
}

