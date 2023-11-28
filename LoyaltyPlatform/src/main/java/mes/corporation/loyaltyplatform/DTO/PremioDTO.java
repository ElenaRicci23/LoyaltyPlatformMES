package mes.corporation.loyaltyplatform.DTO;

public class PremioDTO {
    private String nome;
    private String descrizione;
    private int puntiRichiesti;
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getPuntiRichiesti() {
        return puntiRichiesti;
    }

    public void setPuntiRichiesti(int puntiRichiesti) {
        this.puntiRichiesti = puntiRichiesti;
    }



}

