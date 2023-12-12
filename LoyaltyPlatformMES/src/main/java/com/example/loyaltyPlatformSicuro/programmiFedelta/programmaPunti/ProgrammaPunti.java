package com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti;

import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.programmiFedelta.factory.IProgrammaPunti;
import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.Configurazione.Premio;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Questa classe rappresenta un programma di fedeltà basato su punti.
 */
@Entity
@Table(name = "programma_punti")
public class ProgrammaPunti extends ProgrammaFedelta implements IProgrammaPunti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tasso_conversione")
    private double tassoConversione;

    @OneToMany(mappedBy = "programmaPunti", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Premio> premi = new ArrayList<>();

    /**
     * Restituisce l'ID del programma punti.
     *
     * @return L'ID del programma punti.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID del programma punti.
     *
     * @param id L'ID del programma punti da impostare.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il tasso di conversione dei punti.
     *
     * @return Il tasso di conversione dei punti.
     */
    public double getTassoConversione() {
        return tassoConversione;
    }

    /**
     * Imposta il tasso di conversione dei punti.
     *
     * @param importoSpesoPerPunto L'importo speso per ottenere un punto.
     * @throws IllegalArgumentException Se l'importo speso per punto è zero o negativo.
     */
    public void setTassoConversione(double importoSpesoPerPunto) {
        if (importoSpesoPerPunto <= 0) {
            throw new IllegalArgumentException("L'importo speso per punto deve essere maggiore di zero.");
        }
        this.tassoConversione = 1.0 / importoSpesoPerPunto;
    }

    /**
     * Restituisce la lista dei premi associati al programma punti.
     *
     * @return La lista dei premi del programma punti.
     */
    public List<Premio> getPremi() {
        return premi;
    }

    @Override
    public void aggiungiPremio(Premio premio) {
        if (premio != null) {
            premi.add(premio);
            premio.setProgrammaPunti(this);
        }
    }

    @Override
    public void rimuoviPremio(Premio premio) {
        if (premio != null) {
            premi.remove(premio);
            premio.setProgrammaPunti(null);
        }
    }

    @Override
    public void modificaPremio(Premio premio, String nuovoNome, int nuoviPunti, String nuovaDescrizione) {
        if (premio != null) {
            Premio premioDaModificare = premi.stream()
                    .filter(p -> p.getId().equals(premio.getId()))
                    .findFirst()
                    .orElse(null);

            if (premioDaModificare != null) {
                premioDaModificare.setNome(nuovoNome);
                premioDaModificare.setPuntiDelPremio(nuoviPunti);
                premioDaModificare.setDescrizione(nuovaDescrizione);
            }
        }
    }
}
