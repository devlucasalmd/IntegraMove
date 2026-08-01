import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatMenuModule } from '@angular/material/menu';
import {
  MatFormFieldControl,
  MatFormFieldModule,
} from '@angular/material/form-field';
import { MatDividerModule } from '@angular/material/divider';
import { FormGroup, FormsModule } from '@angular/forms';
import { ExercicioService } from '../../services/exercicios.service';
import { ExercicioResponseDTO } from '../../models/exercicio-response';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { ExercicioFormDialogComponent } from './exercicio-form-dialog/exercicio-form-dialog.component';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTooltipModule } from '@angular/material/tooltip';
import { SummaryCardData, SummaryCardsComponent } from '../../../../shared/components/summary-cards/summary-cards.component';
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
    MatDialogModule,
    MatDividerModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatTooltipModule,
    SummaryCardsComponent,
  ],
})
export class ExercicioListComponent implements OnInit {
  exercicios: ExercicioResponseDTO[] = [];
  exerciciosFiltrados: ExercicioResponseDTO[] = [];

  filtro = '';
  filtroGrupoMuscular = '';
  filtroIntensidade = '';
  filtroStatus = '';

  filtrosAbertos = false;

  carregando = false;
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
      next: (exercicios) => {
        this.exercicios = exercicios;
        this.exerciciosFiltrados = [...this.exercicios];
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar exercícios:', erro);
        this.carregando = false;
      },
    });
  }

  visualizarExercicio(exercicio: ExercicioResponseDTO): void {
    const dialogRef = this.dialog.open(ExercicioFormDialogComponent, {
      width: '760px',
      maxWidth: '95vw',
      data: {
        modo: 'visualizar',
        exercicio,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.carregarExercicios();
      }
    });
  }

  abrirNovo(): void {
    const dialogRef = this.dialog.open(ExercicioFormDialogComponent, {
      width: '600px',
      data: {
        modo: 'criar',
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.carregarExercicios();
      }
    });
  }

  abrirEditar(exercicio: ExercicioResponseDTO): void {
    const dialogRef = this.dialog.open(ExercicioFormDialogComponent, {
      width: '600px',
      data: {
        modo: 'editar',
        exercicio,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.carregarExercicios();
      }
    });
  }

  filtrarExercicios(): void {
    const termo = this.filtro.trim().toLowerCase();

    this.exerciciosFiltrados = this.exercicios.filter((exercicio) => {
      const nome = exercicio.nome?.toLowerCase() || '';
      const grupo = exercicio.grupoMuscular?.toLowerCase() || '';
      const intensidade = exercicio.intensidade?.toLowerCase() || '';
      const status = exercicio.ativo ? 'ativo' : 'inativo';

      const passaBusca =
        !termo ||
        nome.includes(termo) ||
        grupo.includes(termo) ||
        intensidade.includes(termo) ||
        status.includes(termo);

      const passaGrupo =
        !this.filtroGrupoMuscular ||
        exercicio.grupoMuscular === this.filtroGrupoMuscular;

      const passaIntensidade =
        !this.filtroIntensidade ||
        exercicio.intensidade === this.filtroIntensidade;

      const passaStatus = !this.filtroStatus || status === this.filtroStatus;

      return passaBusca && passaGrupo && passaIntensidade && passaStatus;
    });
  }

  limparFiltro(): void {
    this.filtro = '';
    this.filtrarExercicios();
  }

  limparTodosFiltros(): void {
    this.filtro = '';
    this.filtroGrupoMuscular = '';
    this.filtroIntensidade = '';
    this.filtroStatus = '';

    this.filtrarExercicios();
  }

  totalAtivos(): number {
    return this.exercicios.filter((exercicio) => exercicio.ativo).length;
  }

  totalInativos(): number {
    return this.exercicios.filter((exercicio) => !exercicio.ativo).length;
  }

  totalAltaIntensidade(): number {
    return this.exercicios.filter((exercicio) => {
      const intensidade = exercicio.intensidade
        ?.toLowerCase()
        .normalize('NFD')
        .replace(/[\u0300-\u036f]/g, '');

      return intensidade === 'alta';
    }).length;
  }

  classeIntensidade(intensidade: string): string {
    if (!intensidade) {
      return '';
    }

    return intensidade
      .toLowerCase()
      .normalize('NFD')
      .replace(/[\u0300-\u036f]/g, '');
  }

  get gruposMusculares(): string[] {
    return [
      ...new Set(
        this.exercicios
          .map((exercicio) => exercicio.grupoMuscular)
          .filter((grupo): grupo is string => !!grupo),
      ),
    ];
  }

  get intensidades(): string[] {
    return [
      ...new Set(
        this.exercicios
          .map((exercicio) => exercicio.intensidade)
          .filter((intensidade): intensidade is string => !!intensidade),
      ),
    ];
  }

  alternarFiltros(): void {
    this.filtrosAbertos = !this.filtrosAbertos;
  }

  get resumoExercicios(): SummaryCardData[] {
    return [
      { icon: 'inventory_2', label: 'Total', value: `${this.exercicios.length}`, variant: 'total' },
      { icon: 'check_circle', label: 'Ativos', value: `${this.totalAtivos()}`, variant: 'success' },
      { icon: 'block', label: 'Inativos', value: `${this.totalInativos()}`, variant: 'danger' },
    ];
  }
}
