package com.example.loyaltyPlatformSicuro.utenti.cliente.tessera;

import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.ProgrammaPunti;
import com.example.loyaltyPlatformSicuro.utenti.cliente.Cliente;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;
/**
 * Questa classe rappresenta un'entità "Tessera" che è associata a un cliente e tiene traccia delle tessere emesse e dei programmi fedeltà associati.
 */
@Entity
@Table(name = "tessera")
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codiceABarreUnivoco;

    @Column(name = "data_emissione")
    private LocalDate dataEmissione;

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

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
     * Costruttore vuoto per la classe Tessera.
     */
    public Tessera() {
    }

    /**
     * Restituisce l'ID della tessera.
     *
     * @return l'ID della tessera
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID della tessera.
     *
     * @param id l'ID della tessera da impostare
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il codice a barre univoco associato a questa tessera.
     *
     * @return il codice a barre univoco
     */
    public String getCodiceABarreUnivoco() {
        return codiceABarreUnivoco;
    }

    /**
     * Imposta il codice a barre univoco associato a questa tessera.
     *
     * @param codiceABarreUnivoco il codice a barre univoco da impostare
     */
    public void setCodiceABarreUnivoco(String codiceABarreUnivoco) {
        this.codiceABarreUnivoco = codiceABarreUnivoco;
    }

    /**
     * Restituisce la data di emissione della tessera.
     *
     * @return la data di emissione della tessera
     */
    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    /**
     * Imposta la data di emissione della tessera.
     *
     * @param dataEmissione la data di emissione da impostare
     */
    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    /**
     * Restituisce la data di scadenza della tessera.
     *
     * @return la data di scadenza della tessera
     */
    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Imposta la data di scadenza della tessera.
     *
     * @param dataScadenza la data di scadenza da impostare
     */
    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    /**
     * Restituisce il cliente associato a questa tessera.
     *
     * @return il cliente associato
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Imposta il cliente associato a questa tessera.
     *
     * @param cliente il cliente da associare
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Restituisce l'insieme dei programmi fedeltà associati a questa tessera.
     *
     * @return l'insieme dei programmi fedeltà associati
     */
    public Set<ProgrammaFedelta> getProgrammiFedelta() {
        return programmiFedelta;
    }

    /**
     * Aggiunge un programma fedeltà all'insieme dei programmi associati a questa tessera.
     *
     * @param programmaFedelta il programma fedeltà da aggiungere
     */
    public void addProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        programmiFedelta.add(programmaFedelta);
    }

    /**
     * Restituisce la mappa dei punti accumulati nei programmi punti associati a questa tessera.
     *
     * @return la mappa dei punti
     */
    public Map<ProgrammaPunti, Integer> getPuntiProgrammaPunti() {
        return puntiProgrammaPunti;
    }

    /**
     * Imposta la mappa dei punti accumulati nei programmi punti associati a questa tessera.
     *
     * @param puntiProgrammaPunti la mappa dei punti da impostare
     */
    public void setPuntiProgrammaPunti(Map<ProgrammaPunti, Integer> puntiProgrammaPunti) {
        this.puntiProgrammaPunti = puntiProgrammaPunti;
    }

    /**
     * Imposta un codice a barre univoco generato casualmente per questa tessera.
     */
    public void setCodiceBarre() {
        Random random = new Random();
        StringBuilder codiceBarreBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int cifra = random.nextInt(10);
            codiceBarreBuilder.append(cifra);
        }
        this.codiceABarreUnivoco = codiceBarreBuilder.toString();
    }
}
