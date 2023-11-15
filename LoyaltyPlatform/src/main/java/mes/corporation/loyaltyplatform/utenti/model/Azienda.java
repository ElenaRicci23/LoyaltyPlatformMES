package mes.corporation.loyaltyplatform.utenti.model;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.fedelta.ProgrammaFedeltaPunti;
import mes.corporation.loyaltyplatform.fedelta.TipoProgrammaFedelta;

@Entity
public class Azienda extends Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "azienda_id")
    private DatiPersonaliAzienda datiPersonali;

    @Enumerated(EnumType.STRING)
    private TipoProgrammaFedelta tipoProgrammaFedelta;

    @OneToOne(mappedBy = "azienda")
    private ProgrammaFedeltaPunti programmaFedeltaPunti;
    public Azienda() {
    }

    public Azienda(String nome, String cognome, String email, String password, DatiPersonaliAzienda datiPersonali) {
        super( email, password);
        this.datiPersonali = datiPersonali;
        this.datiPersonali.setAzienda(this); // Imposta il collegamento bidirezionale
    }

    public DatiPersonaliAzienda getDatiPersonali() {
        return datiPersonali;
    }

    public void setDatiPersonali(DatiPersonaliAzienda datiPersonali) {
        this.datiPersonali = datiPersonali;
        this.datiPersonali.setAzienda(this); // Imposta il collegamento bidirezionale
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPartitaIva(String partitaIva) {
        this.datiPersonali.setPartitaIva(partitaIva);
    }


    public TipoProgrammaFedelta getTipoProgrammaFedelta() {
        return tipoProgrammaFedelta;
    }

    public void setTipoProgrammaFedelta(TipoProgrammaFedelta tipoProgrammaFedelta) {
        this.tipoProgrammaFedelta = tipoProgrammaFedelta;
    }

}

