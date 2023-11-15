package mes.corporation.loyaltyplatform.fedelta;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.model.Cliente;

import java.math.BigDecimal;


@Entity
public class ProgrammaFedeltaPunti implements ProgrammaFedelta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;
    @OneToOne
    @JoinColumn(name = "azienda_id")
    private Azienda azienda;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    @Override
    public void registraCliente(Cliente cliente) {
        // Implementa la registrazione del cliente per il programma fedeltà basato su punti
    }

    @Override
    public int calcolaPunti(BigDecimal importoSpeso) {
        int punti = importoSpeso.divide(BigDecimal.valueOf(10)).intValue();

        return punti;
    }


}
