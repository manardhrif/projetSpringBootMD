import { Routes } from '@angular/router';
import { MachinesComponent } from './components/machines/machines.component';
import { ProduitsComponent } from './components/produits/produits.component';
import { EmployesComponent } from './components/employes/employes.component';
import { OrdresComponent } from './components/ordres/ordres.component';


export const appRoutes: Routes = [
  { path: '', redirectTo: 'machines', pathMatch: 'full' },
  { path: 'machines', component: MachinesComponent },
  { path: 'produits', component: ProduitsComponent },
  { path: 'employes', component: EmployesComponent },
  { path: 'ordres', component: OrdresComponent},



];
