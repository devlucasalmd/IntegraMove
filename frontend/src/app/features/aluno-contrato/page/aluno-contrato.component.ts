import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTooltipModule } from '@angular/material/tooltip';

import { ContratoService } from '../service/contrato.service';
import { ContratoResponseDTO, StatusContrato } from '../model/contrato.model';
import { PlanoService } from '../../planos/plano.service';
import { PlanoResponseDTO } from '../../planos/models/plano-response';

const LABEL_STATUS_CONTRATO: Record<StatusContrato, string> = {
  ATIVO: 'Ativo',
  ENCERRADO: 'Encerrado',
  CANCELADO: 'Cancelado'
};

/**
 * Aba "Contratos" do perfil do aluno.
 *
 * Lista os contratos do aluno (`GET /contratos/aluno/{alunoId}`).
 * Não há criação manual de contrato aqui — um contrato só é gerado
 * indiretamente ao registrar uma Venda do tipo PLANO (aba Vendas).
 */
@Component({
  selector: 'app-aluno-contrato',
  standalone: true,
  imports: [
    CommonModule,
    DatePipe,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatTooltipModule
  ],
  templateUrl: './aluno-contrato.component.html',
  styleUrl: './aluno-contrato.component.css'
})
export class AlunoContratoComponent implements OnInit {

  alunoId!: string;

  contratos: ContratoResponseDTO[] = [];
  planosPorId: Record<string, PlanoResponseDTO> = {};

  carregando = false;
  erro: string | null = null;

  displayedColumns: string[] = [
    'plano',
    'vigencia',
    'diaVencimento',
    'renovacaoAutomatica',
    'status',
    'documento'
  ];

  constructor(
    private route: ActivatedRoute,
    private contratoService: ContratoService,
    private planoService: PlanoService
  ) {}

  ngOnInit(): void {
    this.alunoId = this.route.parent?.snapshot.paramMap.get('alunoId') ?? '';

    if (!this.alunoId) {
      this.alunoId = this.route.snapshot.paramMap.get('alunoId') ?? '';
    }

    this.carregarPlanos();
    this.carregarContratos();
  }

  carregarPlanos(): void {
    this.planoService.listarPlanos().subscribe({
      next: (planos) => {
        this.planosPorId = planos.reduce((mapa, plano) => {
          mapa[plano.id] = plano;
          return mapa;
        }, {} as Record<string, PlanoResponseDTO>);
      },
      error: (erro) => {
        console.error('Erro ao carregar planos:', erro);
      }
    });
  }

  carregarContratos(): void {
    this.carregando = true;
    this.erro = null;

    this.contratoService.listarPorAluno(this.alunoId).subscribe({
      next: (contratos) => {
        this.contratos = contratos;
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar contratos do aluno:', erro);
        this.contratos = [];
        this.erro = 'Não foi possível carregar os contratos deste aluno. Tente novamente em instantes.';
        this.carregando = false;
      }
    });
  }

  nomePlano(contrato: ContratoResponseDTO): string {
    return this.planosPorId[contrato.planoId]?.nome ?? 'Plano removido';
  }

  /** Plano DIARIA não tem "dia do mês" de vencimento — cada dia de vigência é sua própria cobrança. */
  ehPlanoDiario(contrato: ContratoResponseDTO): boolean {
    return this.planosPorId[contrato.planoId]?.periodicidade === 'DIARIA';
  }

  labelStatusContrato(status: StatusContrato): string {
    return LABEL_STATUS_CONTRATO[status] ?? status;
  }

  classeStatusContrato(status: StatusContrato): string {
    if (status === 'ATIVO') return 'success';
    if (status === 'CANCELADO') return 'danger';
    return 'neutro';
  }

  abrirDocumento(contrato: ContratoResponseDTO): void {
    if (!contrato.documentoUrl) {
      return;
    }

    window.open(contrato.documentoUrl, '_blank', 'noopener');
  }
}
