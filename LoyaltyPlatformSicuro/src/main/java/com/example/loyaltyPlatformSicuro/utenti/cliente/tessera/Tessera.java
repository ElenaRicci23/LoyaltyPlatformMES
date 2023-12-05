package com.example.loyaltyPlatformSicuro.utenti.cliente.tessera;


import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.ProgrammaPunti;
import com.example.loyaltyPlatformSicuro.utenti.cliente.Cliente;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codiceABarreUnivoco;

    private LocalDate dataEmissione;
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


    public Set<ProgrammaFedelta> getProgrammiFedelta() {
        return programmiFedelta;
    }

    public void addProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        programmiFedelta.add(programmaFedelta);
    }


    public Map<ProgrammaPunti, Integer> getPuntiProgrammaPunti() {
        return puntiProgrammaPunti;
    }

    public void setPuntiProgrammaPunti(Map<ProgrammaPunti, Integer> puntiProgrammaPunti) {
        this.puntiProgrammaPunti = puntiProgrammaPunti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodiceABarreUnivoco() {
        return codiceABarreUnivoco;
    }

    public void setCodiceABarreUnivoco(String codiceABarreUnivoco) {
        this.codiceABarreUnivoco = codiceABarreUnivoco;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public void setCodiceBarre() {
        // Genera un codice a barre casuale (ad esempio, di 10 cifre)
        Random random = new Random();
        StringBuilder codiceBarreBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int cifra = random.nextInt(10); // Genera una cifra casuale da 0 a 9
            codiceBarreBuilder.append(cifra);
        }
        this.codiceABarreUnivoco = codiceBarreBuilder.toString();
    }


}
