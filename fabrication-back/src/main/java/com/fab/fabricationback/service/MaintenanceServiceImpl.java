package com.fab.fabricationback.service;

import com.fab.fabricationback.model.Machine;
import com.fab.fabricationback.model.Maintenance;
import com.fab.fabricationback.repository.MachineRepository;
import com.fab.fabricationback.repository.MaintenanceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepo;
    private final MachineRepository machineRepo;

    @Override
    public Maintenance planifierMaintenance(Long machineId, String description) {
        Machine machine = machineRepo.findById(machineId)
                .orElseThrow(() -> new EntityNotFoundException("Machine introuvable"));

        machine.setEtat(com.fab.fabricationback.model.enums.EtatMachine.EN_MAINTENANCE);
        machineRepo.save(machine);

        return maintenanceRepo.save(Maintenance.builder()
                .machine(machine)
                .description(description)
                .dateDebut(LocalDateTime.now())
                .build());
    }

    @Override
    public Maintenance terminerMaintenance(Long maintenanceId) {
        Maintenance maintenance = maintenanceRepo.findById(maintenanceId)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance introuvable"));

        maintenance.setDateFin(LocalDateTime.now());
        maintenanceRepo.save(maintenance);

        // Remettre la machine en état DISPONIBLE
        Machine machine = maintenance.getMachine();
        machine.setEtat(com.fab.fabricationback.model.enums.EtatMachine.DISPONIBLE);
        machineRepo.save(machine);

        return maintenance;
    }

    @Override
    public List<Maintenance> getMaintenancesParMachine(Long machineId) {
        Machine machine = machineRepo.findById(machineId)
                .orElseThrow(() -> new EntityNotFoundException("Machine introuvable"));
        return maintenanceRepo.findByMachine(machine);
    }

    @Override
    public boolean isMachineEnMaintenance(Long machineId) {
        Machine machine = machineRepo.findById(machineId)
                .orElseThrow(() -> new EntityNotFoundException("Machine introuvable"));
        List<Maintenance> maintenances = maintenanceRepo.findByMachineAndDateFinIsNull(machine);
        return !maintenances.isEmpty();
    }
}
