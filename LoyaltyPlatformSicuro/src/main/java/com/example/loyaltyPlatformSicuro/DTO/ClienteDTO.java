package com.example.loyaltyPlatformSicuro.DTO;


public class ClienteDTO {


    private Long Id;
    private String email;
    private String password;
    private DatiPersonaliClienteDTO datiPersonali;

    public ClienteDTO(String email, String password, DatiPersonaliClienteDTO datiPersonali) {
        this.email = email;
        this.password = password;
        this.datiPersonali = datiPersonali;
    }

    public ClienteDTO() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public DatiPersonaliClienteDTO getDatiPersonali() {
        return datiPersonali;
    }

    public void setDatiPersonali(DatiPersonaliClienteDTO datiPersonali) {
        this.datiPersonali = datiPersonali;
    }
}


