import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrdreFabrication } from '../../models/ordre-fabrication';
import { OrdreFabricationService } from '../../services/ordre-fabrication.service';
import { FormsModule,ReactiveFormsModule ,} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Employe } from '../../models/employe';
import { Machine } from '../../models/machine';
import { Produit } from '../../models/produit';
import { EmployeService } from '../../services/employe.service';
import { ProduitService } from '../../services/produit.service';
import { MachineService } from '../../services/machine.service';


@Component({
  selector: 'app-employes',
  standalone: true,
  imports: [CommonModule, HttpClientModule, ReactiveFormsModule , FormsModule],
  templateUrl: './ordres.component.html',
  styleUrls: ['./ordres.component.css']
})
export class OrdresComponent implements OnInit {

  ordres: OrdreFabrication[] = [];
  nouveauOrdre: OrdreFabrication = this.initOrdre();
  ordreEnModification?: OrdreFabrication;
  modifierMode: boolean = false;

  employes: Employe[] = [];
  machines: Machine[] = [];
  produits: Produit[] = [];

  constructor(
    private ordreService: OrdreFabricationService,
    private employeService: EmployeService,
    private machineService: MachineService,
    private produitService: ProduitService
  ) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.ordreService.getAll().subscribe(data => this.ordres = data);
    this.employeService.getAll().subscribe(data => this.employes = data);
    this.machineService.getAll().subscribe(data => this.machines = data);
    this.produitService.getAll().subscribe(data => this.produits = data);
  }

  initOrdre(): OrdreFabrication {
    return {
      projet: '',
      quantite: 0,
      date: '',
      etat: 'PLANIFIÉ',
      employe: {} as Employe,
      machine: {} as Machine,
      produit: {} as Produit
    };
  }

  ajouterOrdre() {
    this.ordreService.add(this.nouveauOrdre).subscribe(() => {
      this.loadData();
      this.nouveauOrdre = this.initOrdre();
    });
  }

  supprimerOrdre(id: number) {
    this.ordreService.delete(id).subscribe(() => this.loadData());
  }

  modifierOrdre(ord: OrdreFabrication) {
    this.modifierMode = true;
    // Faire une copie pour ne pas modifier la liste directement
    this.nouveauOrdre = { ...ord };
    this.ordreEnModification = ord;
  }

  enregistrerModification() {
    if (!this.ordreEnModification) return;

    this.ordreService.update(this.nouveauOrdre).subscribe(() => {
      this.loadData();
      this.modifierMode = false;
      this.nouveauOrdre = this.initOrdre();
      this.ordreEnModification = undefined;
    });
  }

  annulerModification() {
    this.modifierMode = false;
    this.nouveauOrdre = this.initOrdre();
    this.ordreEnModification = undefined;
  }
}
