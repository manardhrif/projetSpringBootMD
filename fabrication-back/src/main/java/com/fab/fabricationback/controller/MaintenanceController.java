package com.fab.fabricationback.controller;

import com.fab.fabricationback.model.Maintenance;
import com.fab.fabricationback.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
@RequiredArgsConstructor
@CrossOrigin
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @PostMapping("/planifier")
    public Maintenance planifier(@RequestParam Long machineId, @RequestParam String description) {
        return maintenanceService.planifierMaintenance(machineId, description);
    }

    @PutMapping("/terminer/{id}")
    public Maintenance terminer(@PathVariable Long id) {
        return maintenanceService.terminerMaintenance(id);
    }

    @GetMapping("/machine/{id}")
    public List<Maintenance> getParMachine(@PathVariable Long id) {
        return maintenanceService.getMaintenancesParMachine(id);
    }

    @GetMapping("/en-maintenance/{machineId}")
    public boolean estEnMaintenance(@PathVariable Long machineId) {
        return maintenanceService.isMachineEnMaintenance(machineId);
    }
}
