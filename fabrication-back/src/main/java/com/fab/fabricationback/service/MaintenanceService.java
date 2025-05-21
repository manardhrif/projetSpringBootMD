package com.fab.fabricationback.service;

import com.fab.fabricationback.model.Maintenance;
import com.fab.fabricationback.model.Machine;

import java.util.List;

public interface MaintenanceService {
    Maintenance planifierMaintenance(Long machineId, String description);
    Maintenance terminerMaintenance(Long maintenanceId);
    List<Maintenance> getMaintenancesParMachine(Long machineId);
    boolean isMachineEnMaintenance(Long machineId);
}
