package com.fab.fabricationback.controller;

import com.fab.fabricationback.model.JournalEtat;
import com.fab.fabricationback.model.OrdreFabrication;
import com.fab.fabricationback.model.enums.EtatOrdre;
import com.fab.fabricationback.service.OrdreFabricationService;
import com.fab.fabricationback.repository.JournalEtatRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordres")
@CrossOrigin
@RequiredArgsConstructor
public class OrdreFabricationController {

    private final OrdreFabricationService service;
    private final JournalEtatRepository journalEtatRepository;


    @GetMapping
    public List<OrdreFabrication> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public OrdreFabrication getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public OrdreFabrication create(@Valid @RequestBody OrdreFabrication ordre) {
        return service.save(ordre);
    }

    @PutMapping("{id}")
    public OrdreFabrication update(@PathVariable Long id, @Valid @RequestBody OrdreFabrication ordre) {
        ordre.setId(id);
        return service.save(ordre);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    @PutMapping("/{id}/affecter")
    public OrdreFabrication affecter(@PathVariable Long id,
                                     @RequestParam Long employeId,
                                     @RequestParam Long machineId) {
        return service.affecterEmployeEtMachine(id, employeId, machineId);
    }



    //Obtenir tous les ordres selon leur état (EN_ATTENTE, EN_COURS, TERMINE, etc.)
    @GetMapping("/etat")
    public List<OrdreFabrication> getByEtat(@RequestParam EtatOrdre etat) {
        return service.findByEtat(etat);
    }


    @GetMapping("/{id}/journal")
    public List<JournalEtat> getJournal(@PathVariable Long id) {
        OrdreFabrication ordre = service.findById(id);
        return journalEtatRepository.findAll().stream()
                .filter(j -> j.getOrdre().getId().equals(id))
                .toList();
    }


    @PutMapping("/{id}/changer-etat")
    public OrdreFabrication changerEtat(@PathVariable Long id,
                                        @RequestParam EtatOrdre etat) {
        return service.changerEtat(id, etat);
    }

}
