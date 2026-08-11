import { Component, OnInit, computed, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';

import { AlunoService } from '../../../alunos/services/aluno.service';
import { PlanoService } from '../../../planos/plano.service';
import { ReceitaService } from '../../../financeiro/receber/service/receita.service';
import { DespesaService } from '../../../financeiro/despesa/service/despesa.service';

import { AlunoResumoResponseDTO } from '../../../alunos/models/aluno-resumo-response.model';
import { PlanoResponseDTO } from '../../../planos/models/plano-response';
import { ReceitasResponseDTO } from '../../../financeiro/receber/models/receita-response.model';
import { DespesaResponseDTO } from '../../../financeiro/despesa/models/despesa-response.model';

import { SummaryCardsComponent, SummaryCardData } from '../../../../shared/components/summary-cards/summary-cards.component';
import { LineChartComponent } from '../../components/line-chart/line-chart.component';

import { AlunoRiscoInadimplencia, AulaAgendadaDia, PeriodoDashboard, PlanoUtilizado, PontoGrafico, ResumoFinanceiroMock, TipoAulaAgendada } from '../../models/dashboard.model';
import {
  AULAS_HOJE_MOCK,
  RESUMO_FINANCEIRO_MOCK,
  descricaoPeriodoEvolucao,
  gerarSerieEvolucaoMock,
} from '../../models/dashboard-mock';
import {
  ALUNOS_MOCK_FALLBACK,
  DESPESAS_MOCK_FALLBACK,
  PLANOS_MOCK_FALLBACK,
  RECEITAS_MOCK_FALLBACK,
} from '../../models/dashboard-mock-fallback';

/**
 * Painel geral (rota "/") — cabeçalho e gráfico de evolução refletem o
 * período selecionado; alunos/planos/receitas/despesas vêm dos services
 * reais (`AlunoService`, `PlanoService`, `ReceitaService`, `DespesaService`)
 * e, se a chamada falhar, caem para um dataset de exemplo local (ver
 * `dashboard-mock-fallback.ts`) para manter a tela navegável. Resumo
 * financeiro do mês e agenda do dia continuam 100% MOCK // TODO: mock —
 * não existem endpoints de fechamento mensal nem de agenda agregada hoje.
 */
@Component({
  selector: 'app-dashboard-page',
  standalone: true,
  templateUrl: './dashboard-page.component.html',
  styleUrl: './dashboard-page.component.css',
  imports: [
    CommonModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatButtonToggleModule,
    SummaryCardsComponent,
    LineChartComponent,
  ],
})
export class DashboardPageComponent implements OnInit {
  /** Data atual exibida no cabeçalho — real, formatada por extenso em pt-BR. */
  readonly dataAtualFormatada = this.formatarDataPorExtenso(new Date());

  readonly periodo = signal<PeriodoDashboard>('mes');

  /**
   * Filtro (multi-seleção) da agenda do dia — "Avaliações" / "Turmas".
   * Nenhum toggle ativo = mostra tudo (inclusive itens `individual`, que não
   * têm toggle correspondente). Com um ou mais toggles ativos, a lista passa
   * a exibir só os tipos selecionados — ou seja, itens `individual` ficam
   * ocultos assim que qualquer filtro é aplicado (comportamento intencional:
   * o pedido cobre somente avaliações e turmas, então "individual" não tem
   * como ser incluído/excluído isoladamente).
   */
  readonly agendaFiltroTipos = signal<TipoAulaAgendada[]>([]);

  alunos: AlunoResumoResponseDTO[] = [];
  planos: PlanoResponseDTO[] = [];
  receitas: ReceitasResponseDTO[] = [];
  despesas: DespesaResponseDTO[] = [];

  carregandoAlunos = true;
  carregandoReceitas = true;
  carregandoDespesas = true;

  usandoDadosExemploAlunos = false;
  usandoDadosExemploFinanceiro = false;

  // ---- Gráfico de evolução (100% mock) ----

  // TODO: mock — série de "novas matrículas" gerada localmente, sem endpoint real por trás.
  readonly serieEvolucao = computed<PontoGrafico[]>(() => gerarSerieEvolucaoMock(this.periodo()));

  readonly descricaoEvolucao = computed(() => descricaoPeriodoEvolucao(this.periodo()));

  constructor(
    private readonly alunoService: AlunoService,
    private readonly planoService: PlanoService,
    private readonly receitaService: ReceitaService,
    private readonly despesaService: DespesaService
  ) {}

  ngOnInit(): void {
    this.carregarAlunos();
    this.carregarPlanos();
    this.carregarReceitas();
    this.carregarDespesas();
  }

  onPeriodoChange(periodo: PeriodoDashboard): void {
    this.periodo.set(periodo);
  }

  onAgendaFiltroChange(tipos: TipoAulaAgendada[]): void {
    this.agendaFiltroTipos.set(tipos);
  }

  // ---- Carregamento (real, com fallback mockado) ----

  private carregarAlunos(): void {
    this.carregandoAlunos = true;

    this.alunoService.listarAlunos().subscribe({
      next: (alunos) => {
        this.alunos = alunos;
        this.carregandoAlunos = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar alunos do dashboard:', erro);
        this.alunos = ALUNOS_MOCK_FALLBACK;
        this.usandoDadosExemploAlunos = true;
        this.carregandoAlunos = false;
      },
    });
  }

  private carregarPlanos(): void {
    this.planoService.listarPlanos().subscribe({
      next: (planos) => {
        this.planos = planos;
      },
      error: (erro) => {
        console.error('Erro ao carregar planos do dashboard:', erro);
        this.planos = PLANOS_MOCK_FALLBACK;
      },
    });
  }

  private carregarReceitas(): void {
    this.carregandoReceitas = true;

    this.receitaService.listarReceitas().subscribe({
      next: (receitas) => {
        this.receitas = receitas;
        this.carregandoReceitas = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar receitas do dashboard:', erro);
        this.receitas = RECEITAS_MOCK_FALLBACK;
        this.usandoDadosExemploFinanceiro = true;
        this.carregandoReceitas = false;
      },
    });
  }

  private carregarDespesas(): void {
    this.carregandoDespesas = true;

    this.despesaService.listarDespesas().subscribe({
      next: (despesas) => {
        this.despesas = despesas;
        this.carregandoDespesas = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar despesas do dashboard:', erro);
        this.despesas = DESPESAS_MOCK_FALLBACK;
        this.usandoDadosExemploFinanceiro = true;
        this.carregandoDespesas = false;
      },
    });
  }

  get carregandoFinanceiro(): boolean {
    return this.carregandoReceitas || this.carregandoDespesas;
  }

  // ---- KPIs de alunos ----

  get totalAtivos(): number {
    return this.alunos.filter((aluno) => aluno.status === 'ATIVO').length;
  }

  get totalInativos(): number {
    return this.alunos.filter((aluno) => aluno.status === 'INATIVO').length;
  }

  get alunosRisco(): AlunoResumoResponseDTO[] {
    return this.alunos.filter((aluno) => aluno.pagamento === 'VENCIDO' || aluno.pagamento === 'A_VENCER');
  }

  /**
   * Alunos inadimplentes — real, calculado a partir de `AlunoService.listarAlunos()`.
   * Critério: somente `pagamento === 'VENCIDO'` (cobrança já vencida e não paga).
   * Diferente de `alunosRisco` (usado no widget "risco de inadimplência"),
   * que também inclui `A_VENCER` (cobranças que ainda vão vencer) — aqui o
   * recorte é mais estrito, adequado ao card "Alunos inadimplentes" do topo.
   */
  get alunosInadimplentes(): AlunoResumoResponseDTO[] {
    return this.alunos.filter((aluno) => aluno.pagamento === 'VENCIDO');
  }

  /**
   * Percentual de presença dos alunos matriculados — 100% MOCK // TODO: mock
   * — não existe hoje endpoint de presença/check-in de aluno no backend
   * (ex.: `GET /alunos/{id}/presencas` ou um agregado
   * `GET /dashboard/presenca`). Gerado de forma determinística a partir do
   * total de alunos ativos (sem `Math.random()`), no mesmo espírito de
   * `gerarDadosMockInadimplencia`.
   */
  get percentualPresencaMock(): number {
    const base = 68;
    const variacao = this.totalAtivos % 25; // 0 a 24 pontos percentuais

    return Math.min(100, base + variacao);
  }

  /**
   * Quantidade de alunos matriculados vindos pelo Wellhub — 100% MOCK //
   * TODO: mock — não existe campo de origem/canal de matrícula (Wellhub) em
   * nenhum DTO de aluno no backend hoje (ex.: campo
   * `origemMatricula`/`canalAquisicao` em `Aluno`/`AlunoResponseDTO`, com um
   * enum incluindo `WELLHUB`). Gerado como uma fração determinística do
   * total de alunos, sem `Math.random()`.
   */
  get totalAlunosWellhubMock(): number {
    return Math.round(this.alunos.length * 0.18);
  }

  /**
   * Base `alunosRisco`, enriquecida com `valorDevido`, `dataVencimento` e
   * `diasAtraso` gerados de forma determinística a partir do id do aluno —
   * não existe hoje um endpoint `GET /alunos/inadimplentes` no backend.
   */
  get alunosRiscoDetalhado(): AlunoRiscoInadimplencia[] {
    return this.alunosRisco
      .map((aluno) => this.gerarDadosMockInadimplencia(aluno))
      .sort((a, b) => b.diasAtraso - a.diasAtraso);
  }

  get planosMaisUtilizados(): PlanoUtilizado[] {
    const contagem = new Map<string, number>();

    for (const aluno of this.alunos) {
      const nome = aluno.nomePlano || 'Sem plano';
      contagem.set(nome, (contagem.get(nome) ?? 0) + 1);
    }

    return Array.from(contagem.entries())
      .map(([nome, totalAlunos]) => ({
        nome,
        totalAlunos,
        valor: this.planos.find((plano) => plano.nome === nome)?.valor,
      }))
      .sort((a, b) => b.totalAlunos - a.totalAlunos);
  }

  get maiorContagemPlano(): number {
    return Math.max(1, ...this.planosMaisUtilizados.map((plano) => plano.totalAlunos));
  }

  /** Soma o `pendente` das receitas com as despesas em aberto/a vencer/vencidas. */
  get totalPendencias(): number {
    const pendenteReceitas = this.receitas.reduce((soma, receita) => soma + (receita.pendente || 0), 0);

    const pendenteDespesas = this.despesas
      .filter((despesa) => despesa.status === 'EM_ABERTO' || despesa.status === 'A_VENCER' || despesa.status === 'VENCIDA')
      .reduce((soma, despesa) => soma + (despesa.valor || 0), 0);

    return pendenteReceitas + pendenteDespesas;
  }

  get resumoAlunosCards(): SummaryCardData[] {
    return [
      {
        // Real — AlunoService.listarAlunos().
        icon: 'groups',
        label: 'Total de alunos',
        value: `${this.alunos.length}`,
        variant: 'total',
      },
      {
        // Real — AlunoService.listarAlunos() (aluno.status === 'ATIVO').
        icon: 'check_circle',
        label: 'Alunos ativos',
        value: `${this.totalAtivos}`,
        variant: 'success',
      },
      {
        // Real — AlunoService.listarAlunos() (aluno.status === 'INATIVO').
        icon: 'person_off',
        label: 'Alunos inativos',
        value: `${this.totalInativos}`,
        variant: 'warning',
      },
      {
        // Real — AlunoService.listarAlunos() (aluno.pagamento === 'VENCIDO').
        icon: 'report_problem',
        label: 'Alunos inadimplentes',
        value: `${this.alunosInadimplentes.length}`,
        variant: 'danger',
      },
      {
        // TODO: mock — sem endpoint de presença/check-in de aluno no backend hoje.
        icon: 'fact_check',
        label: 'Presença de alunos matriculados',
        value: `${this.percentualPresencaMock}%`,
        variant: 'success',
      },
      {
        // TODO: mock — sem campo de origem/canal de matrícula (Wellhub) em nenhum DTO de aluno hoje.
        icon: 'hub',
        label: 'Alunos vindos pelo Wellhub',
        value: `${this.totalAlunosWellhubMock}`,
        variant: 'total',
      },
    ];
  }

  // TODO: mock — resumo financeiro do mês ainda não vem de um endpoint real (sem corte mensal no backend).
  get resumoFinanceiroMock(): ResumoFinanceiroMock {
    return RESUMO_FINANCEIRO_MOCK;
  }

  // TODO: mock — agenda do dia ainda não vem de um endpoint real (sem `AgendaService` no backend).
  get aulasHojeMock(): AulaAgendadaDia[] {
    return AULAS_HOJE_MOCK;
  }

  /** Agenda do dia após aplicar o filtro `agendaFiltroTipos` (ver comentário no signal). */
  get aulasHojeFiltradas(): AulaAgendadaDia[] {
    const filtros = this.agendaFiltroTipos();

    if (filtros.length === 0) {
      return this.aulasHojeMock;
    }

    return this.aulasHojeMock.filter((aula) => filtros.includes(aula.tipo));
  }

  // ---- Helpers mock (inadimplência) ----

  private gerarDadosMockInadimplencia(aluno: AlunoResumoResponseDTO): AlunoRiscoInadimplencia {
    const hash = this.hashSimples(aluno.id);

    const valorDevido = 80 + (hash % 271); // entre R$80 e R$350

    // VENCIDO: 1 a 30 dias de atraso (positivo). A_VENCER: vence em 1 a 15 dias (negativo).
    const diasAtraso = aluno.pagamento === 'VENCIDO' ? 1 + (hash % 30) : -(1 + (hash % 15));

    const dataVencimento = this.calcularDataVencimento(diasAtraso);

    return {
      id: aluno.id,
      nome: aluno.nome,
      nomePlano: aluno.nomePlano,
      pagamento: aluno.pagamento,
      status: aluno.status,
      valorDevido,
      dataVencimento,
      diasAtraso,
    };
  }

  private hashSimples(texto: string): number {
    let hash = 0;

    for (let i = 0; i < texto.length; i++) {
      hash = (hash * 31 + texto.charCodeAt(i)) >>> 0;
    }

    return hash;
  }

  private calcularDataVencimento(diasAtraso: number): string {
    const data = new Date();
    data.setDate(data.getDate() - diasAtraso);

    return data.toISOString().slice(0, 10);
  }

  private formatarDataPorExtenso(data: Date): string {
    const formatado = data.toLocaleDateString('pt-BR', {
      weekday: 'long',
      day: 'numeric',
      month: 'long',
      year: 'numeric',
    });

    return formatado.charAt(0).toUpperCase() + formatado.slice(1);
  }

  formatarMoeda(valor: number): string {
    return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
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

  inicialNome(nome: string): string {
    return nome?.charAt(0).toUpperCase() || '?';
  }

  iconePorTipoAula(tipo: AulaAgendadaDia['tipo']): string {
    const icones: Record<AulaAgendadaDia['tipo'], string> = {
      turma: 'groups',
      individual: 'person',
      avaliacao: 'monitor_weight',
    };

    return icones[tipo] || 'event';
  }
}
