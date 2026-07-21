import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { TreinoAlunoResponseDTO } from '../../model/treino-aluno-response.model';
import { TreinoAlunoService } from '../../service/treino-aluno.service';

@Component({
  selector: 'app-treino-aluno-list',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    DatePipe,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './treino-aluno-list.component.html',
  styleUrl: './treino-aluno-list.component.css',
})
export class TreinoAlunoListComponent implements OnInit {
  alunoId!: string;

  fichas: TreinoAlunoResponseDTO[] = [];
  carregando = false;

  constructor(
    private route: ActivatedRoute,
    private treinoAlunoService: TreinoAlunoService,
  ) {}

  ngOnInit(): void {
    this.alunoId =
      this.route.snapshot.paramMap.get('alunoId') ||
      this.route.parent?.snapshot.paramMap.get('alunoId') ||
      this.route.parent?.parent?.snapshot.paramMap.get('alunoId') ||
      '';

    if (!this.alunoId) {
      console.error('alunoId não encontrado na rota');
      return;
    }

    this.carregarFichas();
  }

  carregarFichas(): void {
    this.carregando = true;

    this.treinoAlunoService.listarPorAluno(this.alunoId).subscribe({
      next: (response) => {
        this.fichas = response;
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar fichas de treino:', erro);
        this.carregando = false;
      },
    });
  }

  get fichaAtiva(): TreinoAlunoResponseDTO | undefined {
    return this.fichas.find((ficha) => ficha.ativo);
  }
}
