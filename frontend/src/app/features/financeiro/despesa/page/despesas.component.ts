import { CommonModule, CurrencyPipe, DatePipe } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatChipsModule } from "@angular/material/chips";
import { MatDialog, MatDialogModule } from "@angular/material/dialog";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatMenuModule } from "@angular/material/menu";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatSelectModule } from "@angular/material/select";
import { MatSnackBar, MatSnackBarModule } from "@angular/material/snack-bar";
import { MatTableModule } from "@angular/material/table";
import { DespesaResponseDTO } from "../models/despesa-response.model";
import { DespesaService } from "../service/despesa.service";
import { DespesaDialogComponent } from "./despesas-dialog/despesas-dialog.component";

@Component({
  selector: 'app-contas-pagar',
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
    MatTableModule
  ],
  templateUrl: './despesas.component.html',
  styleUrl: './despesas.component.css'
})
export class DespesasComponent implements OnInit {

  despesas: DespesaResponseDTO[] = [];
  despesasFiltradas: DespesaResponseDTO[] = [];

  carregando = false;
  exibindoFormulario = false;
  despesaEditando: DespesaResponseDTO | null = null;

  filtroForm!: FormGroup;
  form!: FormGroup;

  displayedColumns: string[] = [
    'descricao',
    'categoria',
    'fornecedor',
    'dataVencimento',
    'dataPagamento',
    'valor',
    'status',
    'acoes'
  ];

  categorias = [
    { value: 'ALUGUEL', label: 'Aluguel' },
    { value: 'LUZ', label: 'Luz' },
    { value: 'AGUA', label: 'Água' },
    { value: 'INTERNET', label: 'Internet' },
    { value: 'SALARIO', label: 'Salário' },
    { value: 'MANUTENCAO', label: 'Manutenção' },
    { value: 'EQUIPAMENTO', label: 'Equipamento' },
    { value: 'MARKETING', label: 'Marketing' },
    { value: 'CONTABILIDADE', label: 'Contabilidade' },
    { value: 'SISTEMA', label: 'Sistema' },
    { value: 'OUTROS', label: 'Outros' }
  ];

  statusOptions = [
    { value: 'A_VENCER', label: 'A vencer' },
    { value: 'EM_ABERTO', label: 'Em aberto' },
    { value: 'VENCIDA', label: 'Vencida' },
    { value: 'PAGA', label: 'Paga' },
    { value: 'CANCELADA', label: 'Cancelada' }
  ];

  formasPagamento = [
    { value: 'PIX', label: 'PIX' },
    { value: 'DINHEIRO', label: 'Dinheiro' },
    { value: 'CREDITO', label: 'Crédito' },
    { value: 'DEBITO', label: 'Débito' },
    { value: 'BOLETO', label: 'Boleto' }
  ];

  constructor(
    private fb: FormBuilder,
    private despesaService: DespesaService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.criarFormularios();
    this.carregarDespesas();
  }

  criarFormularios(): void {
    this.filtroForm = this.fb.group({
      termo: [''],
      status: [''],
      categoria: ['']
    });

    this.form = this.fb.group({
      descricao: [''],
      categoria: [''],
      valor: [null],
      dataVencimento: [''],
      fornecedor: [''],
      observacoes: ['']
    });

    this.filtroForm.valueChanges.subscribe(() => {
      this.aplicarFiltros();
    });
  }

  carregarDespesas(): void {
    this.carregando = true;

    this.despesaService.listar().subscribe({
      next: (response) => {
        this.despesas = response;
        this.despesasFiltradas = response;
        this.aplicarFiltros();
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar despesas:', erro);
        this.snackBar.open('Erro ao carregar contas a pagar.', 'Fechar', {
          duration: 3000
        });
        this.carregando = false;
      }
    });
  }

  aplicarFiltros(): void {
    const termo = this.filtroForm.get('termo')?.value?.toLowerCase() || '';
    const status = this.filtroForm.get('status')?.value || '';
    const categoria = this.filtroForm.get('categoria')?.value || '';

    this.despesasFiltradas = this.despesas.filter(despesa => {
      const textoBusca = `
        ${despesa.descricao || ''}
        ${despesa.fornecedor || ''}
        ${despesa.observacoes || ''}
      `.toLowerCase();

      const combinaTermo = !termo || textoBusca.includes(termo);
      const combinaStatus = !status || despesa.status === status;
      const combinaCategoria = !categoria || despesa.categoria === categoria;

      return combinaTermo && combinaStatus && combinaCategoria;
    });
  }
  abrirNovo(): void {

    const dialogRef = this.dialog.open(DespesaDialogComponent, {
      width: '650px'
    });

    dialogRef.afterClosed().subscribe(resultado => {

      if (resultado) {
        this.carregarDespesas();
      }

    });

  }


