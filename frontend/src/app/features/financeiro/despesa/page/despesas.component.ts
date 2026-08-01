import { CommonModule, CurrencyPipe, DatePipe } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatChipsModule } from "@angular/material/chips";
import { MatDialog, MatDialogModule } from "@angular/material/dialog";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatMenuModule } from "@angular/material/menu";
import { MatProgressSpinnerModule, MatSpinner } from "@angular/material/progress-spinner";
import { MatSelectModule } from "@angular/material/select";
import { MatSnackBar, MatSnackBarModule } from "@angular/material/snack-bar";
import { MatTableModule } from "@angular/material/table";
import { DespesaResponseDTO } from "../models/despesa-response.model";
import { DespesaService } from "../service/despesa.service";
import { DespesaDialogComponent } from "./despesas-dialog/despesas-dialog.component";
import { MatDividerModule } from "@angular/material/divider";
import { SummaryCardsComponent, SummaryCardData } from '../../../../shared/components/summary-cards/summary-cards.component';

import { MatTooltipModule } from "@angular/material/tooltip";@Component({
  selector: 'app-despesas-list',
  standalone: true,
  templateUrl: './despesas.component.html',
  styleUrl: './despesas.component.css',
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
    SummaryCardsComponent
  ],
})

export class DespesasComponent implements OnInit {

  despesas: DespesaResponseDTO[] = [];
  despesasFiltrados: DespesaResponseDTO[] = [];

  filtro = '';
  filtroCategoria = '';
  filtroStatus = '';

  filtrosAbertos = false;
  carregando = false;

  colunas: string[] = [
    'descricao',
    'valor',
    'vencimento',
    'status',
    'acoes',
  ];

  constructor(
    private readonly despesaService: DespesaService,
    private readonly dialog: MatDialog,
  ) {}

  ngOnInit(): void {
    this.carregarDespesas();
  }

  carregarDespesas(): void {
    this.carregando = true;

    this.despesaService.listarDespesas().subscribe({
      next: (despesas) => {
        this.despesas = despesas;
        this.despesasFiltrados = [...despesas];

        this.carregando = false;
      },

      error: (erro) => {
        console.error('Erro ao carregar contas a pagar:', erro);

        this.carregando = false;
      },
    });
  }

  filtrarDespesas(): void {
    const termo = this.filtro.trim().toLowerCase();

    this.despesasFiltrados = this.despesas.filter((despesa) => {
      const bateTexto =
        !termo ||
        despesa.descricao?.toLowerCase().includes(termo) ||
        despesa.categoria?.toLowerCase().includes(termo) ||
        despesa.fornecedor?.toLowerCase().includes(termo);

      const bateCategoria = !this.filtroCategoria || despesa.categoria === this.filtroCategoria;
      const bateStatus = !this.filtroStatus || despesa.status === this.filtroStatus;

      return bateTexto && bateCategoria && bateStatus;
    });
  }

  limparFiltro(): void {
    this.filtro = '';
    this.filtrarDespesas();
  }

  alternarFiltros(): void {
    this.filtrosAbertos = !this.filtrosAbertos;
  }

  abrirNovo(): void {
    console.log('Abrir formulário de nova despesa');

    // Futuramente:
    // this.router.navigate(['/financeiro/contas-pagar/novo']);
  }

  limparTodosFiltros(): void {
    this.filtro = '';
    this.filtroCategoria = '';
    this.filtroStatus = '';
    this.filtrarDespesas();
  }

  valorTotal(): number {
    return this.despesas
      .filter((despesa) => despesa.status !== 'CANCELADA')
      .reduce((total, despesa) => total + (Number(despesa.valor) || 0), 0);
  }

  totalPago(): number {
    return this.despesas
      .filter((despesa) => despesa.status === 'PAGA')
      .reduce((total, despesa) => total + (Number(despesa.valor) || 0), 0);
  }

  totalPendente(): number {
    return this.despesas
      .filter((despesa) => despesa.status === 'A_VENCER' || despesa.status === 'EM_ABERTO')
      .reduce((total, despesa) => total + (Number(despesa.valor) || 0), 0);
  }

  totalVencido(): number {
    return this.despesas
      .filter((despesa) => despesa.status === 'VENCIDA')
      .reduce((total, despesa) => total + (Number(despesa.valor) || 0), 0);
  }

  formatarStatus(status: string): string {
    const labels: Record<string, string> = {
      A_VENCER: 'A vencer',
      EM_ABERTO: 'Em aberto',
      VENCIDA: 'Vencida',
      PAGA: 'Paga',
      CANCELADA: 'Cancelada',
    };

    return labels[status] || status;
  }

  get categorias(): string[] {
    return [
      ...new Set(
        this.despesas
          .map((despesa) => despesa.categoria)
          .filter((categoria): categoria is string => !!categoria)
      ),
    ];
  }

  get resumoDespesas(): SummaryCardData[] {
    return [
      { icon: 'account_balance', label: 'Total', value: this.formatarMoeda(this.valorTotal()), variant: 'total' },
      { icon: 'check_circle', label: 'Pago', value: this.formatarMoeda(this.totalPago()), variant: 'success' },
      { icon: 'schedule', label: 'Pendente', value: this.formatarMoeda(this.totalPendente()), variant: 'warning' },
      { icon: 'error_outline', label: 'Vencido', value: this.formatarMoeda(this.totalVencido()), variant: 'danger' },
    ];
  }

  private formatarMoeda(valor: number): string {
    return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }

  visualizarDespesa(despesa: DespesaResponseDTO): void {
    console.log('Visualizar despesa:', despesa);

    // Futuramente:
    // this.router.navigate(['/financeiro/contas-pagar', despesa.id]);
  }
}
 