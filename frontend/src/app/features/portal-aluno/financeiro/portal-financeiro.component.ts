import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { PagamentoService } from '../../aluno-financeiro/service/pagamento.service';
import { PagamentoResponseDTO } from '../../aluno-financeiro/model/pagamento-response.model';

@Component({
  selector: 'app-portal-financeiro',
  standalone: true,
  imports: [CommonModule, MatIconModule, MatProgressSpinnerModule],
  templateUrl: './portal-financeiro.component.html',
  styleUrl: './portal-financeiro.component.css',
})
export class PortalFinanceiroComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly pagamentoService = inject(PagamentoService);

  protected alunoId = '';
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

    this.pagamentoService.listarPorAluno(this.alunoId).subscribe({
      next: (pagamentos) => {
        this.pagamentos = [...pagamentos].sort(
          (a, b) => new Date(b.dataVencimento).getTime() - new Date(a.dataVencimento).getTime()
        );
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar financeiro do aluno:', erro);
        this.erro = true;
        this.carregando = false;
      },
    });
  }

  protected get totalPago(): number {
    return this.pagamentos
      .filter((p) => p.status === 'PAGO')
      .reduce((total, p) => total + p.valor, 0);
  }

  protected get totalEmAberto(): number {
    return this.pagamentos
      .filter((p) => p.status === 'PENDENTE')
      .reduce((total, p) => total + p.valor, 0);
  }

  protected get totalVencido(): number {
    return this.pagamentos
      .filter((p) => p.status === 'ATRASADO')
      .reduce((total, p) => total + p.valor, 0);
  }

  protected statusLabel(status: string): string {
    const labels: Record<string, string> = {
      PENDENTE: 'Em aberto',
      ATRASADO: 'Vencido',
      PAGO: 'Pago',
      CANCELADO: 'Cancelado',
    };
    return labels[status] ?? status;
  }

  protected statusClasse(status: string): string {
    const classes: Record<string, string> = {
      PENDENTE: 'status-em-aberto',
      ATRASADO: 'status-vencido',
      PAGO: 'status-pago',
      CANCELADO: 'status-cancelado',
    };
    return classes[status] ?? '';
  }
}
