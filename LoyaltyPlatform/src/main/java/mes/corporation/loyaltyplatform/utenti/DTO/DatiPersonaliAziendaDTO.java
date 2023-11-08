package mes.corporation.loyaltyplatform.utenti.DTO;

public class DatiPersonaliAziendaDTO {
    private String nome;
    private String partitaIva;
    private String codiceUnivoco;
    private String ragioneSociale;
    private String settore;
    private String indirizzo;
    private int numeroStabilimenti;

    public DatiPersonaliAziendaDTO() {
    }

    public DatiPersonaliAziendaDTO(String nome, String partitaIva, String codiceUnivoco, String ragioneSociale, String settore, String indirizzo, int numeroStabilimenti) {
        this.nome = nome;
        this.partitaIva = partitaIva;
        this.codiceUnivoco = codiceUnivoco;
        this.ragioneSociale = ragioneSociale;
        this.settore = settore;
        this.indirizzo = indirizzo;
        this.numeroStabilimenti = numeroStabilimenti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
