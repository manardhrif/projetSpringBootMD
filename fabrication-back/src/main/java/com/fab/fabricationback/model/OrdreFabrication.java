package com.fab.fabricationback.model;

import com.fab.fabricationback.model.enums.EtatOrdre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrdreFabrication {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String projet;
        //les relations avec employe et machine
        @ManyToOne
        private Employe employe;

        @ManyToOne
        private Machine machine;

        @ManyToOne
        private Produit produit;


        @PositiveOrZero(message = "ne doit pas etre negatif")
        private int quantite;

        private LocalDate date;


        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private EtatOrdre etat;
// Exemple : "Planifié", "En cours", "Terminé"
    }


