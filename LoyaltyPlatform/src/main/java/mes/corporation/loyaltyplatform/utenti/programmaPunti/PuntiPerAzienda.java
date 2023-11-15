package mes.corporation.loyaltyplatform.utenti.programmaPunti;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import mes.corporation.loyaltyplatform.utenti.model.Tessera;

@Entity
public class PuntiPerAzienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Tessera tessera;
    @ManyToOne
    private Azienda azienda;
    private int punti;

    public PuntiPerAzienda(Tessera tessera, Azienda azienda, int punti) {
        this.tessera = tessera;
        this.azienda = azienda;
        this.punti = punti;
    }

    public PuntiPerAzienda() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    // Getter e Setter...
}
