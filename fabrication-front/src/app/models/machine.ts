export interface Machine {
  id?: number; // ID optionnel pour les nouveaux objets
  nom: string;
  etat: string;
  derniere_maintenance: string; // sous forme 'YYYY-MM-DD'
}
