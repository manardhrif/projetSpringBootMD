package com.fab.fabricationback.service;

import com.fab.fabricationback.model.Employe;
import com.fab.fabricationback.model.Machine;
import com.fab.fabricationback.repository.EmployeRepository;
import com.fab.fabricationback.repository.MachineRepository;
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
    private final MachineRepository machineRepository;

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

        if (employe.getMachineAssignee() != null && employe.getMachineAssignee().getId() != null) {
            Machine machine = machineRepository.findById(employe.getMachineAssignee().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Machine " + employe.getMachineAssignee().getId() + " introuvable"));
            employe.setMachineAssignee(machine);
        } else {
            employe.setMachineAssignee(null); // optionnel si tu veux "détacher"
        }

        return employeRepository.save(employe);
    }

    @Override
    public void delete(Long id) {
        log.warn("Suppression de l'employé {}", id);
        employeRepository.deleteById(id);
    }
}
