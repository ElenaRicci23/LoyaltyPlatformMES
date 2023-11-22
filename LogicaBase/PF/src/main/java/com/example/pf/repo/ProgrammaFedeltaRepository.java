package com.example.pf.repo;


import com.example.pf.model.ProgrammaFedelta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammaFedeltaRepository extends JpaRepository<ProgrammaFedelta, Long> {

    ProgrammaFedelta findByNome(String nome);
}
