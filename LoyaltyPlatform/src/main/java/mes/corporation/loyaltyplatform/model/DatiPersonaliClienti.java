package mes.corporation.loyaltyplatform.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.regex.Pattern;

@Entity
    @Table(name = "dati_personali_clienti")
    public class DatiPersonaliClienti {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private char sesso;
        private String codiceFiscale;
        private Date dataNascita;
        private String residenza;
        private Long cellulare;
        // altri attributi

        // Costruttore vuoto
        public DatiPersonaliClienti() {
        }

        // Costruttore con parametri
        public DatiPersonaliClienti(char sesso, String codiceFiscale, Date dataNascita, String residenza, Long cellulare) {
            setSesso(sesso);
            setCellulare(cellulare);
            setCodiceFiscale(codiceFiscale);
            setResidenza(residenza);
            setDataNascita(dataNascita);
        }

        // Getter e Setter
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public char getSesso() {
            return sesso;
        }

    public void setSesso(char sesso) {
        if (!isValidSesso(sesso))
            throw new IllegalArgumentException("Sesso non valido");
        this.sesso = sesso;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        if (!isValidCodiceFiscale(codiceFiscale))
            throw new IllegalArgumentException("Codice fiscale non valido");
        this.codiceFiscale = codiceFiscale;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        if (!isValidDataNascita(dataNascita))
            throw new IllegalArgumentException("Data di nascita non valida");
        this.dataNascita = dataNascita;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        if (!isValidResidenza(residenza))
            throw new IllegalArgumentException("Residenza non valida");
        this.residenza = residenza;
    }

    public Long getCellulare() {
        return cellulare;
    }

    public void setCellulare(Long cellulare) {
        if (!isValidCellulare(cellulare))
            throw new IllegalArgumentException("Numero di cellulare non valido");
        this.cellulare = cellulare;
    }



    public static boolean isValidCodiceFiscale(String codiceFiscale) {
        final String CF_REGEX = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$";
        // Verifica che il Codice Fiscale corrisponda al pattern definito
        return Pattern.matches(CF_REGEX, codiceFiscale);
    }

    public static boolean isValidDataNascita(Date dataNascita) {
        if (dataNascita == null) {
            return false; // La data di nascita non deve essere nulla
        }

        // Verifica che la data di nascita sia successiva all'anno 1900
        Calendar calMin = Calendar.getInstance();
        calMin.set(Calendar.YEAR, 1900);

        // Verifica che la data di nascita sia precedente all'anno attuale
        Calendar calMax = Calendar.getInstance();

        // Imposta la data massima a oggi
        calMax.setTime(new java.util.Date());

        // Confronta la data di nascita con i limiti
        return dataNascita.after(new Date(calMin.getTimeInMillis())) && dataNascita.before(new Date(calMax.getTimeInMillis()));
    }

    public static boolean isValidSesso(char sesso) {
        return sesso == 'M' || sesso == 'F' ;
    }

    public static boolean isValidCellulare(Long cellulare) {
        if ((cellulare == null) || (cellulare <= 0) || (cellulare > 999999999)) {
            return false; // Il numero di cellulare deve essere positivo, non nullo e inferiore a 10 cifre
        }
        return true;
    }

    public static boolean isValidResidenza(String residenza) {
        if (residenza == null || residenza.trim().isEmpty()) {
            return false; // La residenza non deve essere nulla o vuota
        }
        return true;
    }

}


