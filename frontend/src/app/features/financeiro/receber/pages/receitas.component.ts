import { ReceitaService } from '../service/receita.service';
import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';

import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { ReceitaResponseDTO } from '../models/receita-response.model';
import { ReceitaRequestDTO } from '../models/receita-request.model';
import { ReceitaDialogComponent } from './receita-dialog/receita-dialog.component';

@Component({
  selector: 'app-contas-receber',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    CurrencyPipe,
    DatePipe,
    MatButtonModule,
    MatCardModule,
    MatChipsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatMenuModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatSnackBarModule,
    MatTableModule,
  ],
  templateUrl: './receitas.component.html',
  styleUrl: './receitas.component.css',
})
export class ReceitasComponent implements OnInit {
  receitas: ReceitaResponseDTO[] = [];
  receitasFiltradas: ReceitaResponseDTO[] = [];

  carregando = false;
  exibindoFormulario = false;

  receitaEditando: ReceitaResponseDTO | null = null;

  filtroForm!: FormGroup;
  form!: FormGroup;

  displayedColumns: string[] = [
    'descricao',
    'categoria',
    'cliente',
    'dataVencimento',
    'dataRecebimento',
    'valor',
    'status',
    'acoes',
  ];

  categorias = [
    { value: 'MENSALIDADE', label: 'Mensalidade' },
    { value: 'MATRICULA', label: 'Matrícula' },
    { value: 'PERSONAL', label: 'Personal Trainer' },
    { value: 'AVALIACAO', label: 'Avaliação Física' },
    { value: 'VENDA_PRODUTO', label: 'Venda de Produto' },
    { value: 'SUPLEMENTO', label: 'Suplemento' },
    { value: 'ACESSORIO', label: 'Acessório' },
    { value: 'OUTROS', label: 'Outros' },
  ];

  statusOptions = [
    { value: 'A_VENCER', label: 'A vencer' },
    { value: 'EM_ABERTO', label: 'Em aberto' },
    { value: 'VENCIDA', label: 'Vencida' },
    { value: 'PAGA', label: 'Recebida' },
    { value: 'CANCELADA', label: 'Cancelada' },
  ];

  formasRecebimento = [
    { value: 'PIX', label: 'PIX' },
    { value: 'DINHEIRO', label: 'Dinheiro' },
    { value: 'CREDITO', label: 'Cartão de Crédito' },
    { value: 'DEBITO', label: 'Cartão de Débito' },
    { value: 'BOLETO', label: 'Boleto' },
  ];

  constructor(
    private fb: FormBuilder,
    private dialog: MatDialog,
    private receitaService: ReceitaService,
    private snackBar: MatSnackBar,
  ) {}

  ngOnInit(): void {
    this.criarFormularios();
    this.carregarReceitas();
  }

  criarFormularios(): void {

  this.filtroForm = this.fb.group({
    termo: [''],
    status: [''],
    categoria: ['']
  });

}
  carregarReceitas(): void {
    this.carregando = true;

    this.receitaService.listar().subscribe({
      next: (response) => {
        this.receitas = response;
        this.receitasFiltradas = response;

        this.aplicarFiltros();

        this.carregando = false;
      },

      error: (erro) => {
        console.error('Erro ao carregar receitas:', erro);

        this.snackBar.open('Erro ao carregar contas a receber.', 'Fechar', {
          duration: 3000,
        });

        this.carregando = false;
      },
    });
  }

  aplicarFiltros(): void {
    const termo = this.filtroForm.get('termo')?.value?.toLowerCase() || '';

    const status = this.filtroForm.get('status')?.value || '';

    const categoria = this.filtroForm.get('categoria')?.value || '';

    this.receitasFiltradas = this.receitas.filter((receita) => {
      const textoBusca = `
        ${receita.descricao || ''}
        ${receita.cliente || ''}
        ${receita.observacoes || ''}
      `.toLowerCase();

      const combinaTermo = !termo || textoBusca.includes(termo);

      const combinaStatus = !status || receita.status === status;

      const combinaCategoria = !categoria || receita.categoria === categoria;

      return combinaTermo && combinaStatus && combinaCategoria;
    });
  }

  abrirNovo(): void {
    const dialogRef = this.dialog.open(ReceitaDialogComponent, {
      width: '850px',
      maxWidth: '95vw',
    });

    dialogRef.afterClosed().subscribe((resultado) => {
      if (resultado) {
        this.carregarReceitas();

        this.snackBar.open('Receita cadastrada com sucesso.', 'Fechar', {
          duration: 3000,
        });
      }
    });
  }

