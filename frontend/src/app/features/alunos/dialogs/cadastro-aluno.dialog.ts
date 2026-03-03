import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialogModule } from '@angular/material/dialog';
import { AlunoFormComponent } from '../pages/aluno-form.component';

@Component({
  selector: 'app-cadastro-aluno-dialog',
  standalone: true,
  imports: [
    CommonModule,
    MatDialogModule,
    AlunoFormComponent
  ],
  template: `
    <h2 mat-dialog-title>Cadastrar Aluno</h2>
    <mat-dialog-content>
      <app-aluno-form></app-aluno-form>
    </mat-dialog-content>
  `
})
export class CadastroAlunoDialog {}
