import { CommonModule, CurrencyPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';

import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTooltipModule } from '@angular/material/tooltip';

import { PlanoService } from '../../plano.service';
import { Periodicidade, PlanoResponseDTO } from '../../models/plano-response';
import { PlanoRequestDTO } from '../../models/plano-request.model';
import { PlanoFormDialogComponent, PlanoFormDialogData } from '../plano-form-dialog/plano-form-dialog.component';

const LABEL_PERIODICIDADE: Record<Periodicidade, string> = {
  DIARIA: 'Diária',
  MENSAL: 'Mensal',
  TRIMESTRAL: 'Trimestral',
  SEMESTRAL: 'Semestral',
  ANUAL: 'Anual'
};

/**
 * Tela de gestão de planos (`/planos`).
 *
 * Lista os planos cadastrados (`GET /planos`) e permite criar
 * (`POST /planos`), editar e ativar/inativar (`PUT /planos/{id}`) planos.
 */
@Component({
  selector: 'app-planos-list',
  standalone: true,
  imports: [
    CommonModule,
    CurrencyPipe,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    MatTooltipModule
  ],
  templateUrl: './planos-list.component.html',
  styleUrl: './planos-list.component.css'
})
export class PlanosListComponent implements OnInit {

  planos: PlanoResponseDTO[] = [];

  carregando = false;
  erro: string | null = null;

  atualizandoStatusId: string | null = null;

  displayedColumns: string[] = [
    'nome',
    'valor',
    'periodicidade',
    'duracaoDias',
    'status',
    'acoes'
  ];

  constructor(
    private planoService: PlanoService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.carregarPlanos();
  }

  carregarPlanos(): void {
    this.carregando = true;
    this.erro = null;

    this.planoService.listarPlanos().subscribe({
      next: (planos) => {
        this.planos = planos;
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar planos:', erro);
        this.planos = [];
        this.erro = 'Não foi possível carregar os planos. Tente novamente em instantes.';
        this.carregando = false;
      }
    });
  }

  abrirNovoPlano(): void {
    this.abrirFormulario(null);
  }

  editarPlano(plano: PlanoResponseDTO): void {
    this.abrirFormulario(plano);
  }

  private abrirFormulario(plano: PlanoResponseDTO | null): void {
    const dialogRef = this.dialog.open<PlanoFormDialogComponent, PlanoFormDialogData, PlanoResponseDTO | undefined>(
      PlanoFormDialogComponent,
      {
        width: '640px',
        maxWidth: '96vw',
        autoFocus: false,
        disableClose: true,
        panelClass: 'dialog-profissional',
        data: { plano }
      }
    );

    dialogRef.afterClosed().subscribe((planoSalvo) => {
      if (!planoSalvo) {
        return;
      }

      this.carregarPlanos();
      this.snackBar.open(
        plano ? 'Plano atualizado com sucesso.' : 'Plano cadastrado com sucesso.',
        'Fechar',
        { duration: 3000 }
      );
    });
  }

  alternarStatus(plano: PlanoResponseDTO): void {
    if (this.atualizandoStatusId) {
      return;
    }

    const request: PlanoRequestDTO = {
      nome: plano.nome,
      valor: plano.valor,
      descricao: plano.descricao,
      periodicidade: plano.periodicidade,
      duracaoDias: plano.duracaoDias,
      ativo: !plano.ativo
    };

    this.atualizandoStatusId = plano.id;

    this.planoService.atualizar(plano.id, request).subscribe({
      next: () => {
        this.atualizandoStatusId = null;
        this.carregarPlanos();
        this.snackBar.open(
          request.ativo ? 'Plano ativado com sucesso.' : 'Plano inativado com sucesso.',
          'Fechar',
          { duration: 3000 }
        );
      },
      error: (erro) => {
        console.error('Erro ao alternar status do plano:', erro);
        this.atualizandoStatusId = null;
        this.snackBar.open('Não foi possível alterar o status do plano.', 'Fechar', { duration: 4000 });
      }
    });
  }

  labelPeriodicidade(periodicidade: Periodicidade): string {
    return LABEL_PERIODICIDADE[periodicidade] ?? periodicidade;
  }

  classeStatus(plano: PlanoResponseDTO): string {
    return plano.ativo ? 'success' : 'neutro';
  }
}
