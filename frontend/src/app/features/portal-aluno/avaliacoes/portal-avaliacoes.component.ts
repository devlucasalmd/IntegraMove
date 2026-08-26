import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { AvaliacaoService } from '../../avaliacoes/services/avaliacao.service';
import { AvaliacaoRealizadaResponseDTO } from '../../avaliacoes/models/avaliacao-response.model';

/**
 * Aba "Avaliações" do portal do aluno.
 *
 * Reaproveita o mesmo `AvaliacaoService`/contrato da aba de avaliações do
 * perfil do aluno (visão administrativa). Como os campos de cada template
 * são dinâmicos (snapshot em `valores`), a listagem exibe genericamente
 * cada `nomeCampo`/`valor`/`unidade`, sem agrupamento fixo de membros.
 */
@Component({
  selector: 'app-portal-avaliacoes',
  standalone: true,
  imports: [CommonModule, MatIconModule, MatProgressSpinnerModule],
  templateUrl: './portal-avaliacoes.component.html',
  styleUrl: './portal-avaliacoes.component.css',
})
export class PortalAvaliacoesComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly avaliacaoService = inject(AvaliacaoService);

  protected alunoId = '';
  protected avaliacoes: AvaliacaoRealizadaResponseDTO[] = [];

  protected carregando = true;
  protected erro = false;

  ngOnInit(): void {
    this.alunoId = this.route.parent?.snapshot.paramMap.get('alunoId') ?? '';

    if (!this.alunoId) {
      this.erro = true;
      this.carregando = false;
      return;
    }

    this.avaliacaoService.listarPorAluno(this.alunoId).subscribe({
      next: (avaliacoes: AvaliacaoRealizadaResponseDTO[]) => {
        this.avaliacoes = [...avaliacoes].sort((a, b) => b.dataAvaliacao.localeCompare(a.dataAvaliacao));
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar avaliações:', erro);
        this.erro = true;
        this.carregando = false;
      },
    });
  }
}
