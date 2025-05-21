package com.fab.fabricationback.controller;

import com.fab.fabricationback.model.Machine;
import com.fab.fabricationback.service.MachineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/machines")
@CrossOrigin
@RequiredArgsConstructor
public class MachineController {

    private final MachineService service;

    @GetMapping
    public List<Machine> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Machine getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Machine create(@Valid @RequestBody Machine machine) {
        return service.save(machine);
    }

    @PutMapping("{id}")
    public Machine update(@PathVariable Long id, @Valid @RequestBody Machine machine) {
        machine.setId(id);
        return service.save(machine);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
