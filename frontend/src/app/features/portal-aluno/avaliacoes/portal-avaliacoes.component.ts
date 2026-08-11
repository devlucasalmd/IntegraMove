import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { AvaliacaoService } from '../../avaliacoes/services/avaliacao.service';
import { AvaliacaoResponseDTO } from '../../avaliacoes/models/avaliacao-response.model';

interface GrupoMedida {
  label: string;
  direito: number;
  esquerdo: number;
}

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
  protected avaliacoes: AvaliacaoResponseDTO[] = [];

  protected carregando = true;
  protected erro = false;

  ngOnInit(): void {
    this.alunoId = this.route.parent?.snapshot.paramMap.get('alunoId') ?? '';

    if (!this.alunoId) {
      this.erro = true;
      this.carregando = false;
      return;
    }

    this.avaliacaoService.listarAvaliacoes(this.alunoId).subscribe({
      next: (avaliacoes: AvaliacaoResponseDTO[]) => {
        this.avaliacoes = [...avaliacoes].sort(
          (a, b) => new Date(b.dataAvaliacao).getTime() - new Date(a.dataAvaliacao).getTime()
        );
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar avaliações:', erro);
        this.erro = true;
        this.carregando = false;
      },
    });
  }

  protected gruposMedida(avaliacao: AvaliacaoResponseDTO): GrupoMedida[] {
    return [
      { label: 'Remada braço', direito: avaliacao.remadaBracoD, esquerdo: avaliacao.remadaBracoE },
      { label: 'Elevação lateral', direito: avaliacao.elevacaoLatD, esquerdo: avaliacao.elevacaoLatE },
      { label: 'Extensão joelho', direito: avaliacao.extensaoJoelhoD, esquerdo: avaliacao.extensaoJoelhoE },
      { label: 'Flexão joelho', direito: avaliacao.flexaoJoelhoD, esquerdo: avaliacao.flexaoJoelhoE },
      { label: 'Extensão quadril', direito: avaliacao.extensaoQuadrilD, esquerdo: avaliacao.extensaoQuadrilE },
    ];
  }
}
