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
  ],
})
export class ReceitasComponent implements OnInit {

  receitas: ReceitasResponseDTO[] = [];
  receitasFiltrados: ReceitasResponseDTO[] = [];

  filtro = '';

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
        console.error(
          'Erro ao carregar contas a receber:',
          erro
        );

        this.carregando = false;
      },
    });
  }

  filtrarReceitas(): void {
    const termo = this.filtro
      .trim()
      .toLowerCase();

    if (!termo) {
      this.receitasFiltrados = [...this.receitas];
      return;
    }

    this.receitasFiltrados = this.receitas.filter(
      (receita) =>
        receita.categoria
          ?.toLowerCase()
          .includes(termo)
    );
  }

  limparFiltro(): void {
    this.filtro = '';
    this.filtrarReceitas();
  }

  alternarFiltros(): void {
    this.filtrosAbertos = !this.filtrosAbertos;
  }

  valorTotal(): number {
    return this.receitas.reduce(
      (total, receita) =>
        total + (Number(receita.total) || 0),
      0
    );
  }

  totalRecebidos(): number {
    return this.receitas.reduce(
      (total, receita) =>
        total + (Number(receita.recebido) || 0),
      0
    );
  }

  totalPendente(): number {
    return this.receitas.reduce(
      (total, receita) =>
        total + (Number(receita.pendente) || 0),
      0
    );
  }

  get categorias(): string[] {
    return [
      ...new Set(
        this.receitas
          .map((receita) => receita.categoria)
          .filter(
            (categoria): categoria is string =>
              !!categoria
          )
      ),
    ];
  }

  visualizarReceita(
    receita: ReceitasResponseDTO
  ): void {

    console.log(
      'Visualizar receita:',
      receita
    );

    // Futuramente:
    // this.router.navigate([
    //   '/financeiro/contas-receber',
    //   receita.id
    // ]);
  }
}
