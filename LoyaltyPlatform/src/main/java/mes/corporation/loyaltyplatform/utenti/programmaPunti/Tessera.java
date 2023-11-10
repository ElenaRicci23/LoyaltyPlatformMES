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
import mes.corporation.loyaltyplatform.utenti.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Entity
public class Tessera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente proprietario;

    @OneToMany(mappedBy = "tessera", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PuntiPerAzienda> puntiPerAziende = new ArrayList<>();

    // Costruttori, getter, setter e altri metodi necessari

    public void accumulaPunti(Azienda azienda, int punti) {
        // Metodo per accumulare punti per un'azienda sulla tessera

        // Verifica se esiste già un'istanza di PuntiAzienda per l'azienda specificata
        Optional<PuntiPerAzienda> puntiAziendaEsistente = puntiPerAziende.stream()
                .filter(pa -> pa.getAzienda().equals(azienda))
                .findFirst();

        if (puntiAziendaEsistente.isPresent()) {
            // Aggiorna i punti se l'istanza esiste già
            PuntiPerAzienda puntiAzienda = puntiAziendaEsistente.get();
            puntiAzienda.setPunti(puntiAzienda.getPunti() + punti);
        } else {
            // Crea una nuova istanza di PuntiAzienda e aggiungila alla lista
            PuntiPerAzienda nuovoPuntiAzienda = new PuntiPerAzienda(this, azienda, punti);
            puntiPerAziende.add(nuovoPuntiAzienda);
        }
    }

    public int getPuntiPerAzienda(Azienda azienda) {
        // Metodo per ottenere il numero di punti accumulati per un'azienda sulla tessera

        // Cerca l'istanza di PuntiAzienda per l'azienda specificata
        Optional<PuntiPerAzienda> puntiAzienda = puntiPerAziende.stream()
                .filter(pa -> pa.getAzienda().equals(azienda))
                .findFirst();

        return puntiAzienda.map(PuntiPerAzienda::getPunti).orElse(0);
    }
}
