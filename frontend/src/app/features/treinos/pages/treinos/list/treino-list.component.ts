import { TreinoService } from './../../../services/treino.service';
import { Component, OnInit } from '@angular/core';
import { forkJoin, of } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';

import { TreinoDialogComponent } from '../treino-dialog/treino-dialog.component';
import { TreinoResponseDTO } from '../../../models/treino-response.model';
import { ExercicioDialogComponent } from '../treino-detail/exercicio-dialog/exercicio-dialog.component';
import { TreinoItemService } from '../../../services/treino-item.service';

@Component({
  selector: 'app-treino-list',
  standalone: true,
  templateUrl: './treino-list.component.html',
  styleUrls: ['./treino-list.component.css'],
  imports: [
    CommonModule,
    RouterModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule
  ]
})
export class TreinoListComponent implements OnInit {

  treinos: TreinoResponseDTO[] = [];
  carregando = false;

  constructor(
    private dialog: MatDialog,
    private treinoService :TreinoService,
    private treinoItemService: TreinoItemService
  ) {}

  ngOnInit(): void {
    this.carregarTreinos();
  }

  // carregarTreinos(): void {

  //   this.treinoService.listarTreinos().subscribe({
  //     next: (response) => {
  //       this.treinos = response.map(treino => ({
  //         ...treino,
  //         exercicios: treino.exercicios ?? []
  //       }));
  //     }
  //   });
  // }

   carregarTreinos(): void {
    this.carregando = true;

    this.treinoService.listarTreinos()
      .pipe(
        switchMap((treinos) => {
          if (treinos.length === 0) {
            return of([]);
          }

          const requisicoes = treinos.map(treino =>
            this.treinoItemService.listarItensDoTreino(treino.id).pipe(
              map(itens => ({
                ...treino,
                exercicios: itens ?? []
              })),
              catchError(() => of({
                ...treino,
                exercicios: []
              }))
            )
          );

          return forkJoin(requisicoes);
        })
      )
      .subscribe({
        next: (treinosComExercicios) => {
          this.treinos = treinosComExercicios;
          this.carregando = false;
        },
        error: (erro) => {
          console.error('Erro ao carregar treinos:', erro);
          this.carregando = false;
        }
      });
  }

  abrirNovoTreino(): void {
    const dialogRef = this.dialog.open(TreinoDialogComponent, {
      width: '720px',
      maxWidth: '95vw',
      disableClose: true,
      autoFocus: false,
      panelClass: 'dialog-profissional'
    });

    dialogRef.afterClosed().subscribe((treinoSalvo: TreinoResponseDTO | undefined) => {
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
        treinoNome: treino.nome
      }
    });

    dialogRef.afterClosed().subscribe((atualizou: boolean) => {
      if (atualizou) {
        this.carregarTreinos();
      }
    });
  }

  editarTreino(id: string): void {
    console.log('Editar', id);
  }

  excluirTreino(id: string): void {
    console.log('Excluir', id);
  }
}
