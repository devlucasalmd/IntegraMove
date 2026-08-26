import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

import { AlunoService } from '../../alunos/services/aluno.service';
import { AlunoResumoService } from '../service/aluno-resumo.service';
import {
  FormaPagamento,
  ResumoAlunoResponseDTO,
  StatusVenda,
  TipoVenda
} from '../model/resumo-aluno.model';

const LABEL_TIPO_VENDA: Record<TipoVenda, string> = {
  PLANO: 'Plano',
  AVALIACAO: 'Avaliação',
  DIARIA: 'Diária',
  PRODUTO: 'Produto'
};

const LABEL_STATUS_VENDA: Record<StatusVenda, string> = {
  PENDENTE: 'Pendente',
  CONCLUIDA: 'Concluída',
  CANCELADA: 'Cancelada'
};

const LABEL_FORMA_PAGAMENTO: Record<FormaPagamento, string> = {
  DEBITO: 'Débito',
  CREDITO: 'Crédito',
  PIX: 'Pix',
  DINHEIRO: 'Dinheiro',
  BOLETO: 'Boleto'
};

@Component({
  selector: 'app-aluno-resumo',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    DatePipe,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatInputModule
  ],
  templateUrl: './aluno-resumo.component.html',
  styleUrl: './aluno-resumo.component.css'
})
export class AlunoResumoComponent implements OnInit {

  alunoId!: string;

  resumo: ResumoAlunoResponseDTO | null = null;
  carregandoResumo = false;
  erroResumo: string | null = null;

  // Observação do aluno
  observacao = '';
  editandoObservacao = false;
  salvandoObservacao = false;

  constructor(
    private route: ActivatedRoute,
    private alunoService: AlunoService,
    private alunoResumoService: AlunoResumoService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.alunoId = this.route.parent?.snapshot.paramMap.get('alunoId') ?? '';

    if (!this.alunoId) {
      this.alunoId = this.route.snapshot.paramMap.get('alunoId') ?? '';
    }

    this.carregarResumo();
    this.carregarObservacao();
  }

  carregarResumo(): void {
    this.carregandoResumo = true;
    this.erroResumo = null;

    this.alunoResumoService.buscarResumo(this.alunoId).subscribe({
      next: (resumo) => {
        this.resumo = resumo;
        this.carregandoResumo = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar resumo do aluno:', erro);
        this.resumo = null;
        this.erroResumo = 'Não foi possível carregar o resumo deste aluno. Tente novamente em instantes.';
        this.carregandoResumo = false;
      }
    });
  }

  recarregarResumo(): void {
    this.carregarResumo();
  }

  carregarObservacao(): void {
    this.alunoService.buscarAlunoPorId(this.alunoId).subscribe({
      next: (aluno) => {
        this.observacao = aluno.observacao ?? '';
      },
      error: (erro) => {
        console.error('Erro ao carregar observação do aluno:', erro);
      }
    });
  }

  habilitarEdicaoObservacao(): void {
    this.editandoObservacao = true;
  }

  cancelarEdicaoObservacao(): void {
    this.editandoObservacao = false;
    this.carregarObservacao();
  }

  salvarObservacao(): void {
    this.salvandoObservacao = true;

    this.alunoService.atualizarObservacao(this.alunoId, this.observacao).subscribe({
      next: () => {
        this.salvandoObservacao = false;
        this.editandoObservacao = false;
        this.snackBar.open('Observação salva com sucesso.', 'Fechar', { duration: 3000 });
      },
      error: (erro) => {
        // O endpoint PATCH /alunos/{id}/observacao ainda não existe no
        // backend, então em desenvolvimento essa chamada falha (404).
        // Mantemos a alteração aplicada localmente para não travar o uso
        // da tela enquanto o endpoint não é implementado.
        console.warn('Endpoint de observação ainda não implementado no backend:', erro);
        this.salvandoObservacao = false;
        this.editandoObservacao = false;
        this.snackBar.open(
          'Observação atualizada localmente (endpoint do backend ainda não existe).',
          'Fechar',
          { duration: 4000 }
        );
      }
    });
  }

  get quantidadeParcelasEmAtraso(): number {
    return this.resumo?.quantidadeParcelasEmAtraso ?? 0;
  }

  labelTipoVenda(tipo: TipoVenda): string {
    return LABEL_TIPO_VENDA[tipo] ?? tipo;
  }

  labelStatusVenda(status: StatusVenda): string {
    return LABEL_STATUS_VENDA[status] ?? status;
  }

  labelFormaPagamento(forma: FormaPagamento): string {
    return LABEL_FORMA_PAGAMENTO[forma] ?? forma;
  }

  classeStatusVenda(status: StatusVenda): string {
    if (status === 'CONCLUIDA') return 'success';
    if (status === 'CANCELADA') return 'danger';
    return 'warning';
  }
}
