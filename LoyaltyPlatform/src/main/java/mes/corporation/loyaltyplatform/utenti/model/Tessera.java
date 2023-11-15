package mes.corporation.loyaltyplatform.utenti.model;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.utenti.programmaPunti.PuntiInsufficientiException;
import mes.corporation.loyaltyplatform.utenti.programmaPunti.PuntiPerAzienda;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "cliente_id")

    @OneToOne(fetch = FetchType.LAZY)
    private Cliente proprietario;

    @OneToMany(mappedBy = "tessera", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PuntiPerAzienda> puntiPerAziende = new ArrayList<>();

    public Tessera(Cliente proprietario) {
        this.proprietario = proprietario;
    }

    public Tessera() {  }

    public void aggiungiPunti(Azienda azienda, int punti) {
        PuntiPerAzienda puntiAzienda = puntiPerAziende.stream()
                .filter(pa -> pa.getAzienda().equals(azienda))
                .findFirst()
                .orElse(new PuntiPerAzienda(this, azienda, 0));

        puntiAzienda.setPunti(puntiAzienda.getPunti() + punti);

        if (!puntiPerAziende.contains(puntiAzienda)) {
            puntiPerAziende.add(puntiAzienda);
        }
    }
    public void scalaPunti(Azienda azienda, int punti) throws PuntiInsufficientiException {
        PuntiPerAzienda puntiAzienda = puntiPerAziende.stream()
                .filter(pa -> pa.getAzienda().equals(azienda))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Punti non trovati per l'azienda"));

        int puntiAttuali = puntiAzienda.getPunti();
        if (puntiAttuali >= punti) {
            puntiAzienda.setPunti(puntiAttuali - punti);
        } else {
            throw new PuntiInsufficientiException("Punti insufficienti per ottenere il premio");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getProprietario() {
        return proprietario;
    }

    public void setProprietario(Cliente proprietario) {
        this.proprietario = proprietario;
    }

    public List<PuntiPerAzienda> getPuntiPerAziende() {
        return puntiPerAziende;
    }

    public void setPuntiPerAziende(List<PuntiPerAzienda> puntiPerAziende) {
        this.puntiPerAziende = puntiPerAziende;
    }


}
