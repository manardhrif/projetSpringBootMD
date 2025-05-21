package com.fab.fabricationback.service;

import com.fab.fabricationback.model.Employe;
import java.util.List;

public interface EmployeService {
    List<Employe> findAll();
    Employe findById(Long id);
    Employe save(Employe employe);
    void delete(Long id);
}
