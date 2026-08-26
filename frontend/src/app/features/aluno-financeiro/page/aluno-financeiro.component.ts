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
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { PagamentoResponseDTO } from '../model/pagamento-response.model';
import { PagamentoService } from '../service/pagamento.service';
import { ContratoService } from '../../aluno-contrato/service/contrato.service';
import { VendaService } from '../../aluno-vendas/service/venda.service';
import { PlanoService } from '../../planos/plano.service';
import { forkJoin } from 'rxjs';
import { PagamentoFormDialogComponent } from './pagamento-form-dialog/pagamento-form-dialog.component';


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

  private planoNomePorId = new Map<string, string>();
  private planoIdPorContratoId = new Map<string, string>();
  private vendaPorId = new Map<string, { tipo: string; descricao: string | null; planoId: string | null }>();

  constructor(
    private route: ActivatedRoute,
    private pagamentoService: PagamentoService,
    private contratoService: ContratoService,
    private vendaService: VendaService,
    private planoService: PlanoService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
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

    forkJoin({
      pagamentos: this.pagamentoService.listarPorAluno(this.alunoId),
      contratos: this.contratoService.listarPorAluno(this.alunoId),
      vendas: this.vendaService.listarPorAluno(this.alunoId),
      planos: this.planoService.listarPlanos()
    }).subscribe({
      next: ({ pagamentos, contratos, vendas, planos }) => {
        this.planoNomePorId = new Map(planos.map(plano => [plano.id, plano.nome]));
        this.planoIdPorContratoId = new Map(contratos.map(contrato => [contrato.id, contrato.planoId]));
        this.vendaPorId = new Map(vendas.map(venda => [
          venda.id,
          { tipo: venda.tipo, descricao: venda.descricao, planoId: venda.planoId }
        ]));

        this.pagamentos = pagamentos;
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

  nomeParcela(pagamento: PagamentoResponseDTO): string {
    const planoIdDoContrato = pagamento.contratoId
      ? this.planoIdPorContratoId.get(pagamento.contratoId)
      : undefined;

    if (planoIdDoContrato) {
      return this.planoNomePorId.get(planoIdDoContrato) ?? 'Plano';
    }

    const venda = pagamento.vendaId ? this.vendaPorId.get(pagamento.vendaId) : undefined;

    if (venda) {
      if (venda.tipo === 'PLANO' && venda.planoId) {
        return this.planoNomePorId.get(venda.planoId) ?? 'Plano';
      }

      return venda.descricao ?? 'Pagamento';
    }

    return 'Pagamento';
  }

  podeRegistrarPagamento(pagamento: PagamentoResponseDTO): boolean {
    return pagamento.status === 'PENDENTE' || pagamento.status === 'ATRASADO';
  }

  abrirRegistrarPagamento(pagamento: PagamentoResponseDTO): void {
    const dialogRef = this.dialog.open(PagamentoFormDialogComponent, {
      data: { pagamento, descricao: this.nomeParcela(pagamento) }
    });

    dialogRef.afterClosed().subscribe((resultado) => {
      if (resultado) {
        this.snackBar.open('Pagamento registrado com sucesso.', 'Fechar', {
          duration: 3000
        });
        this.carregarPagamentos();
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
      .filter(pagamento => pagamento.status === 'PENDENTE')
      .reduce((total, pagamento) => total + pagamento.valor, 0);
  }

  get totalVencido(): number {
    return this.pagamentos
      .filter(pagamento => pagamento.status === 'ATRASADO')
      .reduce((total, pagamento) => total + pagamento.valor, 0);
  }

  get proximoVencimento(): string | null {
    const pagamentosFuturos = this.pagamentos
      .filter(pagamento => pagamento.status === 'PENDENTE')
      .sort((a, b) =>
        new Date(a.dataVencimento).getTime() - new Date(b.dataVencimento).getTime()
      );

    return pagamentosFuturos.length > 0
      ? pagamentosFuturos[0].dataVencimento
      : null;
  }

  getStatusLabel(status: string): string {
    const labels: Record<string, string> = {
      PENDENTE: 'Em aberto',
      ATRASADO: 'Vencido',
      PAGO: 'Pago',
      CANCELADO: 'Cancelado'
    };

    return labels[status] ?? status;
  }

  getStatusClass(status: string): string {
    const classes: Record<string, string> = {
      PENDENTE: 'status-em-aberto',
      ATRASADO: 'status-vencido',
      PAGO: 'status-pago',
      CANCELADO: 'status-cancelado'
    };

    return classes[status] ?? '';
  }
}
