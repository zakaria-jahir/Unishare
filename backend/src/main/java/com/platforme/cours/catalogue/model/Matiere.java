package com.platforme.cours.catalogue.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "matieres",
        uniqueConstraints = @UniqueConstraint(columnNames = {"nom", "filiere_id"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;
}
