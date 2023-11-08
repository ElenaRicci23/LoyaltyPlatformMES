package mes.corporation.loyaltyplatform.utenti.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    protected String email;
    protected String password;

    public Utente() {
    }

    public Utente(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
