package com.example.loyaltyPlatformSicuro.DTO;

import java.util.List;

public class ProgrammaPuntiDTO {
    private double tassoConversione;
    private List<PremioDTO> premi;

//    private boolean abilitaPuntiCompleanno;
//    private boolean abilitaPuntiIscrizione;

    public double getTassoConversione() {
        return tassoConversione;
    }

    public void setTassoConversione(double tassoConversione) {
        this.tassoConversione = tassoConversione;
    }

    public List<PremioDTO> getPremi() {
        return premi;
    }

    public void setPremi(List<PremioDTO> premi) {
        this.premi = premi;
    }
}
