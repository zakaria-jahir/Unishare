package com.platforme.cours.catalogue.repository;

import com.platforme.cours.catalogue.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    List<Matiere> findByFiliereId(Long filiereId);
    boolean existsByNomAndFiliereId(String nom, Long filiereId);
}