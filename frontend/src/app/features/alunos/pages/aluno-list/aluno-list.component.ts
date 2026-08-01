import { MatSpinner } from '@angular/material/progress-spinner';
import { Component, OnInit } from '@angular/core';
import { AlunoService } from '../../services/aluno.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatMenuModule } from '@angular/material/menu';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDividerModule } from '@angular/material/divider';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { AlunoResumoResponseDTO } from '../../models/aluno-resumo-response.model';
import { SummaryCardsComponent, SummaryCardData } from '../../../../shared/components/summary-cards/summary-cards.component'
import { MatOption } from '@angular/material/select';
@Component({
  selector: 'app-aluno-list',
  standalone: true,
  templateUrl: './aluno-list.component.html',
  styleUrls: ['./aluno-list.component.css'],
  imports: [
    CommonModule,
    RouterModule,
    MatButtonModule,
    MatTableModule,
    MatCardModule,
    MatIconModule,
    MatMenuModule,
    MatDividerModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatOption,
    MatSpinner,
    SummaryCardsComponent
  ],
})

export class AlunoListComponent implements OnInit {
  alunos: AlunoResumoResponseDTO[] = [];
  alunosFiltrados: AlunoResumoResponseDTO[] = [];

  filtro: string = '';
  carregando = false;

  colunas: string[] = ['aluno', 'plano', 'pagamento', 'status', 'acoes'];

  filtrosAbertos = false;
  filtroPlano = '';
  filtroPagamento = '';
  filtroStatus = '';

  readonly planos: string[] = ['Mensal', 'Trimestral', 'Anual'];

  constructor(private alunoService: AlunoService) {}

  ngOnInit(): void {
    this.carregarAlunos();
  }

  carregarAlunos(): void {
    this.carregando = true;

    this.alunoService.listarAlunos().subscribe({
      next: (response) => {
        this.alunos = response;
        this.alunosFiltrados = response;

        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao listar alunos:', erro);
        this.carregando = false;
      },
    });
  }

  filtrarAlunos(): void {
    const termo = this.filtro.trim().toLowerCase();

    this.alunosFiltrados = this.alunos.filter((aluno) => {
      const bateTexto =
        !termo ||
        aluno.nome.toLowerCase().includes(termo) ||
        aluno.nomePlano?.toLowerCase().includes(termo) ||
        aluno.pagamento?.toLowerCase().includes(termo) ||
        aluno.status?.toLowerCase().includes(termo);

      const batePlano = !this.filtroPlano || aluno.nomePlano === this.filtroPlano;
      const batePagamento = !this.filtroPagamento || aluno.pagamento === this.filtroPagamento;
      const bateStatus = !this.filtroStatus || aluno.status === this.filtroStatus;

      return bateTexto && batePlano && batePagamento && bateStatus;
    });
  }

  limparFiltro(): void {
    this.filtro = '';
    this.filtrarAlunos();
  }

  alternarFiltros(): void {
    this.filtrosAbertos = !this.filtrosAbertos;
  }

  limparTodosFiltros(): void {
    this.filtro = '';
    this.filtroPlano = '';
    this.filtroPagamento = '';
    this.filtroStatus = '';
    this.filtrarAlunos();
  }

  formatarPagamento(pagamento: string): string {
    const labels: Record<string, string> = {
      FEITO: 'Feito',
      EM_ABERTO: 'Em aberto',
      A_VENCER: 'A vencer',
      VENCIDO: 'Vencido',
    };

    return labels[pagamento] || pagamento;
  }

  formatarStatus(status: string): string {
    const labels: Record<string, string> = {
      ATIVO: 'Ativo',
      INATIVO: 'Inativo',
    };

    return labels[status] || status;
  }

  inicialNome(nome: string): string {
    return nome?.charAt(0).toUpperCase() || '?';
  }

  totalAtivos(): number {
    return this.alunos.filter((aluno) => aluno.status === 'ATIVO').length;
  }

  totalInativos(): number {
    return this.alunos.filter((aluno) => aluno.status === 'INATIVO').length;
  }

  totalPendentes(): number {
    return this.alunos.filter(
      (aluno) => aluno.pagamento === 'EM_ABERTO' || aluno.pagamento === 'A_VENCER'
    ).length;
  }

  totalVencidos(): number {
    return this.alunos.filter((aluno) => aluno.pagamento === 'VENCIDO').length;
  }

  get resumoAlunos(): SummaryCardData[] {
    return [
      { icon: 'groups', label: 'Total de alunos', value: `${this.alunos.length}`, variant: 'total' },
      { icon: 'check_circle', label: 'Ativos', value: `${this.totalAtivos()}`, variant: 'success' },
      { icon: 'schedule', label: 'Pagamento pendente', value: `${this.totalPendentes()}`, variant: 'warning' },
      { icon: 'error_outline', label: 'Pagamento vencido', value: `${this.totalVencidos()}`, variant: 'danger' },
    ];
  }
}
