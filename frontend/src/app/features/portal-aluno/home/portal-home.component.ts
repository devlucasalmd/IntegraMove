import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { forkJoin } from 'rxjs';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { AlunoService } from '../../alunos/services/aluno.service';
import { AlunoDetalheResponseDTO } from '../../alunos/models/aluno-detalhe-response.model';
import { AlunoTreinoService } from '../../aluno-treino/service/aluno-treino.service';
import { AlunoTreinoResponseDTO } from '../../aluno-treino/model/aluno-treino-response.model';
import { PagamentoService } from '../../aluno-financeiro/service/pagamento.service';
import { PagamentoResponseDTO } from '../../aluno-financeiro/model/pagamento-response.model';

@Component({
  selector: 'app-portal-home',
  standalone: true,
  imports: [CommonModule, RouterLink, MatIconModule, MatProgressSpinnerModule],
  templateUrl: './portal-home.component.html',
  styleUrl: './portal-home.component.css',
})
export class PortalHomeComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly alunoService = inject(AlunoService);
  private readonly treinoAlunoService = inject(AlunoTreinoService);
  private readonly pagamentoService = inject(PagamentoService);

  protected alunoId = '';
  protected aluno: AlunoDetalheResponseDTO | null = null;
  protected fichaAtiva: AlunoTreinoResponseDTO | null = null;
  protected pagamentos: PagamentoResponseDTO[] = [];

  protected carregando = true;
  protected erro = false;

  ngOnInit(): void {
    this.alunoId = this.route.parent?.snapshot.paramMap.get('alunoId') ?? '';

    if (!this.alunoId) {
      this.erro = true;
      this.carregando = false;
      return;
    }

    forkJoin({
      aluno: this.alunoService.buscarAlunoPorId(this.alunoId),
      fichas: this.treinoAlunoService.listarPorAluno(this.alunoId),
      pagamentos: this.pagamentoService.listarPorAluno(this.alunoId),
    }).subscribe({
      next: ({ aluno, fichas, pagamentos }) => {
        this.aluno = aluno;
        this.fichaAtiva = fichas.find((f) => f.ativo) ?? null;
        this.pagamentos = pagamentos;
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar dashboard do aluno:', erro);
        this.erro = true;
        this.carregando = false;
      },
    });
  }

  protected get proximoPagamento(): PagamentoResponseDTO | null {
    const pendentes = this.pagamentos
      .filter((p) => p.status === 'A_VENCER' || p.status === 'EM_ABERTO' || p.status === 'VENCIDO')
      .sort((a, b) => new Date(a.dataVencimento).getTime() - new Date(b.dataVencimento).getTime());

    return pendentes[0] ?? null;
  }

  protected get statusPagamentoLabel(): string {
    const pagamento = this.proximoPagamento;
    if (!pagamento) return 'Em dia';
    if (pagamento.status === 'VENCIDO') return 'Pagamento vencido';
    return 'Pagamento pendente';
  }

  protected get statusPagamentoClasse(): string {
    const pagamento = this.proximoPagamento;
    if (!pagamento) return 'ok';
    if (pagamento.status === 'VENCIDO') return 'alerta';
    return 'atencao';
  }
}
