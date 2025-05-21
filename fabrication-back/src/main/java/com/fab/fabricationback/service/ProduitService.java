package com.fab.fabricationback.service;


import com.fab.fabricationback.model.Produit;
import com.fab.fabricationback.repository.ProduitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service @RequiredArgsConstructor
@Slf4j
public class ProduitService {

    private final ProduitRepository repo;

    public List<Produit> findAll() {
        return repo.findAll();
    }
    public Produit findById(Long id) {
        return repo.findById(id)
             .orElseThrow(() -> new EntityNotFoundException("Produit" +id+ "introuvable")); }
    public Produit save(Produit p) {
        log.info("Sauvegarde du produit {}", p.getNom());
    return repo.save(p);
    }
    public void delete(Long id) {
        log.warn("Supression du produit {}",id);
        repo.deleteById(id);
    }

    public Page<Produit> search(String q, Pageable pageable) {
        if (q == null || q.isBlank()) {
            return repo.findAll(pageable);      //  vient de JpaRepository
        }
        return repo.findByNomContainingIgnoreCase(q, pageable);
    }


}
