package com.platforme.cours.catalogue.repository;

import com.platforme.cours.catalogue.model.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NiveauRepository extends JpaRepository<Niveau, Long> {
    boolean existsByNom(String nom);
}