import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { forkJoin, of } from 'rxjs';
import { catchError, finalize, switchMap } from 'rxjs/operators';
import { TreinoResponseDTO } from '../../../treinos/models/treino-response.model';
import { TreinoItemResponseDTO } from '../../../treinos/models/treino-item-response';
import { TreinoAlunoResponseDTO } from '../../model/treino-aluno-response.model';
import { TreinoAlunoService } from '../../service/treino-aluno.service';
import { TreinoService } from '../../../treinos/services/treino.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';


interface TreinoDaFichaDetalhado {
  treino: TreinoResponseDTO;
  exercicios: TreinoItemResponseDTO[];
}

@Component({
  selector: 'app-ficha-treino-detalhe',
  templateUrl: './treino-aluno-detalhe.component.html',
  styleUrls: ['./treino-aluno-detalhe.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule
  ]
})
export class TreinoAlunoDetalheComponent implements OnInit {
  alunoId = '';
  fichaId = '';

  ficha: TreinoAlunoResponseDTO | null = null;
  treinosDetalhados: TreinoDaFichaDetalhado[] = [];

  carregando = false;
  erro = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private treinoAlunoService: TreinoAlunoService,
    private treinoService: TreinoService
  ) {}

  ngOnInit(): void {
    this.alunoId =
      this.route.snapshot.paramMap.get('alunoId') ||
      this.route.parent?.snapshot.paramMap.get('alunoId') ||
      this.route.parent?.parent?.snapshot.paramMap.get('alunoId') ||
      '';

    this.fichaId =
      this.route.snapshot.paramMap.get('fichaId') ||
      this.route.snapshot.paramMap.get('id') ||
      '';

    this.carregarFicha();
  }

  carregarFicha(): void {
    if (!this.alunoId || !this.fichaId) {
      this.erro = true;
      return;
    }

    this.carregando = true;
    this.erro = false;

    this.treinoAlunoService
      .buscarPorId(this.alunoId, this.fichaId)
      .pipe(
        switchMap((ficha) => {
          this.ficha = ficha;

          if (!ficha.treinosIds || ficha.treinosIds.length === 0) {
            return of([]);
          }

          const requests = ficha.treinosIds.map((treinoId) =>
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

  voltar(): void {
    this.router.navigate(['../'], {
      relativeTo: this.route,
    });
  }

  totalTreinos(): number {
    return this.treinosDetalhados.length;
  }

  totalExercicios(): number {
    return this.treinosDetalhados.reduce(
      (total, item) => total + item.exercicios.length,
      0
    );
  }

  formatarData(data?: string): string {
    if (!data) {
      return 'Não informado';
    }

    const [ano, mes, dia] = data.split('-');
    return `${dia}/${mes}/${ano}`;
  }

  statusLabel(): string {
    return this.ficha?.ativo ? 'Ativa' : 'Inativa';
  }
}
