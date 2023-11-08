package mes.corporation.loyaltyplatform.model;

import jakarta.persistence.*;

import java.sql.Date;

// Classe associata ai dati personali del cliente
@Entity
@Table(name = "dati_personali_clienti")
public class DatiPersonaliClienti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private char sesso;
    private String CF;
    private Date dataNascita;
    private String residenza;
    private Long cellulare;
    // altri attributi

    // Costruttori, getter e setter
}
