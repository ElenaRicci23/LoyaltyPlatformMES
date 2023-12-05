package com.example.loyaltyPlatformSicuro.programmiFedelta.repo;

import com.example.loyaltyPlatformSicuro.programmiFedelta.ProgrammaFedelta;
import com.example.loyaltyPlatformSicuro.utenti.azienda.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgrammaFedeltaRepository extends JpaRepository<ProgrammaFedelta, Long> {

    ProgrammaFedelta findByNome(String nome);

    List<ProgrammaFedelta> findByAziendaId(Long aziendaId);

    List<ProgrammaFedelta> findByAzienda(Azienda azienda);
}
