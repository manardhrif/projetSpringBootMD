package com.fab.fabricationback.repository;

import com.fab.fabricationback.model.Maintenance;
import com.fab.fabricationback.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByMachine(Machine machine);
    List<Maintenance> findByMachineAndDateFinIsNull(Machine machine
    ); // maintenances en cours
}
