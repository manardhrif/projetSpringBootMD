import { Employe } from './employe';
import { Machine } from './machine';
import { Produit } from './produit';

export interface OrdreFabrication {
  id?: number;
  projet: string;
  quantite: number;
  date: string;
  etat: 'PLANIFIÉ' | 'EN_COURS' | 'TERMINÉ';
  employe: Employe;
  machine: Machine;
  produit: Produit;
}
