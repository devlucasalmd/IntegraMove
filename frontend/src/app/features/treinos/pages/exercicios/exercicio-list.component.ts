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
import { FormGroup, FormsModule } from '@angular/forms';
import { ExercicioService } from '../../services/exercicios.service';
import { ExercicioResponseDTO } from '../../models/exercicio-response';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { ExercicioFormDialogComponent } from './exercicio-form-dialog/exercicio-form-dialog.component';
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
    MatDialogModule,
    MatDividerModule,
    FormsModule,
    MatFormFieldModule,
  ],
})
export class ExercicioListComponent implements OnInit {
  exercicios: ExercicioResponseDTO[] = [];
  exerciciosFiltrados: ExercicioResponseDTO[] = [];

  filtro: string = '';

  modoVisualizacao = false;
  
  form!: FormGroup;

  colunas: string[] = [
    'nome',
    'grupoMuscular',
    'intensidade',
    'status',
    'acoes',
  ];

  constructor(
    private exercicioService: ExercicioService,
    private dialog: MatDialog,
  ) {}

  ngOnInit(): void {
    this.carregarExercicios();
  }

  carregarExercicios() {
    this.exercicioService.listarExercicios().subscribe({
      next: (data) => (this.exercicios = data),
      error: (err) => console.error(err),
    });
  }

  filtrarExercicios() {
    const termo = this.filtro.toLowerCase();

    this.exerciciosFiltrados = this.exercicios.filter((e) =>
      e.nome.toLowerCase().includes(termo),
    );
  }

  abrirNovo() {
    console.log('clicou');
    const dialogRef = this.dialog.open(ExercicioFormDialogComponent, {
      width: '900px',
      maxWidth: '95vw',
      panelClass: 'custom-dialog',
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.carregarExercicios();
      }
    });
  }

  abrirEditar(exercicio: ExercicioResponseDTO) {
    const dialogRef = this.dialog.open(ExercicioFormDialogComponent, {
      width: '500px',
      data: exercicio,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.carregarExercicios();
      }
    });
  }

  abrirVisualizar(exercicio: ExercicioResponseDTO): void {
    this.dialog.open(ExercicioFormDialogComponent, {
      width: '500px',

      data: {
        ...exercicio,
        visualizacao: true,
      },
    });
  }
}
