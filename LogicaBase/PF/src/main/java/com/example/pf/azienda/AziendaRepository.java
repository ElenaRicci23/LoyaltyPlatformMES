package com.example.pf.azienda;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda, Long> {

    // Ricerca di aziende per nome
    List<Azienda> findByNome(String nome);

    // Ricerca di aziende per partita IVA
    Azienda findByPartitaIva(String partitaIva);

    // Ricerca di aziende per settore
    List<Azienda> findBySettore(String settore);

    // Ricerca di aziende in un certo indirizzo
    List<Azienda> findByIndirizzoContaining(String indirizzo);

}


