import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Produit } from '../../models/produit';
import { ProduitService } from '../../services/produit.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-produits',
  standalone: true,
  imports: [CommonModule, HttpClientModule,FormsModule],
  templateUrl: './produits.component.html',
  styleUrls: ['./produits.component.css']
})
export class ProduitsComponent implements OnInit {
  produits: Produit[] = [];
  nouveauProduit: Produit = {  nom: '', type: '', stock: 0, fournisseur: '' };
  modifierMode = false;

  constructor(private produitService: ProduitService) {}

  ngOnInit(): void {
    this.chargerProduits();
  }

  chargerProduits() {
    this.produitService.getAll().subscribe(data => this.produits = data);
  }

  ajouterProduit() {
    this.produitService.create(this.nouveauProduit).subscribe(() => {
      this.chargerProduits();
      this.nouveauProduit = {  nom: '', type: '', stock: 0, fournisseur: '' };
    });
  }

  modifierProduit(produit: Produit) {
    this.nouveauProduit = { ...produit };
    this.modifierMode = true;
  }

  enregistrerModification() {
  if (this.nouveauProduit.id !== undefined) {
    this.produitService.update(this.nouveauProduit.id, this.nouveauProduit).subscribe(() => {
      this.chargerProduits();
      this.nouveauProduit = { nom: '', type: '', stock: 0, fournisseur: '' };
      this.modifierMode = false;
    });
  } else {
    console.error("Impossible de modifier : ID manquant");
  }
}


  supprimerProduit(id: number | undefined) {
  if (id === undefined) {
    console.error("ID manquant pour la suppression");
    return;
  }

  this.produitService.delete(id).subscribe(() => {
    this.chargerProduits();
  });
}

}
