package com.example.loyaltyPlatformSicuro.DTO;

public class AziendaDTO {

    private Long id;

    private String email;
    private String password;
    private DatiPersonaliAziendaDTO datiPersonali;


    public AziendaDTO(String email, String password, DatiPersonaliAziendaDTO datiPersonali) {
        this.email = email;
        this.password = password;
        this.datiPersonali = datiPersonali;
    }

    public AziendaDTO() {
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


    public DatiPersonaliAziendaDTO getDatiPersonali() {
        return datiPersonali;
    }


    public void setDatiPersonali(DatiPersonaliAziendaDTO datiPersonali) {
        this.datiPersonali = datiPersonali;
    }
}