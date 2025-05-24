import { Machine } from './machine';

export interface Employe {
  id?: number;
  nom: string;
  poste: string;
  Machine_Assignee?: Machine ;
}
