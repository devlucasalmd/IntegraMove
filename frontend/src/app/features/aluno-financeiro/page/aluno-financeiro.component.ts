import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatMenuModule } from '@angular/material/menu';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { PagamentoResponseDTO } from '../model/pagamento-response.model';
import { PagamentoService } from '../service/pagamento.service';


@Component({
  selector: 'app-aluno-financeiro',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    CurrencyPipe,
    DatePipe,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatChipsModule,
    MatDialogModule,
    MatSnackBarModule,
    MatMenuModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './aluno-financeiro.component.html',
  styleUrl: './aluno-financeiro.component.css'
})
export class AlunoFinanceiroComponent implements OnInit {

  alunoId!: string;

  pagamentos: PagamentoResponseDTO[] = [];

  carregando = false;

  displayedColumns: string[] = [
    'observacoes',
    'dataVencimento',
    'dataPagamento',
    'valor',
    'formaPagamento',
    'status',
    'acoes'
  ];

  constructor(
    private route: ActivatedRoute,
    private pagamentoService: PagamentoService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.alunoId = this.route.parent?.snapshot.paramMap.get('alunoId') ?? '';

    if (!this.alunoId) {
      this.alunoId = this.route.snapshot.paramMap.get('alunoId') ?? '';
    }

    this.carregarPagamentos();
  }

  carregarPagamentos(): void {
    this.carregando = true;

    this.pagamentoService.listarPorAluno(this.alunoId).subscribe({
      next: (response) => {
        this.pagamentos = response;
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar pagamentos:', erro);
        this.snackBar.open('Erro ao carregar pagamentos do aluno.', 'Fechar', {
          duration: 3000
        });
        this.carregando = false;
      }
    });
  }

  registrarPagamento(pagamento: PagamentoResponseDTO): void {
    const hoje = new Date().toISOString().substring(0, 10);

    this.pagamentoService.pagarPagamento(this.alunoId, pagamento.id, {
      dataPagamento: hoje,
      formaPagamento: 'PIX'
    }).subscribe({
      next: () => {
        this.snackBar.open('Pagamento registrado com sucesso.', 'Fechar', {
          duration: 3000
        });
        this.carregarPagamentos();
      },
      error: (erro) => {
        console.error('Erro ao registrar pagamento:', erro);
        this.snackBar.open('Erro ao registrar pagamento.', 'Fechar', {
          duration: 3000
        });
      }
    });
  }

  cancelarPagamento(pagamento: PagamentoResponseDTO): void {
    this.pagamentoService.cancelarPagamento(this.alunoId, pagamento.id).subscribe({
      next: () => {
        this.snackBar.open('Pagamento cancelado com sucesso.', 'Fechar', {
          duration: 3000
        });
        this.carregarPagamentos();
      },
      error: (erro) => {
        console.error('Erro ao cancelar pagamento:', erro);
        this.snackBar.open('Erro ao cancelar pagamento.', 'Fechar', {
          duration: 3000
        });
      }
    });
  }

  get totalPago(): number {
    return this.pagamentos
      .filter(pagamento => pagamento.status === 'PAGO')
      .reduce((total, pagamento) => total + pagamento.valor, 0);
  }

  get totalEmAberto(): number {
    return this.pagamentos
      .filter(pagamento =>
        pagamento.status === 'A_VENCER' ||
        pagamento.status === 'EM_ABERTO'
      )
      .reduce((total, pagamento) => total + pagamento.valor, 0);
  }

  get totalVencido(): number {
    return this.pagamentos
      .filter(pagamento => pagamento.status === 'VENCIDO')
      .reduce((total, pagamento) => total + pagamento.valor, 0);
  }

  get proximoVencimento(): string | null {
    const pagamentosFuturos = this.pagamentos
      .filter(pagamento =>
        pagamento.status === 'A_VENCER' ||
        pagamento.status === 'EM_ABERTO'
      )
      .sort((a, b) =>
        new Date(a.dataVencimento).getTime() - new Date(b.dataVencimento).getTime()
      );

    return pagamentosFuturos.length > 0
      ? pagamentosFuturos[0].dataVencimento
      : null;
  }

  getStatusLabel(status: string): string {
    const labels: Record<string, string> = {
      A_VENCER: 'A vencer',
      EM_ABERTO: 'Em aberto',
      VENCIDO: 'Vencido',
      PAGO: 'Pago',
      CANCELADO: 'Cancelado'
    };

    return labels[status] ?? status;
  }

  getStatusClass(status: string): string {
    const classes: Record<string, string> = {
      A_VENCER: 'status-a-vencer',
      EM_ABERTO: 'status-em-aberto',
      VENCIDO: 'status-vencido',
      PAGO: 'status-pago',
      CANCELADO: 'status-cancelado'
    };

    return classes[status] ?? '';
  }
}
