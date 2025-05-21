package com.fab.fabricationback.service;
import com.fab.fabricationback.model.Machine;
import java.util.List;

public interface MachineService {

        List<Machine> findAll();
        Machine findById(Long id);
        Machine save(Machine machine);
        void delete(Long id);
    }


