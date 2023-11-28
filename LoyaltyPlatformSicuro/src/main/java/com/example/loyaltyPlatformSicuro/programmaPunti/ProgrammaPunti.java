package com.example.loyaltyPlatformSicuro.programmaPunti;

/*
 * MIT License
 *
 * Copyright (c) 2023 Di Felice Matteo, Ricci Elena, Avaltroni Sara
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import com.example.loyaltyPlatformSicuro.fedelta.model.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.utenti.model.Azienda;
import com.example.loyaltyPlatformSicuro.utenti.model.Tessera;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ProgrammaPunti extends ProgrammaFedelta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Tessera tessera;

    @ManyToOne
    private Azienda azienda;

    final double rapportoPuntiSpesa = 1;
    private int punti;

    @OneToMany(mappedBy = "programmaPunti", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Premio> premiDisponibili = new ArrayList<>();


    public ProgrammaPunti(Tessera tessera, Azienda azienda, double rapportoPuntiSpesa, int punti) {
        this.tessera = tessera;
        this.azienda = azienda;
        this.punti = punti;
    }

    public ProgrammaPunti() {

    }

    public Long getId() {
        return id;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public List<Premio> getPremiDisponibili() {
        return premiDisponibili;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public void setPremiDisponibili(List<Premio> premiDisponibili) {
        this.premiDisponibili = premiDisponibili;
    }

    public double getRapportoPuntiSpesa() {
        return rapportoPuntiSpesa;
    }

    public void aggiungiPremio(Premio premio) {
        // Metodo per aggiungere un premio al catalogo premi
        premiDisponibili.add(premio);
        premio.setProgrammaPunti(this);
    }
}
