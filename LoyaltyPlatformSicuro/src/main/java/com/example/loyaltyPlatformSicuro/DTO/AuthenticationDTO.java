package com.example.loyaltyPlatformSicuro.DTO;


public class AuthenticationDTO {
    private String email;
    private String password;

    public AuthenticationDTO() {
        // Costruttore vuoto richiesto da Spring
    }

    public AuthenticationDTO(String email, String password) {
        this.email = email;
        this.password = password;
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
