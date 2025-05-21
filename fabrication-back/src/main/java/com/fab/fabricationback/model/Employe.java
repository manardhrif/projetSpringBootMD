package com.fab.fabricationback.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Employe {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String nom;

        private String poste;

        @ManyToOne
        private Machine machineAssignee;
    }



