package com.example.pf.DTO;

import com.example.pf.azienda.Azienda;

public class AziendaDTO {

    private String nome;

    private String email;
    private String partitaIva;
    private String codiceUnivoco;
    private String ragioneSociale;
    private String settore;
    private String indirizzo;
    private int numeroStabilimenti;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public static Azienda convertToEntity(AziendaDTO aziendaDTO) {
        Azienda azienda = new Azienda();
        azienda.setNome(aziendaDTO.getNome());
        azienda.setEmail(aziendaDTO.getEmail());
        azienda.setPartitaIva(aziendaDTO.getPartitaIva());
        azienda.setCodiceUnivoco(aziendaDTO.getCodiceUnivoco());
        azienda.setRagioneSociale(aziendaDTO.getRagioneSociale());
        azienda.setSettore(aziendaDTO.getSettore());
        azienda.setIndirizzo(aziendaDTO.getIndirizzo());
        azienda.setNumeroStabilimenti(aziendaDTO.getNumeroStabilimenti());

        return azienda;
    }


    public static AziendaDTO convertToDTO(Azienda azienda) {
        AziendaDTO aziendaDTO = new AziendaDTO();
        aziendaDTO.setNome(azienda.getNome());
        aziendaDTO.setEmail(azienda.getEmail());
        aziendaDTO.setPartitaIva(azienda.getPartitaIva());
        aziendaDTO.setCodiceUnivoco(azienda.getCodiceUnivoco());
        aziendaDTO.setRagioneSociale(azienda.getRagioneSociale());
        aziendaDTO.setSettore(azienda.getSettore());
        aziendaDTO.setIndirizzo(azienda.getIndirizzo());
        aziendaDTO.setNumeroStabilimenti(azienda.getNumeroStabilimenti());
        return aziendaDTO;
    }


}
