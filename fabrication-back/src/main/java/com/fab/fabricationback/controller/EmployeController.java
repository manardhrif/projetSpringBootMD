package com.fab.fabricationback.controller;

import com.fab.fabricationback.model.Employe;
import com.fab.fabricationback.service.EmployeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@CrossOrigin
@RequiredArgsConstructor
public class EmployeController {

    private final EmployeService service;

    @GetMapping
    public List<Employe> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Employe getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Employe create(@Valid @RequestBody Employe employe) {
        return service.save(employe);
    }

    @PutMapping("{id}")
    public Employe update(@PathVariable Long id, @Valid @RequestBody Employe employe) {
        employe.setId(id);
        return service.save(employe);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
