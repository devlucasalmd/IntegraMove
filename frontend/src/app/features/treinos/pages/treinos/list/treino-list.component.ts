import { TreinoService } from './../../../services/treino.service';
import { Component, OnInit } from '@angular/core';
import { forkJoin, of } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';

import { RouterModule } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';

import { TreinoDialogComponent } from '../treino-dialog/treino-dialog.component';
import { TreinoResponseDTO } from '../../../models/treino-response.model';
import { ExercicioDialogComponent } from '../treino-detail/exercicio-dialog/exercicio-dialog.component';
import { TreinoItemService } from '../../../services/treino-item.service';
import { TreinoDetailComponent } from '../treino-detail/treino-detail.component';
import { SummaryCardData, SummaryCardsComponent } from '../../../../../shared/components/summary-cards/summary-cards.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material/menu';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-treino-list',
  standalone: true,
  templateUrl: './treino-list.component.html',
  styleUrls: ['./treino-list.component.css'],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    MatMenuModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatTooltipModule,
    SummaryCardsComponent,
  ],
})
export class TreinoListComponent implements OnInit {
  treinos: TreinoResponseDTO[] = [];
  treinosFiltrados: TreinoResponseDTO[] = [];

  filtro = '';
  filtroGrupoMuscular = '';
  filtroNivel = '';
  filtroFuncionalidade = '';

  filtrosAbertos = false;
  carregando = false;

  colunas: string[] = [
    'treino',
    'grupoMuscular',
    'nivel',
    'funcionalidade',
    'exercicios',
    'acoes',
  ];

  constructor(
    private dialog: MatDialog,
    private treinoService: TreinoService,
    private treinoItemService: TreinoItemService,
  ) {}

  ngOnInit(): void {
    this.carregarTreinos();
  }

  carregarTreinos(): void {
    this.carregando = true;

    this.treinoService
      .listarTreinos()
      .pipe(
        switchMap((treinos) => {
          if (treinos.length === 0) {
            return of([]);
          }

          const requisicoes = treinos.map((treino) =>
            this.treinoItemService.listarItensDoTreino(treino.id).pipe(
              map((itens) => ({
                ...treino,
                exercicios: itens ?? [],
              })),
              catchError(() =>
                of({
                  ...treino,
                  exercicios: [],
                }),
              ),
            ),
          );

          return forkJoin(requisicoes);
        }),
      )
      .subscribe({
        next: (treinosComExercicios) => {
          this.treinos = treinosComExercicios;
          this.treinosFiltrados = [...this.treinos];
          this.carregando = false;
        },
        error: (erro) => {
          console.error('Erro ao carregar treinos:', erro);
          this.carregando = false;
        },
      });
  }

  filtrarTreinos(): void {
    const termo = this.filtro.trim().toLowerCase();

    this.treinosFiltrados = this.treinos.filter((treino) => {
      const passaBusca =
        !termo ||
        treino.nome?.toLowerCase().includes(termo) ||
        treino.responsavel?.toLowerCase().includes(termo) ||
        treino.grupoMuscular?.toLowerCase().includes(termo) ||
        treino.nivel?.toLowerCase().includes(termo);

      const passaGrupo = !this.filtroGrupoMuscular || treino.grupoMuscular === this.filtroGrupoMuscular;
      const passaNivel = !this.filtroNivel || treino.nivel === this.filtroNivel;
      const passaFuncionalidade =
        !this.filtroFuncionalidade || treino.funcionalidade === this.filtroFuncionalidade;

      return passaBusca && passaGrupo && passaNivel && passaFuncionalidade;
    });
  }

  limparFiltro(): void {
    this.filtro = '';
    this.filtrarTreinos();
  }

  alternarFiltros(): void {
    this.filtrosAbertos = !this.filtrosAbertos;
  }

  limparTodosFiltros(): void {
    this.filtro = '';
    this.filtroGrupoMuscular = '';
    this.filtroNivel = '';
    this.filtroFuncionalidade = '';
    this.filtrarTreinos();
  }

  get gruposMusculares(): string[] {
    return [
      ...new Set(
        this.treinos
          .map((treino) => treino.grupoMuscular)
          .filter((grupo): grupo is string => !!grupo),
      ),
    ];
  }

  get niveis(): string[] {
    return [
      ...new Set(
        this.treinos
          .map((treino) => treino.nivel)
          .filter((nivel): nivel is string => !!nivel),
      ),
    ];
  }

  get funcionalidades(): string[] {
    return [
      ...new Set(
        this.treinos
          .map((treino) => treino.funcionalidade)
          .filter((funcionalidade): funcionalidade is string => !!funcionalidade),
      ),
    ];
  }

  get resumoTreinos(): SummaryCardData[] {
    return [
      { icon: 'fitness_center', label: 'Total de treinos', value: `${this.treinos.length}`, variant: 'total' },
    ];
  }

  abrirNovoTreino(): void {
    const dialogRef = this.dialog.open(TreinoDialogComponent, {
      width: '720px',
      maxWidth: '95vw',
      disableClose: true,
      autoFocus: false,
      panelClass: 'dialog-profissional',
    });

    dialogRef
      .afterClosed()
      .subscribe((treinoSalvo: TreinoResponseDTO | undefined) => {
        if (!treinoSalvo) {
          return;
        }

        this.carregarTreinos();

        this.abrirDialogAdicionarExercicios(treinoSalvo);
      });
  }

  private abrirDialogAdicionarExercicios(treino: TreinoResponseDTO): void {
    const dialogRef = this.dialog.open(ExercicioDialogComponent, {
      width: '900px',
      maxWidth: '96vw',
      maxHeight: '90vh',
      disableClose: true,
      autoFocus: false,
      panelClass: 'dialog-profissional',
      data: {
        treinoId: treino.id,
        treinoNome: treino.nome,
        grupoMuscular: treino.grupoMuscular,
      },
    });

    dialogRef.afterClosed().subscribe((atualizou: boolean) => {
      if (atualizou) {
        this.carregarTreinos();
      }
    });
  }

  abrirDetalhesTreino(treino: TreinoResponseDTO): void {
    this.dialog.open(TreinoDetailComponent, {
      width: '850px',
      maxWidth: '96vw',
      maxHeight: '90vh',
      autoFocus: false,
      panelClass: 'dialog-profissional',
      data: {
        treinoId: treino.id,
      },
    });
  }

  editarTreino(id: string): void {
    console.log('Editar', id);
  }

  excluirTreino(id: string): void {
    console.log('Excluir', id);
  }
}
