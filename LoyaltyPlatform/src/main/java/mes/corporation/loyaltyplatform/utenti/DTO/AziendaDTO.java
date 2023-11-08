package mes.corporation.loyaltyplatform.utenti.DTO;

public class AziendaDTO {
    private String email;
    private String password;
    private DatiPersonaliAziendaDTO datiPersonali;

    public AziendaDTO() { }
    public DatiPersonaliAziendaDTO datiPersonaliAziendaDTO() {
        return datiPersonali;
    }

    public DatiPersonaliAziendaDTO getDatiPersonali() {
        return datiPersonali;
    }
    public void setDatiPersonali(DatiPersonaliAziendaDTO datiPersonali) {
        this.datiPersonali = datiPersonali;
    }

    public AziendaDTO(String email, String password, DatiPersonaliAziendaDTO datiPersonali) {
        this.email = email;
        this.password = password;
        this.datiPersonali = datiPersonali;
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





