package com.example.loyaltyPlatformSicuro.utenti.cliente.tessera;

import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import jakarta.persistence.*;
import java.time.LocalDateTime;
/**
 * Questa classe rappresenta l'entità "StoricoPunti" che registra le operazioni relative ai punti di un cliente.
 */
@Entity
public class StoricoPunti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tessera_id")
    private Tessera tessera;

    @ManyToOne
    @JoinColumn(name = "programma_fedelta_id")
    private ProgrammaFedelta programmaFedelta;

    @Column(name = "punti_guadagnati")
    private int puntiGuadagnati;

    @Column(name = "punti_utilizzati")
    private int puntiUtilizzati;

    @Column(name = "operazione")
    private int operazione;

    @Column(name = "punti")
    private int punti;

    @Column(name = "data_operazione")
    private LocalDateTime dataOperazione;

    /**
     * Restituisce l'ID dello storico dei punti.
     *
     * @return l'ID dello storico dei punti
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID dello storico dei punti.
     *
     * @param id l'ID dello storico dei punti da impostare
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce la tessera associata a questo storico dei punti.
     *
     * @return la tessera associata
     */
    public Tessera getTessera() {
        return tessera;
    }

    /**
     * Imposta la tessera associata a questo storico dei punti.
     *
     * @param tessera la tessera da impostare
     */
    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    /**
     * Restituisce il programma fedeltà associato a questo storico dei punti.
     *
     * @return il programma fedeltà associato
     */
    public ProgrammaFedelta getProgrammaFedelta() {
        return programmaFedelta;
    }

    /**
     * Imposta il programma fedeltà associato a questo storico dei punti.
     *
     * @param programmaFedelta il programma fedeltà da impostare
     */
    public void setProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        this.programmaFedelta = programmaFedelta;
    }

    /**
     * Restituisce il numero di punti guadagnati in questa operazione.
     *
     * @return il numero di punti guadagnati
     */
    public int getPuntiGuadagnati() {
        return puntiGuadagnati;
    }

    /**
     * Imposta il numero di punti guadagnati in questa operazione.
     *
     * @param puntiGuadagnati il numero di punti guadagnati da impostare
     */
    public void setPuntiGuadagnati(int puntiGuadagnati) {
        this.puntiGuadagnati = puntiGuadagnati;
    }

    /**
     * Restituisce il numero di punti utilizzati in questa operazione.
     *
     * @return il numero di punti utilizzati
     */
    public int getPuntiUtilizzati() {
        return puntiUtilizzati;
    }

    /**
     * Imposta il numero di punti utilizzati in questa operazione.
     *
     * @param puntiUtilizzati il numero di punti utilizzati da impostare
     */
    public void setPuntiUtilizzati(int puntiUtilizzati) {
        this.puntiUtilizzati = puntiUtilizzati;
    }

    /**
     * Restituisce la data e l'ora dell'operazione.
     *
     * @return la data e l'ora dell'operazione
     */
    public LocalDateTime getDataOperazione() {
        return dataOperazione;
    }

    /**
     * Imposta la data e l'ora dell'operazione.
     *
     * @param dataOperazione la data e l'ora dell'operazione da impostare
     */
    public void setDataOperazione(LocalDateTime dataOperazione) {
        this.dataOperazione = dataOperazione;
    }

    /**
     * Restituisce il tipo di operazione (ad esempio, guadagno o utilizzo di punti).
     *
     * @return il tipo di operazione
     */
    public int getOperazione() {
        return operazione;
    }

    /**
     * Imposta il tipo di operazione (ad esempio, guadagno o utilizzo di punti).
     *
     * @param operazione il tipo di operazione da impostare
     */
    public void setOperazione(int operazione) {
        this.operazione = operazione;
    }

    /**
     * Restituisce il saldo totale dei punti dopo questa operazione.
     *
     * @return il saldo totale dei punti
     */
    public int getPunti() {
        return punti;
    }

    /**
     * Imposta il saldo totale dei punti dopo questa operazione.
     *
     * @param punti il saldo totale dei punti da impostare
     */
    public void setPunti(int punti) {
        this.punti = punti;
    }
}
