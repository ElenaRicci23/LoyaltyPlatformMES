package mes.corporation.loyaltyplatform.fedelta;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.model.Cliente;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "azienda_id")
    private Azienda azienda;

    @ManyToOne
    @JoinColumn(name = "programma_fedelta_id")
    private ProgrammaFedeltaPunti programmaFedeltaPunti;

    private BigDecimal importoSpeso;
    private LocalDateTime dataTransazione;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public ProgrammaFedeltaPunti getProgrammaFedeltaPunti() {
        return programmaFedeltaPunti;
    }

    public void setProgrammaFedeltaPunti(ProgrammaFedeltaPunti programmaFedeltaPunti) {
        this.programmaFedeltaPunti = programmaFedeltaPunti;
    }

    public BigDecimal getImportoSpeso() {
        return importoSpeso;
    }

    public void setImportoSpeso(BigDecimal importoSpeso) {
        this.importoSpeso = importoSpeso;
    }


    public LocalDateTime getDataTransazione() {
        return dataTransazione;
    }

    public void setDataTransazione(LocalDateTime dataTransazione) {
        this.dataTransazione = dataTransazione;
    }

    // Altri campi e metodi necessari
}