  editar(despesa: DespesaResponseDTO): void {

    const dialogRef = this.dialog.open(DespesaDialogComponent, {
      width: '650px',
      data: {
        despesa
      }
    });

    dialogRef.afterClosed().subscribe(resultado => {

      if (resultado) {
        this.carregarDespesas();
      }

    });

  }

  fecharFormulario(): void {
    this.exibindoFormulario = false;
    this.despesaEditando = null;
    this.form.reset();
  }

  salvar(): void {
    const payload = {
      descricao: this.form.value.descricao,
      categoria: this.form.value.categoria,
      valor: Number(this.form.value.valor),
      dataVencimento: this.form.value.dataVencimento,
      fornecedor: this.form.value.fornecedor,
      observacoes: this.form.value.observacoes
    };

    if (this.despesaEditando) {
      this.despesaService.atualizar(this.despesaEditando.id, payload).subscribe({
        next: () => {
          this.snackBar.open('Despesa atualizada com sucesso.', 'Fechar', {
            duration: 3000
          });
          this.fecharFormulario();
          this.carregarDespesas();
        },
        error: (erro) => {
          console.error('Erro ao atualizar despesa:', erro);
          this.snackBar.open('Erro ao atualizar despesa.', 'Fechar', {
            duration: 3000
          });
        }
      });

      return;
    }

    this.despesaService.criar(payload).subscribe({
      next: () => {
        this.snackBar.open('Despesa cadastrada com sucesso.', 'Fechar', {
          duration: 3000
        });
        this.fecharFormulario();
        this.carregarDespesas();
      },
      error: (erro) => {
        console.error('Erro ao cadastrar despesa:', erro);
        this.snackBar.open('Erro ao cadastrar despesa.', 'Fechar', {
          duration: 3000
        });
      }
    });
  }

  pagar(despesa: DespesaResponseDTO): void {
    const hoje = new Date().toISOString().substring(0, 10);

    this.despesaService.pagar(despesa.id, {
      dataPagamento: hoje,
      formaPagamento: 'PIX'
    }).subscribe({
      next: () => {
        this.snackBar.open('Despesa marcada como paga.', 'Fechar', {
          duration: 3000
        });
        this.carregarDespesas();
      },
      error: (erro) => {
        console.error('Erro ao pagar despesa:', erro);
        this.snackBar.open('Erro ao pagar despesa.', 'Fechar', {
          duration: 3000
        });
      }
    });
  }

  cancelar(despesa: DespesaResponseDTO): void {
    this.despesaService.cancelar(despesa.id).subscribe({
      next: () => {
        this.snackBar.open('Despesa cancelada com sucesso.', 'Fechar', {
          duration: 3000
        });
        this.carregarDespesas();
      },
      error: (erro) => {
        console.error('Erro ao cancelar despesa:', erro);
        this.snackBar.open('Erro ao cancelar despesa.', 'Fechar', {
          duration: 3000
        });
      }
    });
  }

  limparFiltros(): void {
    this.filtroForm.reset({
      termo: '',
      status: '',
      categoria: ''
    });
  }

  get totalDespesas(): number {
    return this.despesas
      .filter(despesa => despesa.status !== 'CANCELADA')
      .reduce((total, despesa) => total + despesa.valor, 0);
  }

  get totalPago(): number {
    return this.despesas
      .filter(despesa => despesa.status === 'PAGA')
      .reduce((total, despesa) => total + despesa.valor, 0);
  }

  get totalPendente(): number {
    return this.despesas
      .filter(despesa =>
        despesa.status === 'A_VENCER' ||
        despesa.status === 'EM_ABERTO'
      )
      .reduce((total, despesa) => total + despesa.valor, 0);
  }

  get totalVencido(): number {
    return this.despesas
      .filter(despesa => despesa.status === 'VENCIDA')
      .reduce((total, despesa) => total + despesa.valor, 0);
  }

  getStatusLabel(status: string): string {
    const labels: Record<string, string> = {
      A_VENCER: 'A vencer',
      EM_ABERTO: 'Em aberto',
      VENCIDA: 'Vencida',
      PAGA: 'Paga',
      CANCELADA: 'Cancelada'
    };

    return labels[status] ?? status;
  }

  getCategoriaLabel(categoria: string): string {
    const categoriaEncontrada = this.categorias.find(item => item.value === categoria);
    return categoriaEncontrada?.label ?? categoria;
  }

  getStatusClass(status: string): string {
    const classes: Record<string, string> = {
      A_VENCER: 'status-a-vencer',
      EM_ABERTO: 'status-em-aberto',
      VENCIDA: 'status-vencida',
      PAGA: 'status-paga',
      CANCELADA: 'status-cancelada'
    };

    return classes[status] ?? '';
  }
}
