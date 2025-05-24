import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Machine } from '../../models/machine';
import { MachineService } from '../../services/machine.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-machines',
  standalone: true,
  imports: [CommonModule, HttpClientModule, FormsModule],
  templateUrl: './machines.component.html',
  styleUrls: ['./machines.component.css']
})
export class MachinesComponent implements OnInit {
  machines: Machine[] = [];
  nouvelleMachine: Machine = { nom: '', etat: '', derniere_maintenance: '' };
  modifierMode = false;

  constructor(private machineService: MachineService) {}

  ngOnInit(): void {
    this.chargerMachines();
  }

  chargerMachines() {
    this.machineService.getAll().subscribe(data => this.machines = data);
  }

  ajouterMachine() {
    this.machineService.create(this.nouvelleMachine).subscribe(() => {
      this.chargerMachines();
      this.nouvelleMachine = { nom: '', etat: '', derniere_maintenance: '' };
    });
  }

  modifierMachine(machine: Machine) {
    this.nouvelleMachine = { ...machine };
    this.modifierMode = true;
  }

  enregistrerModification() {
    if (this.nouvelleMachine.id === undefined) return;

    this.machineService.update(this.nouvelleMachine.id, this.nouvelleMachine).subscribe(() => {
      this.chargerMachines();
      this.nouvelleMachine = { nom: '', etat: '', derniere_maintenance: '' };
      this.modifierMode = false;
    });
  }

  supprimerMachine(id: number | undefined) {
    if (id === undefined) return;

    this.machineService.delete(id).subscribe(() => {
      this.chargerMachines();
    });
  }
}
