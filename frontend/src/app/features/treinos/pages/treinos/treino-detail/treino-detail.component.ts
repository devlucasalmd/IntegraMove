import { Component, Inject, OnInit } from '@angular/core';

import { ActivatedRoute } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import { ExercicioDialogComponent } from './exercicio-dialog/exercicio-dialog.component';
import { TreinoResponseDTO } from '../../../models/treino-response.model';
import { TreinoItemResponseDTO } from '../../../models/treino-item-response';
import { TreinoService } from '../../../services/treino.service';
import { TreinoItemService } from '../../../services/treino-item.service';

interface TreinoItem {
  id: string;
  nome: string;
  series: number;
  repeticoes: string;
  carga: number;
  descanso: number;
  observacao?: string;
}

@Component({
  selector: 'app-treino-detail',
  standalone: true,
  templateUrl: './treino-detail.component.html',
  styleUrls: ['./treino-detail.component.css'],
  imports: [
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatDialogModule
],
})
export class TreinoDetailComponent implements OnInit {
  treinoId!: string;
  treino: TreinoResponseDTO | null = null;
  exercicios: TreinoItemResponseDTO[] = [];

  carregandoTreino = false;
  carregandoExercicios = false;

  colunas: string[] = [
    'nome',
    'series',
    'reps',
    'carga',
    'descanso',
    'observacao',
  ];

  constructor(
    private treinoService: TreinoService,
    private treinoItemService: TreinoItemService,
    private dialogRef: MatDialogRef<TreinoDetailComponent>,

    @Inject(MAT_DIALOG_DATA)
    public data: {
      treinoId: string;
    },
  ) {}

  ngOnInit(): void {
    this.treinoId = this.data.treinoId;

    this.carregarTreino();
    this.carregarExercicios();
  }

  carregarTreino(): void {
    this.carregandoTreino = true;

    this.treinoService.buscarTreinoPorId(this.treinoId).subscribe({
      next: (response) => {
        this.treino = response;
        this.carregandoTreino = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar treino:', erro);
        this.carregandoTreino = false;
      }
    });
  }

   carregarExercicios(): void {
    this.carregandoExercicios = true;

    this.treinoItemService.listarItensDoTreino(this.treinoId).subscribe({
      next: (response) => {
        this.exercicios = response;
        this.carregandoExercicios = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar exercícios do treino:', erro);
        this.carregandoExercicios = false;
      }
    });
  }

  temObservacoes(): boolean {
    return !!this.treino?.observacoes && this.treino.observacoes.trim().length > 0;
  }


  editar(item: TreinoItem) {
    console.log('Editar', item);
  }

  remover(id: string) {
    console.log('Remover', id);
  }

  fechar(): void {
    this.dialogRef.close();
  }
}
