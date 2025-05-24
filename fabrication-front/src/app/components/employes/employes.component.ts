import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { EmployeService } from '../../services/employe.service';
import { MachineService } from '../../services/machine.service';
import { Employe } from '../../models/employe';
import { Machine } from '../../models/machine';

@Component({
  selector: 'app-employes',
  standalone: true,
  imports: [CommonModule, HttpClientModule, ReactiveFormsModule],
  templateUrl: './employes.component.html',
  styleUrls: ['./employes.component.css']
})
export class EmployesComponent implements OnInit {
  employes: Employe[] = [];
  machines: Machine[] = [];
  form: FormGroup;
  editingEmployeId: number | null = null; // Pour savoir si on modifie

  constructor(
    private fb: FormBuilder,
    private employeService: EmployeService,
    private machineService: MachineService
  ) {
    this.form = this.fb.group({
      nom: ['', Validators.required],
      poste: ['', Validators.required],
      machineAssignee: [null]
    });
  }

  ngOnInit(): void {
    this.loadEmployes();
    this.loadMachines();
  }

  loadEmployes() {
    this.employeService.getAll().subscribe(data => {
      this.employes = data;
    });
  }

  loadMachines() {
    this.machineService.getAll().subscribe(data => {
      this.machines = data;
    });
  }

  onSubmit() {
    const formValue = this.form.value;

    // On prépare l'objet à envoyer
    let employeToSave = {
      nom: formValue.nom,
      poste: formValue.poste,
      machineAssignee: formValue.machineAssignee ? { id: formValue.machineAssignee } : null
    };

    if (this.editingEmployeId) {
      // Modification
      this.employeService.update(this.editingEmployeId, employeToSave).subscribe({
        next: () => {
          alert('Employé modifié avec succès');
          this.form.reset();
          this.editingEmployeId = null;
          this.loadEmployes();
        },
        error: (err) => {
          console.error(err);
          alert('Erreur lors de la modification');
        }
      });
    } else {
      // Ajout
      this.employeService.create(employeToSave).subscribe({
        next: () => {
          alert('Employé ajouté avec succès');
          this.form.reset();
          this.loadEmployes();
        },
        error: (err) => {
          console.error(err);
          alert('Erreur lors de l\'ajout');
        }
      });

    }
  }

  deleteEmploye(id: number) {
    if (confirm('Confirmer la suppression ?')) {
      this.employeService.delete(id).subscribe({
        next: () => {
          alert('Employé supprimé');
          this.loadEmployes();
        },
        error: (err) => {
          console.error(err);
          alert('Erreur lors de la suppression');
        }
      });
    }
  }

  editEmploye(e: Employe) {
    this.editingEmployeId = e.id!;
    this.form.patchValue({
      nom: e.nom,
      poste: e.poste,
      machineAssignee: e.Machine_Assignee ? e.Machine_Assignee.id : null
    });
  }

  cancelEdit() {
    this.editingEmployeId = null;
    this.form.reset();
  }
}

