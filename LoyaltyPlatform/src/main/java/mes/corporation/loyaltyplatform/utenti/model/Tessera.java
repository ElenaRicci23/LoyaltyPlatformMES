package mes.corporation.loyaltyplatform.utenti.model;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.programmaPunti.PuntiInsufficientiException;
import mes.corporation.loyaltyplatform.programmaPunti.PuntiPerAzienda;

import java.util.ArrayList;
import java.util.List;


/**
 * Rappresenta una tessera di un cliente e tiene traccia dei punti accumulati con le aziende.
 */
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

    /**
     * Costruttore per creare una tessera associata a un cliente.
     *
     * @param proprietario Il cliente proprietario della tessera.
     */
    public Tessera(Cliente proprietario) {
        this.proprietario = proprietario;
    }

    /**
     * Costruttore vuoto per l'entità Tessera.
     */
    public Tessera() {  }

    /**
     * Aggiunge punti alla tessera associati a un'azienda specifica.
     *
     * @param azienda L'azienda a cui aggiungere i punti.
     * @param punti   Il numero di punti da aggiungere.
     */
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

    /**
     * Scala punti dalla tessera associati a un'azienda specifica.
     *
     * @param azienda L'azienda da cui scalare i punti.
     * @param punti   Il numero di punti da scalare.
     * @throws PuntiInsufficientiException Se i punti sono insufficienti per ottenere il premio.
     */
    public void scalaPunti(Azienda azienda, int punti) throws PuntiInsufficientiException {
        PuntiPerAzienda puntiAzienda = puntiPerAziende.stream()
                .filter(pa -> pa.getAzienda().equals(azienda))
                .findFirst()
                .orElseThrow(() -> new PuntiInsufficientiException("Punti non trovati per l'azienda"));

        int puntiAttuali = puntiAzienda.getPunti();
        if (puntiAttuali >= punti) {
            puntiAzienda.setPunti(puntiAttuali - punti);
        } else {
            throw new PuntiInsufficientiException("Punti insufficienti per ottenere il premio");
        }
    }

    /**
     * Restituisce l'ID della tessera.
     *
     * @return L'ID della tessera.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID della tessera.
     *
     * @param id L'ID della tessera.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il cliente proprietario della tessera.
     *
     * @return Il cliente proprietario della tessera.
     */
    public Cliente getProprietario() {
        return proprietario;
    }

    /**
     * Imposta il cliente proprietario della tessera.
     *
     * @param proprietario Il cliente proprietario della tessera.
     */
    public void setProprietario(Cliente proprietario) {
        this.proprietario = proprietario;
    }

    /**
     * Restituisce la lista dei punti per le aziende associati a questa tessera.
     *
     * @return La lista dei punti per le aziende.
     */
    public List<PuntiPerAzienda> getPuntiPerAziende() {
        return puntiPerAziende;
    }

    /**
     * Imposta la lista dei punti per le aziende associati a questa tessera.
     *
     * @param puntiPerAziende La lista dei punti per le aziende.
     */
    public void setPuntiPerAziende(List<PuntiPerAzienda> puntiPerAziende) {
        this.puntiPerAziende = puntiPerAziende;
    }
}
