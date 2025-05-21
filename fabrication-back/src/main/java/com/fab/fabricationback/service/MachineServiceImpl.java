package com.fab.fabricationback.service;

import com.fab.fabricationback.model.Machine;
import com.fab.fabricationback.repository.MachineRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;

    @Override
    public List<Machine> findAll() {
        return machineRepository.findAll();
    }

    @Override
    public Machine findById(Long id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Machine " + id + " introuvable"));
    }

    @Override
    public Machine save(Machine machine) {
        log.info("Sauvegarde de la machine {}", machine.getNom());
        return machineRepository.save(machine);
    }

    @Override
    public void delete(Long id) {
        log.warn("Suppression de la machine {}", id);
        machineRepository.deleteById(id);
    }



}
