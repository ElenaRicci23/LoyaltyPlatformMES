package mes.corporation.loyaltyplatform.factory;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.cliente.Cliente;
import mes.corporation.loyaltyplatform.model.Premio;
import mes.corporation.loyaltyplatform.model.ProgrammaFedelta;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ProgrammaPunti extends ProgrammaFedelta implements IProgrammaPunti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "programmaPunti", cascade = CascadeType.ALL)
    private List<Premio> premi = new ArrayList<>();
    private double tassoConversione;
    private int puntiBonus;
    private String messaggioPersonalizzato;
    private String occasioneSpeciale;
    private String eccezione;

    public ProgrammaPunti() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Premio> getPremi() {
        return premi;
    }

    public void setPremi(List<Premio> premi) {
        this.premi = premi;
    }

    public double getTassoConversione() {
        return tassoConversione;
    }

    public void setTassoConversione(double tassoConversione) {
        this.tassoConversione = tassoConversione;
    }

    public int getPuntiBonus() {
        return puntiBonus;
    }

    public void setPuntiBonus(int puntiBonus) {
        this.puntiBonus = puntiBonus;
    }

    public String getMessaggioPersonalizzato() {
        return messaggioPersonalizzato;
    }

    public void setMessaggioPersonalizzato(String messaggioPersonalizzato) {
        this.messaggioPersonalizzato = messaggioPersonalizzato;
    }

    @Override
    public int accumulaPunti(double importoAcquisto) {
        // Verifica se il tasso di conversione è valido e maggiore di zero
        if (tassoConversione > 0) {
            return (int) (importoAcquisto / tassoConversione);
        } else {
            return 0; // Se il tasso di conversione non è valido, restituisci 0 punti
        }
    }
    @Override
    public int getPuntiTotali() {
        // Implementa la logica per ottenere il totale dei punti accumulati fino a questo momento
        // Restituisci il valore dei punti accumulati
        return 0; // Valore fittizio, devi implementare questa logica
    }

    @Override
    public void aggiungiPremio(Premio premio) {
        premi.add(premio);
    }


    public void modificaPremio(Long idPremio, Premio premio) {
        for (Premio existingPremio : premi) {
            if (existingPremio.getId().equals(idPremio)) {
                // Trovato il premio da modificare
                existingPremio.setNome(premio.getNome());
                existingPremio.setPuntiDelPremio(premio.getPuntiDelPremio());
                existingPremio.setDescrizione(premio.getDescrizione());
                return; // Esci dal ciclo una volta effettuata la modifica
            }
        }
        // Se l'ID non corrisponde a nessun premio esistente, puoi gestire l'errore qui
    }


    public void rimuoviPremio(Long idPremio) {
        Premio premioDaRimuovere = null;
        for (Premio existingPremio : premi) {
            if (existingPremio.getId().equals(idPremio)) {
                // Trovato il premio da rimuovere
                premioDaRimuovere = existingPremio;
                break; // Esci dal ciclo una volta trovato
            }
        }
        if (premioDaRimuovere != null) {
            premi.remove(premioDaRimuovere); // Rimuovi il premio
        } else {
            // Se l'ID non corrisponde a nessun premio esistente, puoi gestire l'errore qui
        }
    }


    @Override
    public List<Premio> getPremiDisponibili() {
        // Implementa la logica per ottenere la lista dei premi disponibili nel programma fedeltà
        return premi;
    }

    @Override
    public void impostaTassoConversione(double tasso) {
        this.tassoConversione = tasso;
    }

    @Override
    public void impostaPuntiBonus(int puntiBonus) {
        this.puntiBonus = puntiBonus;
    }

    @Override
    public void aggiungiPremioPersonalizzato(Premio premio) {
        // Implementa la logica per aggiungere un nuovo premio personalizzato al programma fedeltà
    }

    @Override
    public void modificaPremioPersonalizzato(int idPremio, Premio premio) {
        // Implementa la logica per modificare un premio personalizzato esistente nel programma fedeltà
    }

    @Override
    public void rimuoviPremioPersonalizzato(int idPremio) {
        // Implementa la logica per rimuovere un premio personalizzato dal programma fedeltà
    }

    @Override
    public void personalizzaMessaggiComunicazioni(String messaggioPersonalizzato) {
        this.messaggioPersonalizzato = messaggioPersonalizzato;
    }

    @Override
    public void offriRicompenseSpeciali(String occasioneSpeciale) {
        this.occasioneSpeciale = occasioneSpeciale;
    }

    @Override
    public void gestisciEccezioni(String eccezione) {
        this.eccezione = eccezione;
    }

    @Override
    public void registraAttivitaCliente(Cliente cliente, String attivita) {
        // Implementa la logica per registrare l'attività dei clienti, come transazioni o eventi speciali
    }

    @Override
    public String generaStatisticheReport() {
        // Implementa la logica per generare statistiche o report sul programma fedeltà
        // Restituisci una stringa contenente il report
        return null; // Valore fittizio, devi implementare questa logica
    }

    @Override
    public void configuraParametriAvanzati(String parametro, String valore) {
        // Implementa la logica per configurare parametri avanzati
        // Utilizza i parametri parametro e valore per impostare le opzioni desiderate
    }
}
