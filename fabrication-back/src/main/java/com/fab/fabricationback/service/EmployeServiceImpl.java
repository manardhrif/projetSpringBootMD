package com.fab.fabricationback.service;

import com.fab.fabricationback.model.Employe;
import com.fab.fabricationback.repository.EmployeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepository employeRepository;

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public Employe findById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employé " + id + " introuvable"));
    }

    @Override
    public Employe save(Employe employe) {
        log.info("Sauvegarde de l'employé {}", employe.getNom());
        return employeRepository.save(employe);
    }

    @Override
    public void delete(Long id) {
        log.warn("Suppression de l'employé {}", id);
        employeRepository.deleteById(id);
    }
}
