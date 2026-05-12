import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ExercicioDialogComponent } from './exercicio-dialog/exercicio-dialog.component';

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
    CommonModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatDialogModule
  ]
})
export class TreinoDetailComponent implements OnInit {

  treinoId!: string;

  treino = {
    nome: 'Treino A',
    responsavel: 'Carlos',
    nivel: 'Intermediário',
    funcionalidade: 'Hipertrofia'
  };

  colunas: string[] = [
    'nome',
    'series',
    'reps',
    'carga',
    'descanso',
    'observacao'
  ];

  exercicios: TreinoItem[] = [];

  constructor(
    private route: ActivatedRoute,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.treinoId = this.route.snapshot.paramMap.get('id')!;

    // MOCK
    this.exercicios = [
      {
        id: '1',
        nome: 'Supino Reto',
        series: 3,
        repeticoes: '10',
        carga: 40,
        descanso: 60
      },
      {
        id: '2',
        nome: 'Crucifixo',
        series: 3,
        repeticoes: '12',
        carga: 20,
        descanso: 45
      }
    ];
  }

  editar(item: TreinoItem) {
    console.log('Editar', item);
  }

  remover(id: string) {
    console.log('Remover', id);
  }

  adicionar() {
    const dialogRef = this.dialog.open(ExercicioDialogComponent, { width: '600px'});
    dialogRef.afterClosed().subscribe((result) => {
      if(result) {
        this.exercicios.push({
          id: Date.now().toString(),
          ...result
        })
      }
    })
  }
}
