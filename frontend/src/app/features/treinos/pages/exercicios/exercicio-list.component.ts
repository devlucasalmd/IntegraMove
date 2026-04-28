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
import { ExercicioResponseDTO } from '../../models/exercicio-response';
import { ExercicioService } from '../../services/exercicios.service';

@Component({
  selector: 'app-exercicio-list',
  standalone: true,
  templateUrl: './exercicio-list.component.html',
  styleUrl: './exercicio-list.component.css',
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
export class ExercicioListComponent implements OnInit {

  exercicios: ExercicioResponseDTO[] = [];

  filtro: string = '';

  colunas: string[] = ['nome', 'grupoMuscular', 'intensidade'];

  constructor(private exercicioService: ExercicioService) {}

  ngOnInit(): void {
    this.carregarExercicios();
  }

  carregarExercicios() {
    this.exercicioService.listarExercicios().subscribe({
      next: (data) => (this.exercicios = data),
      error: (err) => console.error(err),
    });
  }

  // editar(exercicio: ExercicioResponseDTO) {
  //   this.router.navigate(['/exercicios/editar', exercicio.id]);
  // }

  // deletar(id: string) {
  //   // implementar quando tiver DELETE
  //   this.snackBar.open('Excluir ainda não implementado', 'OK', {
  //     duration: 2000
  //   });
}

  //   filtrarAlunos() {
  //   const termo = this.filtro.toLowerCase();

  //   this.alunosFiltrados = this.alunos.filter((aluno) =>
  //     aluno.nome.toLowerCase().includes(termo)
  //   );
  // }
