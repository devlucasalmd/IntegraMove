import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { CadastroAlunoDialog } from '../../alunos/dialogs/cadastro-aluno.dialog';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    CommonModule,
    MatDialogModule,
    MatButtonModule
  ],
  templateUrl: './home-page.component.html'
})
export class HomePageComponent {

  constructor(private dialog: MatDialog) {}

  abrirCadastro() {
    this.dialog.open(CadastroAlunoDialog, {
      width: '600px'
    });
  }
}
