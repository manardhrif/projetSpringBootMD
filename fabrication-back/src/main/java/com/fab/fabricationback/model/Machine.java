package com.fab.fabricationback.model;
import com.fab.fabricationback.model.enums.EtatMachine;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.time.LocalDate;
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder

public class Machine {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String nom;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private EtatMachine etat; // Exemple : "Disponible", "En maintenance", "Hors service"

        @JsonProperty("derniere_maintenance")
        private LocalDate derniereMaintenance;
    }


