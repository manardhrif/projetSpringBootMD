package com.fab.fabricationback.repository;

import com.fab.fabricationback.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


public interface ProduitRepository
        extends JpaRepository<Produit,Long>  {

    //recher simple par nom contenant
    Page<Produit> findByNomContainingIgnoreCase(String nom, Pageable pageable);
}
