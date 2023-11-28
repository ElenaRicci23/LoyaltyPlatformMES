package mes.corporation.loyaltyplatform.tessera;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.cliente.Cliente;
import mes.corporation.loyaltyplatform.factory.ProgrammaPunti;
import mes.corporation.loyaltyplatform.model.ProgrammaFedelta;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Rappresenta una tessera di un cliente e tiene traccia dei punti accumulati con le aziende.
 */
@Entity
public class Tessera {
    /**
     * L'ID univoco della tessera.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Aggiungi una referenza al cliente proprietario
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id") // Questo è importante per la relazione
    private Cliente cliente;

    /**
     * Insieme dei programmi fedeltà associati a questa tessera.
     */
    @ManyToMany
    @JoinTable(
            name = "tessera_programma_fedelta",
            joinColumns = @JoinColumn(name = "tessera_id"),
            inverseJoinColumns = @JoinColumn(name = "programma_fedelta_id")
    )
    private Set<ProgrammaFedelta> programmiFedelta = new HashSet<>();

    /**
     * Mappa dei punti accumulati nei programmi punti associati a questa tessera.
     */
    @ElementCollection
    @CollectionTable(name = "tessera_programma_punti", joinColumns = @JoinColumn(name = "tessera_id"))
    @MapKeyJoinColumn(name = "programma_punti_id")
    @Column(name = "punti")
    private Map<ProgrammaPunti, Integer> puntiProgrammaPunti = new HashMap<>();

    /**
     * Aggiunge un programma fedeltà alla tessera.
     *
     * @param programmaFedelta Il programma fedeltà da aggiungere.
     */
    public void addProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        programmiFedelta.add(programmaFedelta);
    }

    /**
     * Rimuove un programma fedeltà dalla tessera.
     *
     * @param programmaFedelta Il programma fedeltà da rimuovere.
     */
    public void removeProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        programmiFedelta.remove(programmaFedelta);
    }

    /**
     * Costruttore vuoto di default per Tessera.
     */
    public Tessera() {}

    /**
     * Restituisce la mappa dei punti accumulati nei programmi punti associati a questa tessera.
     *
     * @return La mappa dei punti dei programmi punti.
     */
    public Map<ProgrammaPunti, Integer> getPuntiProgrammaPunti() {
        return puntiProgrammaPunti;
    }

    /**
     * Imposta la mappa dei punti accumulati nei programmi punti associati a questa tessera.
     *
     * @param puntiProgrammaPunti La mappa dei punti dei programmi punti da impostare.
     */
    public void setPuntiProgrammaPunti(Map<ProgrammaPunti, Integer> puntiProgrammaPunti) {
        this.puntiProgrammaPunti = puntiProgrammaPunti;
    }

    /**
     * Restituisce il cliente proprietario di questa tessera.
     *
     * @return Il cliente proprietario della tessera.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Imposta il cliente proprietario di questa tessera.
     *
     * @param cliente Il cliente proprietario da impostare.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Costruttore che accetta un cliente e lo assegna alla tessera.
     *
     * @param cliente Il cliente da associare alla tessera.
     */
    public Tessera(Cliente cliente) {
        this.cliente = cliente; // Assegna il cliente alla tessera
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
     * Restituisce l'insieme dei programmi fedeltà associati a questa tessera.
     *
     * @return L'insieme dei programmi fedeltà associati alla tessera.
     */
    public Set<ProgrammaFedelta> getProgrammiFedelta() {
        return programmiFedelta;
    }

    /**
     * Imposta l'insieme dei programmi fedeltà associati a questa tessera.
     *
     * @param programmiFedelta L'insieme dei programmi fedeltà da impostare.
     */
    public void setProgrammiFedelta(Set<ProgrammaFedelta> programmiFedelta) {
        this.programmiFedelta = programmiFedelta;
    }

    /**
     * Imposta l'ID della tessera.
     *
     * @param id L'ID da impostare.
     */
    public void setId(Long id) {
        this.id = id;
    }
}

