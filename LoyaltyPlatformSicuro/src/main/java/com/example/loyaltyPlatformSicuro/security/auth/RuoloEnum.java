package com.example.loyaltyPlatformSicuro.security.auth;

import java.util.HashSet;
import java.util.Set;

public enum RuoloEnum {
    AZIENDA,
    CLIENTE,
    COMMESSO,
    ADMIN;

    private Set<Privilegio> privileges = new HashSet<>();

    public Set<Privilegio> getPrivileges() {
        return privileges;
    }

    // Metodo per aggiungere un privilegio a un ruolo specifico
    public void addPrivilegio(Privilegio privilegio) {
        privileges.add(privilegio);
    }

    // Metodo per rimuovere un privilegio da un ruolo specifico
    public void removePrivilegio(Privilegio privilegio) {
        privileges.remove(privilegio);
    }
}
