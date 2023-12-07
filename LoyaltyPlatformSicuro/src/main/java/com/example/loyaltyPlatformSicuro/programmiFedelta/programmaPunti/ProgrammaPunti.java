package com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti;


import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.programmiFedelta.factory.IProgrammaPunti;
import com.example.loyaltyPlatformSicuro.programmiFedelta.programmaPunti.Configurazione.Premio;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTassoConversione() {
        return tassoConversione;
    }

    public void setTassoConversione(double importoSpesoPerPunto) {
        if (importoSpesoPerPunto <= 0) {
            throw new IllegalArgumentException("L'importo speso per punto deve essere maggiore di zero.");
        }
        this.tassoConversione = 1.0 / importoSpesoPerPunto;
    }

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

//    public void assegnaPuntiBonusCompleanno(Cliente cliente, Azienda azienda) {
//        if (azienda.getAbilitaPuntiCompleanno()) {
//            LocalDate oggi = LocalDate.now();
//            LocalDate compleannoCliente = cliente.getDataCompleanno();
//
//            if (compleannoCliente != null && oggi.getMonth() == compleannoCliente.getMonth() && oggi.getDayOfMonth() == compleannoCliente.getDayOfMonth()) {
//                // Il cliente ha compleanno oggi, assegna punti bonus
//                int puntiBonus = 100; // Modifica a seconda dei tuoi requisiti
//                cliente.aggiungiPunti(puntiBonus); // Supponiamo che ci sia un metodo per aggiungere punti al cliente
//            }
//        }
//    }
//
//    public void iscriviClienteAlProgrammaFedelta(Cliente cliente, Azienda azienda) {
//        if (azienda.getAbilitaPuntiIscrizione()) {
//            // Logica per l'iscrizione del cliente al programma fedeltà
//
//            int puntiBonus = 50; // Modifica a seconda dei tuoi requisiti
//            cliente.aggiungiPunti(puntiBonus); // Assegna punti bonus al momento dell'iscrizione
//        }
//    }


