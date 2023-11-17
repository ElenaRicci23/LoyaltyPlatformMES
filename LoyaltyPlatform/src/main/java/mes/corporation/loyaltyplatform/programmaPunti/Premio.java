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

@Entity
public class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private int puntiRichiesti;

    @ManyToOne
    private ProgrammaPunti programmaPunti;
    @ManyToOne
    private CatalogoPremi catalogoPremi;



    public Premio() {
        // Costruttore vuoto richiesto da JPA
    }

    public Premio(String nome, int puntiRichiesti) {
        this.nome = nome;
        this.puntiRichiesti = puntiRichiesti;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPuntiRichiesti() {
        return puntiRichiesti;
    }

    public void setPuntiRichiesti(int puntiRichiesti) {
        this.puntiRichiesti = puntiRichiesti;
    }

    public ProgrammaPunti getProgrammaPunti() {
        return programmaPunti;
    }

    public void setProgrammaPunti(ProgrammaPunti programmaPunti) {
        this.programmaPunti = programmaPunti;
    }

    public CatalogoPremi getCatalogoPremi() {
        return catalogoPremi;
    }

    public void setCatalogoPremi(CatalogoPremi catalogoPremi) {
        this.catalogoPremi = catalogoPremi;
    }
}
