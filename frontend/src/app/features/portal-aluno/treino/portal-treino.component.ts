import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { forkJoin, of } from 'rxjs';
import { catchError, finalize, switchMap } from 'rxjs/operators';
import { MatIconModule } from '@angular/material/icon';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { AlunoTreinoService } from '../../aluno-treino/service/aluno-treino.service';
import { AlunoTreinoResponseDTO } from '../../aluno-treino/model/aluno-treino-response.model';
import { TreinoService } from '../../treinos/services/treino.service';
import { TreinoResponseDTO } from '../../treinos/models/treino-response.model';
import { TreinoItemResponseDTO } from '../../treinos/models/treino-item-response';

interface TreinoDetalhado {
  treino: TreinoResponseDTO;
  exercicios: TreinoItemResponseDTO[];
}

@Component({
  selector: 'app-portal-treino',
  standalone: true,
  imports: [CommonModule, MatIconModule, MatExpansionModule, MatProgressSpinnerModule],
  templateUrl: './portal-treino.component.html',
  styleUrl: './portal-treino.component.css',
})
export class PortalTreinoComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly treinoAlunoService = inject(AlunoTreinoService);
  private readonly treinoService = inject(TreinoService);

  protected alunoId = '';
  protected fichas: AlunoTreinoResponseDTO[] = [];
  protected fichaAtiva: AlunoTreinoResponseDTO | null = null;
  protected treinosDetalhados: TreinoDetalhado[] = [];

  protected carregando = true;
  protected erro = false;

  ngOnInit(): void {
    this.alunoId = this.route.parent?.snapshot.paramMap.get('alunoId') ?? '';

    if (!this.alunoId) {
      this.erro = true;
      this.carregando = false;
      return;
    }

    this.treinoAlunoService
      .listarPorAluno(this.alunoId)
      .pipe(
        switchMap((fichas) => {
          this.fichas = fichas;
          this.fichaAtiva = fichas.find((f) => f.ativo) ?? null;

          if (!this.fichaAtiva || !this.fichaAtiva.treinosIds?.length) {
            return of([]);
          }

          const requests = this.fichaAtiva.treinosIds.map((treinoId) =>
            forkJoin({
              treino: this.treinoService.buscarTreinoPorId(treinoId),
              exercicios: this.treinoService
                .listarItensPorTreino(treinoId)
                .pipe(catchError(() => of([]))),
            })
          );

          return forkJoin(requests);
        }),
        catchError((erro) => {
          console.error('Erro ao carregar ficha de treino:', erro);
          this.erro = true;
          return of([]);
        }),
        finalize(() => {
          this.carregando = false;
        })
      )
      .subscribe({
        next: (response) => {
          this.treinosDetalhados = response;
        },
      });
  }

  protected get fichasAnteriores(): AlunoTreinoResponseDTO[] {
    return this.fichas.filter((f) => !f.ativo);
  }

  protected totalExercicios(): number {
    return this.treinosDetalhados.reduce((total, item) => total + item.exercicios.length, 0);
  }
}
