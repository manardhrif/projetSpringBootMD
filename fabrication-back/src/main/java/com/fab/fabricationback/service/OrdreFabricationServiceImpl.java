package com.fab.fabricationback.service;

import com.fab.fabricationback.model.Employe;
import com.fab.fabricationback.model.JournalEtat;
import com.fab.fabricationback.model.Machine;
import com.fab.fabricationback.model.OrdreFabrication;


import com.fab.fabricationback.model.enums.EtatMachine;
import com.fab.fabricationback.model.enums.EtatOrdre;
import com.fab.fabricationback.repository.EmployeRepository;
import com.fab.fabricationback.repository.JournalEtatRepository;
import com.fab.fabricationback.repository.MachineRepository;
import com.fab.fabricationback.repository.OrdreFabricationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdreFabricationServiceImpl implements OrdreFabricationService {

    private final OrdreFabricationRepository ordreRepository;
    private final MachineRepository machineRepo;
    private final EmployeRepository employeRepo;

    private final JournalEtatRepository journalEtatRepository;


    @Override
    public List<OrdreFabrication> findAll() {
        return ordreRepository.findAll();
    }

    @Override
    public OrdreFabrication findById(Long id) {
        return ordreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordre de fabrication " + id + " introuvable"));
    }

    @Override
    public OrdreFabrication save(OrdreFabrication ordre) {
        log.info("Création d'un ordre de fabrication pour le projet {}", ordre.getProjet());
        return ordreRepository.save(ordre);
    }

    @Override
    public void delete(Long id) {
        log.warn("Suppression de l'ordre de fabrication {}", id);
        ordreRepository.deleteById(id);
    }
    //Affecter un employé et une machine à un ordre de fabrication
    //Mais seulement si la machine est dispo et l’employé aussi
    @Override
    public OrdreFabrication affecterEmployeEtMachine(Long ordreId, Long employeId, Long machineId) {
        OrdreFabrication ordre = ordreRepository.findById(ordreId)
                .orElseThrow(() -> new EntityNotFoundException("Ordre non trouvé"));

        Machine machine = machineRepo.findById(machineId)
                .orElseThrow(() -> new EntityNotFoundException("Machine non trouvée"));

        Employe employe = employeRepo.findById(employeId)
                .orElseThrow(() -> new EntityNotFoundException("Employé non trouvé"));

        if (machine.getEtat() == EtatMachine.EN_MAINTENANCE) {
            throw new IllegalStateException("Machine en maintenance !");
        }

        boolean machineOccupee = ordreRepository.existsByMachineAndEtatIn(machine, List.of(EtatOrdre.EN_ATTENTE, EtatOrdre.EN_COURS));
        if (machineOccupee) {
            throw new IllegalStateException("Machine déjà affectée à un autre ordre actif !");
        }

        boolean employeOccupe = ordreRepository.existsByEmployeAndEtatIn(employe, List.of(EtatOrdre.EN_ATTENTE, EtatOrdre.EN_COURS));
        if (employeOccupe) {
            throw new IllegalStateException("Employé déjà affecté à un autre ordre actif !");
        }

        EtatOrdre ancienEtat = ordre.getEtat(); // d'abord on récupère l'ancien état

        ordre.setMachine(machine);
        ordre.setEmploye(employe);
        ordre.setEtat(EtatOrdre.EN_COURS); // on démarre l’ordre
        ordreRepository.save(ordre); // on sauvegarde l'ordre

        // Journaliser le changement d’état
        journalEtatRepository.save(
                JournalEtat.builder()
                        .ordre(ordre)
                        .ancienEtat(ancienEtat) // Si déjà défini
                        .nouvelEtat(EtatOrdre.EN_COURS)
                        .dateChangement(LocalDateTime.now())
                        .build()
        );

        return ordre;

    }
    //Obtenir tous les ordres selon leur état (EN_ATTENTE, EN_COURS, TERMINE, etc.)
    @Override
    public List<OrdreFabrication> findByEtat(EtatOrdre etat) {
        return ordreRepository.findByEtat(etat);
    }



    //creer un endpoint=terminé

    @Override
    public OrdreFabrication changerEtat(Long ordreId, EtatOrdre nouvelEtat) {
        OrdreFabrication ordre = ordreRepository.findById(ordreId)
                .orElseThrow(() -> new EntityNotFoundException("Ordre non trouvé"));

        EtatOrdre ancienEtat = ordre.getEtat();

        if (ancienEtat == nouvelEtat) {
            throw new IllegalStateException("L’état est déjà " + nouvelEtat);
        }

        ordre.setEtat(nouvelEtat);
        ordreRepository.save(ordre);

        // Journaliser le changement
        journalEtatRepository.save(JournalEtat.builder()
                .ordre(ordre)
                .ancienEtat(ancienEtat)
                .nouvelEtat(nouvelEtat)
                .dateChangement(LocalDateTime.now())
                .build());

        return ordre;
    }


}
