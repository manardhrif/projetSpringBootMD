package com.fab.fabricationback.model;
import com.fab.fabricationback.model.enums.EtatOrdre;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JournalEtat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrdreFabrication ordre;

    @Enumerated(EnumType.STRING)
    private EtatOrdre ancienEtat;

    @Enumerated(EnumType.STRING)
    private EtatOrdre nouvelEtat;

    private LocalDateTime dateChangement;
}
