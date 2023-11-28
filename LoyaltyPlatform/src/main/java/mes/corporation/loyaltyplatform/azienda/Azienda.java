package mes.corporation.loyaltyplatform.azienda;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.factory.ProgrammaPunti;
import mes.corporation.loyaltyplatform.model.ProgrammaFedelta;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Azienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;
    private String partitaIva;
    private String codiceUnivoco;
    private String ragioneSociale;
    private String settore;
    private String indirizzo;
    private int numeroStabilimenti;

    // Relazione con i programmi di fedeltà
    @OneToMany(mappedBy = "azienda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ProgrammaFedelta> programmiFedelta = new HashSet<>();

    // Costruttori, getter e setter
    public Azienda() {}
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }

    public void setCodiceUnivoco(String codiceUnivoco) {
        this.codiceUnivoco = codiceUnivoco;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getSettore() {
        return settore;
    }

    public void setSettore(String settore) {
        this.settore = settore;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public int getNumeroStabilimenti() {
        return numeroStabilimenti;
    }

    public void setNumeroStabilimenti(int numeroStabilimenti) {
        this.numeroStabilimenti = numeroStabilimenti;
    }

    // Metodo per aggiungere un programma di fedeltà
    public void addProgrammaFedelta(ProgrammaFedelta programma) {
        programmiFedelta.add(programma);
        programma.setAzienda(this);
    }

    // Metodo per rimuovere un programma di fedeltà
    public void removeProgrammaFedelta(ProgrammaFedelta programma) {
        programmiFedelta.remove(programma);
        programma.setAzienda(null);
    }

    public Set<ProgrammaFedelta> getProgrammiFedelta() {
        return programmiFedelta;
    }

    public void setProgrammiFedelta(Set<ProgrammaFedelta> programmiFedelta) {
        this.programmiFedelta = programmiFedelta;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<ProgrammaPunti> getProgrammiPunti() {
        Set<ProgrammaPunti> programmiPunti = new HashSet<>();

        for (ProgrammaFedelta programmaFedelta : programmiFedelta) {
            if (programmaFedelta instanceof ProgrammaPunti) {
                programmiPunti.add((ProgrammaPunti) programmaFedelta);
            }
        }

        return programmiPunti;
    }

}
