package com.platforme.cours.catalogue.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "niveaux")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Niveau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

}
