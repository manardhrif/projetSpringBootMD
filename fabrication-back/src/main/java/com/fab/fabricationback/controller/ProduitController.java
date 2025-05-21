package com.fab.fabricationback.controller;


import com.fab.fabricationback.model.Produit;
import com.fab.fabricationback.service.ProduitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;



@RestController
@RequestMapping("/api/produits")
@CrossOrigin // autorise l’appel depuis mon futur front Angular
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService service;

    @GetMapping
    public List<Produit> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Produit getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Produit create(@Valid  @RequestBody Produit p) {
        return service.save(p);
    }

    @PutMapping("{id}")
    public Produit update(@PathVariable Long id, @Valid @RequestBody Produit p) {
        p.setId(id);
        return service.save(p);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @GetMapping("/search")
    public Page<Produit> search(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return service.search(q, PageRequest.of(page,size, Sort.by("nom")));
    }
}
