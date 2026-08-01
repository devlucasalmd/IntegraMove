import { CommonModule, CurrencyPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { Router } from '@angular/router';

import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule, MatSpinner } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';

import { ReceitaService } from '../service/receita.service';
import { ReceitasResponseDTO } from '../models/receita-response.model';
import { MatMenuModule } from '@angular/material/menu';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatTooltipModule } from '@angular/material/tooltip';
import { SummaryCardData, SummaryCardsComponent } from '../../../../shared/components/summary-cards/summary-cards.component';

@Component({
  selector: 'app-receitas-list',
  standalone: true,
  templateUrl: './receitas.component.html',
  styleUrl: './receitas.component.css',
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatTableModule,
    MatCardModule,
    MatIconModule,
    MatMenuModule,
    MatDialogModule,
    MatDividerModule,
    MatSpinner,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatTooltipModule,
    SummaryCardsComponent,
  ],
})
export class ReceitasComponent implements OnInit {

  receitas: ReceitasResponseDTO[] = [];
  receitasFiltrados: ReceitasResponseDTO[] = [];

  filtro = '';
  filtroCategoria = '';

  filtrosAbertos = false;
  carregando = false;

  colunas: string[] = [
    'categoria',
    'total',
    'recebido',
    'pendente',
  ];

  constructor(
    private readonly receitaService: ReceitaService,
    private readonly dialog: MatDialog,
  ) {}

  ngOnInit(): void {
    this.carregarReceitas();
  }

  carregarReceitas(): void {
    this.carregando = true;

    this.receitaService.listarReceitas().subscribe({
      next: (receitas) => {
        this.receitas = receitas;
        this.receitasFiltrados = [...receitas];

        this.carregando = false;
      },

      error: (erro) => {
        console.error('Erro ao carregar contas a receber:', erro);

        this.carregando = false;
      },
    });
  }

  filtrarReceitas(): void {
    const termo = this.filtro.trim().toLowerCase();

    this.receitasFiltrados = this.receitas.filter((receita) => {
      const bateTexto = !termo || receita.categoria?.toLowerCase().includes(termo);
      const bateCategoria = !this.filtroCategoria || receita.categoria === this.filtroCategoria;

      return bateTexto && bateCategoria;
    });
  }

  limparFiltro(): void {
    this.filtro = '';
    this.filtrarReceitas();
  }

  alternarFiltros(): void {
    this.filtrosAbertos = !this.filtrosAbertos;
  }

  limparTodosFiltros(): void {
    this.filtro = '';
    this.filtroCategoria = '';
    this.filtrarReceitas();
  }

  abrirNovo(): void {
    console.log('Abrir formulário de nova receita');

    // Futuramente:
    // this.router.navigate(['/financeiro/contas-receber/novo']);
  }

  valorTotal(): number {
    return this.receitas.reduce(
      (total, receita) => total + (Number(receita.total) || 0),
      0
    );
  }

  totalRecebidos(): number {
    return this.receitas.reduce(
      (total, receita) => total + (Number(receita.recebido) || 0),
      0
    );
  }

  totalPendente(): number {
    return this.receitas.reduce(
      (total, receita) => total + (Number(receita.pendente) || 0),
      0
    );
  }

  get categorias(): string[] {
    return [
      ...new Set(
        this.receitas
          .map((receita) => receita.categoria)
          .filter((categoria): categoria is string => !!categoria)
      ),
    ];
  }

  get resumoReceitas(): SummaryCardData[] {
    return [
      { icon: 'account_balance', label: 'Total', value: this.formatarMoeda(this.valorTotal()), variant: 'total' },
      { icon: 'check_circle', label: 'Recebido', value: this.formatarMoeda(this.totalRecebidos()), variant: 'success' },
      { icon: 'schedule', label: 'Pendente', value: this.formatarMoeda(this.totalPendente()), variant: 'warning' },
    ];
  }

  private formatarMoeda(valor: number): string {
    return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }

  visualizarReceita(receita: ReceitasResponseDTO): void {
    console.log('Visualizar receita:', receita);

    // Futuramente:
    // this.router.navigate(['/financeiro/contas-receber', receita.id]);
  }
}
