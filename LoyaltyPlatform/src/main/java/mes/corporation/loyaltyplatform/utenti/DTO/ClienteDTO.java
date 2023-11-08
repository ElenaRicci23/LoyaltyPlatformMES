package mes.corporation.loyaltyplatform.utenti.DTO;

public class ClienteDTO {
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private DatiPersonaliClienteDTO datiPersonali;

    public DatiPersonaliClienteDTO getDatiPersonali() {
        return datiPersonali;
    }

    public void setDatiPersonali(DatiPersonaliClienteDTO datiPersonali) {
        this.datiPersonali = datiPersonali;
    }


    public ClienteDTO(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
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
