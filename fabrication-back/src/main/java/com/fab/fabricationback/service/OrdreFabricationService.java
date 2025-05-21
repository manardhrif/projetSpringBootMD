package com.fab.fabricationback.service;

import com.fab.fabricationback.model.OrdreFabrication;
import com.fab.fabricationback.model.enums.EtatOrdre;

import java.util.List;

public interface OrdreFabricationService {
    List<OrdreFabrication> findAll();
    OrdreFabrication findById(Long id);
    OrdreFabrication save(OrdreFabrication ordre);
    void delete(Long id);

    OrdreFabrication affecterEmployeEtMachine(Long ordreId, Long employeId, Long machineId);
    //Obtenir tous les ordres selon leur état (EN_ATTENTE, EN_COURS, TERMINE, etc.)
    List<OrdreFabrication> findByEtat(EtatOrdre etat);

    OrdreFabrication changerEtat(Long ordreId, EtatOrdre nouvelEtat);


}
