package mes.corporation.loyaltyplatform.azienda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Questa interfaccia rappresenta un repository per l'entità Azienda.
 * Fornisce metodi per la ricerca di aziende basata su diversi criteri.
 */
@Repository
public interface AziendaRepository extends JpaRepository<Azienda, Long> {

    /**
     * Ricerca aziende per nome.
     *
     * @param nome Il nome dell'azienda da cercare.
     * @return Una lista di aziende con il nome specificato.
     */
    List<Azienda> findByNome(String nome);

    /**
     * Ricerca un'azienda per partita IVA.
     *
     * @param partitaIva La partita IVA dell'azienda da cercare.
     * @return L'azienda con la partita IVA specificata.
     */
    Azienda findByPartitaIva(String partitaIva);

    /**
     * Ricerca aziende per settore.
     *
     * @param settore Il settore dell'azienda da cercare.
     * @return Una lista di aziende nel settore specificato.
     */
    List<Azienda> findBySettore(String settore);

    /**
     * Ricerca aziende in un certo indirizzo.
     *
     * @param indirizzo L'indirizzo o parte dell'indirizzo da cercare.
     * @return Una lista di aziende con un indirizzo che contiene la stringa specificata.
     */
    List<Azienda> findByIndirizzoContaining(String indirizzo);

}
