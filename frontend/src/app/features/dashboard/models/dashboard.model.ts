/**
 * Período usado para filtrar o dashboard (cabeçalho: hoje / semana / mês / ano).
 *
 * Nenhum endpoint hoje suporta corte por período — o filtro serve apenas para
 * regenerar a série mockada do gráfico de evolução (ver `dashboard-mock.ts`).
 */
export type PeriodoDashboard = 'hoje' | 'semana' | 'mes' | 'ano';

/**
 * Ponto de dado genérico usado pelo `LineChartComponent` (`{ label, value }`).
 */
export interface PontoGrafico {
  label: string;
  value: number;
}

/**
 * Agregado local de "plano mais utilizado", derivado a partir da lista real
 * de alunos (`AlunoService.listarAlunos()`) enriquecida com o valor de cada
 * plano (`PlanoService.listarPlanos()`). Quando existir um endpoint de
 * estatística de uso de plano no backend, este agregado passa a ser
 * calculado a partir da resposta real em vez de cruzar os dois services.
 */
export interface PlanoUtilizado {
  nome: string;
  totalAlunos: number;
  valor?: number;
}

/**
 * Aluno com risco de inadimplência exibido no card do dashboard. Toda a
 * lista de alunos do dashboard é 100% MOCK // TODO: mock (ver
 * `dashboard-mock.ts`) — este tipo enriquece um aluno mock (filtrado por
 * `pagamento === 'VENCIDO' || pagamento === 'A_VENCER'`) com `valorDevido`,
 * `dataVencimento` e `diasAtraso`, gerados de forma determinística a partir
 * do id do aluno. Quando existir um endpoint `GET /alunos/inadimplentes` no
 * backend, todo este fluxo passa a vir da API.
 */
export interface AlunoRiscoInadimplencia {
  id: string;
  nome: string;
  nomePlano: string;
  pagamento: string;
  status: string;
  valorDevido: number;
  dataVencimento: string;
  diasAtraso: number;
}

/** Tipo de aula/atendimento exibido no card "Aulas e treinos de hoje" (100% mock). */
export type TipoAulaAgendada = 'turma' | 'individual' | 'avaliacao';

/**
 * Item da agenda do dia exibido no dashboard — 100% MOCK // TODO: mock. Não
 * existe hoje um endpoint de agenda "do dia" agregada; quando existir, este
 * card passa a consumi-lo em vez de `AULAS_HOJE_MOCK`.
 */
export interface AulaAgendadaDia {
  id: string;
  titulo: string;
  horario: string;
  instrutor: string;
  local: string;
  tipo: TipoAulaAgendada;
  aluno?: string;
}

/**
 * Resumo financeiro do mês exibido no card "Resumo financeiro do mês" —
 * 100% MOCK // TODO: mock. Quando existir um endpoint de fechamento mensal
 * no backend, este card passa a consumi-lo em vez de `RESUMO_FINANCEIRO_MOCK`.
 */
export interface ResumoFinanceiroMock {
  receitaMes: number;
  despesaMes: number;
  variacaoPercentual: number;
}
