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

package mes.corporation.loyaltyplatform.utenti.programmaPunti;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.utenti.model.Azienda;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class ProgrammaPunti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tessera tessera;

    @ManyToOne
    private Azienda azienda;

    private int punti;


    @OneToMany(mappedBy = "programmaPunti", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Premio> premiDisponibili = new ArrayList<>();

    // Costruttori, getter, setter e altri metodi necessari

    public void aggiungiPremio(Premio premio) {
        // Metodo per aggiungere un premio al catalogo premi
    }

    public List<Premio> getPremiDisponibili() {
        // Metodo per ottenere la lista dei premi disponibili nel catalogo
        return null;
    }
}
