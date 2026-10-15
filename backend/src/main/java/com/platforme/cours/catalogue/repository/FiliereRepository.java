package com.platforme.cours.catalogue.repository;

import com.platforme.cours.catalogue.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    boolean existsByNom(String nom);
}
