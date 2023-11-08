package mes.corporation.loyaltyplatform.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clienti")
public class Cliente extends Utente {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dati_personali_id", referencedColumnName = "id")
    private DatiPersonaliClienti datiPersonali;


    public void visualizzaProfilo() {
        // Implementazione del metodo
    }

    // altri metodi come modificaProfilo, eliminaProfilo, ecc...

    public Cliente(String nome, String cognome, String email, String password, DatiPersonaliClienti datiPersonali) {
        super(nome, cognome, email, password);
        if (!isValidEmailForCliente(email)) {
            throw new IllegalArgumentException("L'email deve essere associata a un individuo.");
        }
        this.datiPersonali = datiPersonali;
    }

    // Metodo di validazione dell'email per un cliente
    private boolean isValidEmailForCliente(String email) {
        // Aggiungi qui la tua logica di validazione per l'email dell'utente cliente
        // Ad esempio, potresti verificare se l'email contiene un dominio specifico o altri criteri
        // Restituisci true se l'email è valida per un cliente, altrimenti false.
        return true; // Modifica questa logica di validazione secondo le tue esigenze.
    }
}
