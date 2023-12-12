package com.example.loyaltyPlatformSicuro.utenti.cliente.tessera;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoricoPuntiRepository extends JpaRepository<StoricoPunti, Long> {
    List<StoricoPunti> findByTesseraId(Long tesseraId);
}

