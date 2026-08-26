import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTooltipModule } from '@angular/material/tooltip';

import { VendaService } from '../service/venda.service';
import { StatusVenda, TipoVenda, VendaResponseDTO } from '../model/venda.model';
import { PlanoService } from '../../planos/plano.service';
import { PlanoResponseDTO } from '../../planos/models/plano-response';
import { VendaFormDialogComponent } from './venda-form-dialog/venda-form-dialog.component';

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

/**
 * Aba "Vendas" do perfil do aluno.
 *
 * Lista as vendas registradas para o aluno (`GET /vendas/aluno/{alunoId}`)
 * e permite registrar novas vendas via dialog (`POST /vendas`).
 */
@Component({
  selector: 'app-aluno-vendas',
  standalone: true,
  imports: [
    CommonModule,
    CurrencyPipe,
    DatePipe,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatChipsModule,
    MatDialogModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    MatTooltipModule
  ],
  templateUrl: './aluno-vendas.component.html',
  styleUrl: './aluno-vendas.component.css'
})
export class AlunoVendasComponent implements OnInit {

  alunoId!: string;

  vendas: VendaResponseDTO[] = [];
  planosAtivos: PlanoResponseDTO[] = [];
  planosPorId: Record<string, string> = {};

  carregando = false;
  erro: string | null = null;

  displayedColumns: string[] = [
    'tipo',
    'descricao',
    'valor',
    'dataVenda',
    'status',
    'contrato'
  ];

  constructor(
    private route: ActivatedRoute,
    private vendaService: VendaService,
    private planoService: PlanoService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.alunoId = this.route.parent?.snapshot.paramMap.get('alunoId') ?? '';

    if (!this.alunoId) {
      this.alunoId = this.route.snapshot.paramMap.get('alunoId') ?? '';
    }

    this.carregarPlanos();
    this.carregarVendas();
  }

  carregarPlanos(): void {
    this.planoService.listarPlanos().subscribe({
      next: (planos) => {
        this.planosAtivos = planos.filter((plano) => plano.ativo);
        this.planosPorId = planos.reduce((mapa, plano) => {
          mapa[plano.id] = plano.nome;
          return mapa;
        }, {} as Record<string, string>);
      },
      error: (erro) => {
        console.error('Erro ao carregar planos:', erro);
      }
    });
  }

  carregarVendas(): void {
    this.carregando = true;
    this.erro = null;

    this.vendaService.listarPorAluno(this.alunoId).subscribe({
      next: (vendas) => {
        this.vendas = vendas;
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar vendas do aluno:', erro);
        this.vendas = [];
        this.erro = 'Não foi possível carregar as vendas deste aluno. Tente novamente em instantes.';
        this.carregando = false;
      }
    });
  }

  abrirNovaVenda(): void {
    const dialogRef = this.dialog.open(VendaFormDialogComponent, {
      width: '640px',
      maxWidth: '96vw',
      autoFocus: false,
      disableClose: true,
      panelClass: 'dialog-profissional',
      data: {
        alunoId: this.alunoId,
        planosAtivos: this.planosAtivos
      }
    });

    dialogRef.afterClosed().subscribe((venda: VendaResponseDTO | undefined) => {
      if (!venda) {
        return;
      }

      this.carregarVendas();

      if (venda.contratoId) {
        this.snackBar.open('Venda registrada e contrato criado com sucesso.', 'Fechar', { duration: 4000 });
      } else {
        this.snackBar.open('Venda registrada com sucesso.', 'Fechar', { duration: 3000 });
      }
    });
  }

  descricaoVenda(venda: VendaResponseDTO): string {
    if (venda.tipo === 'PLANO') {
      return venda.planoId ? (this.planosPorId[venda.planoId] ?? 'Plano') : 'Plano';
    }

    return venda.descricao ?? '-';
  }

  labelTipoVenda(tipo: TipoVenda): string {
    return LABEL_TIPO_VENDA[tipo] ?? tipo;
  }

  labelStatusVenda(status: StatusVenda): string {
    return LABEL_STATUS_VENDA[status] ?? status;
  }

  classeStatusVenda(status: StatusVenda): string {
    if (status === 'CONCLUIDA') return 'success';
    if (status === 'CANCELADA') return 'danger';
    return 'warning';
  }
}