  editar(receita: ReceitaResponseDTO): void {
    const dialogRef = this.dialog.open(ReceitaDialogComponent, {
      width: '850px',

      data: {
        receita,
      },
    });

    dialogRef.afterClosed().subscribe((resultado) => {
      if (resultado) {
        this.carregarReceitas();
      }
    });
  }

  fecharFormulario(): void {
    this.exibindoFormulario = false;
    this.receitaEditando = null;
    this.form.reset();
  }

  salvar(): void {
    const payload: ReceitaRequestDTO = {
      descricao: this.form.value.descricao,
      categoria: this.form.value.categoria,
      valor: Number(this.form.value.valor),
      dataVencimento: this.form.value.dataVencimento,
      cliente: this.form.value.cliente,
      observacoes: this.form.value.observacoes,
    };

    if (this.receitaEditando) {
      this.receitaService
        .atualizar(this.receitaEditando.id, payload)
        .subscribe({
          next: () => {
            this.snackBar.open('Receita atualizada com sucesso.', 'Fechar', {
              duration: 3000,
            });

            this.fecharFormulario();
            this.carregarReceitas();
          },

          error: (erro) => {
            console.error('Erro ao atualizar receita:', erro);

            this.snackBar.open('Erro ao atualizar receita.', 'Fechar', {
              duration: 3000,
            });
          },
        });

      return;
    }

    this.receitaService.criar(payload).subscribe({
      next: () => {
        this.snackBar.open('Receita cadastrada com sucesso.', 'Fechar', {
          duration: 3000,
        });

        this.fecharFormulario();
        this.carregarReceitas();
      },

      error: (erro) => {
        console.error('Erro ao cadastrar receita:', erro);

        this.snackBar.open('Erro ao cadastrar receita.', 'Fechar', {
          duration: 3000,
        });
      },
    });
  }

  receber(receita: ReceitaResponseDTO): void {
    const hoje = new Date().toISOString().substring(0, 10);

    this.receitaService
      .receber(receita.id, {
        dataRecebimento: hoje,
        formaRecebimento: 'PIX',
      })
      .subscribe({
        next: () => {
          this.snackBar.open('Receita marcada como recebida.', 'Fechar', {
            duration: 3000,
          });

          this.carregarReceitas();
        },

        error: (erro) => {
          console.error('Erro ao receber receita:', erro);

          this.snackBar.open('Erro ao registrar recebimento.', 'Fechar', {
            duration: 3000,
          });
        },
      });
  }

  cancelar(receita: ReceitaResponseDTO): void {
    this.receitaService.cancelar(receita.id).subscribe({
      next: () => {
        this.snackBar.open('Receita cancelada com sucesso.', 'Fechar', {
          duration: 3000,
        });

        this.carregarReceitas();
      },

      error: (erro) => {
        console.error('Erro ao cancelar receita:', erro);

        this.snackBar.open('Erro ao cancelar receita.', 'Fechar', {
          duration: 3000,
        });
      },
    });
  }
  limparFiltros(): void {
    this.filtroForm.reset({
      termo: '',
      status: '',
      categoria: '',
    });
  }

  get totalReceitas(): number {
    return this.receitas
      .filter((receita) => receita.status !== 'CANCELADA')
      .reduce((total, receita) => total + receita.valor, 0);
  }

  get totalRecebido(): number {
    return this.receitas
      .filter((receita) => receita.status === 'PAGA')
      .reduce((total, receita) => total + receita.valor, 0);
  }

  get totalPendente(): number {
    return this.receitas
      .filter(
        (receita) =>
          receita.status === 'A_VENCER' || receita.status === 'EM_ABERTO',
      )
      .reduce((total, receita) => total + receita.valor, 0);
  }

  get totalVencido(): number {
    return this.receitas
      .filter((receita) => receita.status === 'VENCIDA')
      .reduce((total, receita) => total + receita.valor, 0);
  }

  getStatusLabel(status: string): string {
    const labels: Record<string, string> = {
      A_VENCER: 'A vencer',
      EM_ABERTO: 'Em aberto',
      VENCIDA: 'Vencida',
      PAGA: 'Recebida',
      CANCELADA: 'Cancelada',
    };

    return labels[status] ?? status;
  }

  getCategoriaLabel(categoria: string): string {
    const categoriaEncontrada = this.categorias.find(
      (item) => item.value === categoria,
    );

    return categoriaEncontrada?.label ?? categoria;
  }

  getStatusClass(status: string): string {
    const classes: Record<string, string> = {
      A_VENCER: 'status-a-vencer',
      EM_ABERTO: 'status-em-aberto',
      VENCIDA: 'status-vencida',
      PAGA: 'status-paga',
      CANCELADA: 'status-cancelada',
    };

    return classes[status] ?? '';
  }
}
