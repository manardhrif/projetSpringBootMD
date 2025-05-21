package com.fab.fabricationback.repository;
import com.fab.fabricationback.model.Employe;
import com.fab.fabricationback.model.Machine;
import com.fab.fabricationback.model.OrdreFabrication;
import com.fab.fabricationback.model.enums.EtatOrdre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrdreFabricationRepository extends JpaRepository <OrdreFabrication, Long>  {

    boolean existsByMachineAndEtatIn(Machine machine, List<EtatOrdre> etats);
    boolean existsByEmployeAndEtatIn(Employe employe, List<EtatOrdre> etats);

    //Obtenir tous les ordres selon leur état (EN_ATTENTE, EN_COURS, TERMINE, etc.)
    List<OrdreFabrication> findByEtat(EtatOrdre etat);




}
