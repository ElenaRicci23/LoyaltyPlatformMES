package mes.corporation.loyaltyplatform.utenti.model;

import jakarta.persistence.*;

@Entity
public class Cliente extends Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id")
    private DatiPersonaliCliente datiPersonali;

    public Cliente() {
    }

    public Cliente(String nome, String cognome, String email, String password, DatiPersonaliCliente datiPersonali) {
        super(email, password);
        this.datiPersonali = datiPersonali;
        this.datiPersonali.setCliente(this); // Imposta il collegamento bidirezionale
    }

    public DatiPersonaliCliente getDatiPersonali() {
        return datiPersonali;
    }

    public void setDatiPersonali(DatiPersonaliCliente datiPersonali) {
        this.datiPersonali = datiPersonali;
        this.datiPersonali.setCliente(this); // Imposta il collegamento bidirezionale
    }
}
