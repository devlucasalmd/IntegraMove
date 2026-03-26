import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatMenuModule } from '@angular/material/menu';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDividerModule } from '@angular/material/divider';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-exercicio-list',
  standalone: true,
  templateUrl: './exercicio-list.component.html',
  imports: [
    CommonModule,
    RouterModule,
    MatButtonModule,
    MatTableModule,
    MatCardModule,
    MatIconModule,
    MatMenuModule,
    MatDividerModule,
    FormsModule,
    MatFormFieldModule
  ],
})
export class ExercicioListComponent {


  filtro: string = '';

  colunas: string[] = ['descricao', 'grupo', 'intensidade'];

  constructor() {}

  // ngOnInit(): void {
  //   this.carregarAlunos();
  // }

  // carregarAlunos() {
  //   this.alunoService.listarAlunos().subscribe({
  //     next: (data) => (this.alunos = data),
  //     error: (err) => console.error(err),
  //   });
  // }

  //   filtrarAlunos() {
  //   const termo = this.filtro.toLowerCase();

  //   this.alunosFiltrados = this.alunos.filter((aluno) =>
  //     aluno.nome.toLowerCase().includes(termo)
  //   );
  // }

}
