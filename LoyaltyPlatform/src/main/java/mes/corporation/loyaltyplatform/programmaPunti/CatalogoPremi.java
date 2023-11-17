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

package mes.corporation.loyaltyplatform.programmaPunti;


import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.utenti.model.Azienda;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CatalogoPremi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Azienda azienda;

    @OneToMany(mappedBy = "catalogoPremi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Premio> premiDisponibili = new ArrayList<>();

    // Costruttori, getter, setter e altri metodi necessari

    public CatalogoPremi() {
        // Costruttore vuoto richiesto da JPA
    }

    public CatalogoPremi(Azienda azienda) {
        this.azienda = azienda;
    }

    public Long getId() {
        return id;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public List<Premio> getPremiDisponibili() {
        return premiDisponibili;
    }

    public void aggiungiPremio(Premio premio) {
        premiDisponibili.add(premio);
        premio.setCatalogoPremi(this);
    }
}
