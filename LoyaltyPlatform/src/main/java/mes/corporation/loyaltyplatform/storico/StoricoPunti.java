package mes.corporation.loyaltyplatform.storico;

import jakarta.persistence.*;
import mes.corporation.loyaltyplatform.tessera.Tessera;

import java.time.LocalDateTime;

/**
 * La classe StoricoPunti rappresenta un'entità per registrare lo storico dei punti di un cliente.
 * Ogni istanza di questa classe contiene informazioni come l'ID, la tessera associata,
 * l'importo, i punti aggiunti e la data in cui sono stati registrati i punti.
 */
@Entity
public class StoricoPunti {
    /**
     * L'ID univoco dello storico punti.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * La tessera associata a questo storico punti.
     */
    @ManyToOne
    @JoinColumn(name = "tessera_id")
    private Tessera tessera;

    /**
     * L'importo relativo all'acquisto o all'azione che ha generato i punti.
     */
    private Double importo;

    /**
     * Il numero di punti aggiunti a questo storico punti.
     */
    private Integer puntiAggiunti;

    /**
     * La data in cui sono stati registrati i punti.
     */
    private LocalDateTime data;

    /**
     * Costruttore per creare una nuova istanza di StoricoPunti.
     *
     * @param tessera La tessera associata a questo storico punti.
     * @param importo L'importo relativo all'acquisto o all'azione che ha generato i punti.
     * @param puntiAggiunti Il numero di punti aggiunti a questo storico punti.
     * @param data La data in cui sono stati registrati i punti.
     */
    public StoricoPunti(Tessera tessera, Double importo, Integer puntiAggiunti, LocalDateTime data) {
        this.tessera = tessera;
        this.importo = importo;
        this.puntiAggiunti = puntiAggiunti;
        this.data = data;
    }

    /**
     * Costruttore vuoto di default per StoricoPunti.
     */
    public StoricoPunti() {

    }

    /**
     * Restituisce l'ID dello storico punti.
     *
     * @return L'ID dello storico punti.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID dello storico punti.
     *
     * @param id L'ID da impostare.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce la tessera associata a questo storico punti.
     *
     * @return La tessera associata a questo storico punti.
     */
    public Tessera getTessera() {
        return tessera;
    }

    /**
     * Imposta la tessera associata a questo storico punti.
     *
     * @param tessera La tessera da impostare.
     */
    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    /**
     * Restituisce l'importo relativo all'acquisto o all'azione che ha generato i punti.
     *
     * @return L'importo relativo all'acquisto o all'azione.
     */
    public Double getImporto() {
        return importo;
    }

    /**
     * Imposta l'importo relativo all'acquisto o all'azione che ha generato i punti.
     *
     * @param importo L'importo da impostare.
     */
    public void setImporto(Double importo) {
        this.importo = importo;
    }

    /**
     * Restituisce il numero di punti aggiunti a questo storico punti.
     *
     * @return Il numero di punti aggiunti.
     */
    public Integer getPuntiAggiunti() {
        return puntiAggiunti;
    }

    /**
     * Imposta il numero di punti aggiunti a questo storico punti.
     *
     * @param puntiAggiunti Il numero di punti da impostare.
     */
    public void setPuntiAggiunti(Integer puntiAggiunti) {
        this.puntiAggiunti = puntiAggiunti;
    }

    /**
     * Restituisce la data in cui sono stati registrati i punti.
     *
     * @return La data dei punti registrati.
     */
    public LocalDateTime getData() {
        return data;
    }

    /**
     * Imposta la data in cui sono stati registrati i punti.
     *
     * @param data La data da impostare.
     */
    public void setData(LocalDateTime data) {
        this.data = data;
    }
}

