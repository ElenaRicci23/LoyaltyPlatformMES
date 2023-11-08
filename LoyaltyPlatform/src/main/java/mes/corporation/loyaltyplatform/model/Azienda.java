package mes.corporation.loyaltyplatform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "aziende")
public class Azienda extends Utente {

    @Column(nullable = false, unique = true)
    private String aziendaEmail;

    // Altri campi specifici dell'azienda

    public Azienda(String nome, String cognome, String aziendaEmail, String password) {
        super(nome, cognome, null, password); // Non impostiamo l'email dell'utente base
        if (!isValidAziendaEmail(aziendaEmail)) {
            throw new IllegalArgumentException("L'email aziendale deve avere un dominio specifico.");
        }
        this.aziendaEmail = aziendaEmail;
    }

    public Azienda() {

    }

    // Metodo di validazione dell'email aziendale
    private boolean isValidAziendaEmail(String email) {
        // Implementa qui la logica per verificare se l'email aziendale ha il dominio specifico.
        // Ad esempio, potresti controllare se l'email termina con il dominio aziendale desiderato.
        String aziendaDomain = "@azienda.com"; // Sostituisci con il tuo dominio aziendale.
        return email.endsWith(aziendaDomain);
    }
}

