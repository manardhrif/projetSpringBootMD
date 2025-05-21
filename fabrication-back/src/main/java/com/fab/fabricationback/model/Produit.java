package com.fab.fabricationback.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(nullable = false)
    private String nom;

    private String type;

    @PositiveOrZero(message = "Le stock ne peut pas être négatif")
    private int stock;

    private String fournisseur;
}

