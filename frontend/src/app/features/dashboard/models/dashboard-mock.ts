import { AulaAgendadaDia, PeriodoDashboard, PontoGrafico, ResumoFinanceiroMock } from './dashboard.model';

/**
 * Série do gráfico de evolução (widget "Novas matrículas") — 100% MOCKADA
 * // TODO: mock. Não existe endpoint de série histórica no backend hoje (ver
 * relatório final da tarefa para o contrato sugerido de
 * `GET /dashboard/evolucao-matriculas?periodo=...`).
 *
 * A granularidade muda de acordo com o período selecionado no cabeçalho:
 * - hoje  -> pontos por hora (janela de funcionamento da academia)
 * - semana -> pontos por dia (segunda a domingo)
 * - mes   -> pontos por semana do mês corrente
 * - ano   -> pontos por mês (janeiro a dezembro)
 *
 * Os valores são gerados de forma determinística (função do índice do ponto,
 * sem `Math.random()`), então o gráfico não "pisca" com valores diferentes a
 * cada render — apenas muda quando o período selecionado muda.
 */

const NOMES_DIAS_SEMANA = ['Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb', 'Dom'];
const NOMES_MESES = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'];
const HORAS_FUNCIONAMENTO = ['06h', '08h', '10h', '12h', '14h', '16h', '18h', '20h', '22h'];

/**
 * Gera um valor inteiro não-negativo, determinístico por índice, combinando
 * uma leve tendência de crescimento com uma oscilação senoidal — resulta em
 * uma linha com "cara" de evolução real, mas 100% reprodutível.
 */
function gerarValorDeterministico(indice: number, base: number, amplitude: number, tendencia: number): number {
  const oscilacao = Math.sin(indice * 0.85) * amplitude;
  const crescimento = indice * tendencia;

  return Math.max(0, Math.round(base + oscilacao + crescimento));
}

function contarSemanasDoMes(dataReferencia: Date): number {
  const ano = dataReferencia.getFullYear();
  const mes = dataReferencia.getMonth();
  const ultimoDia = new Date(ano, mes + 1, 0).getDate();

  return Math.ceil(ultimoDia / 7);
}

// TODO: mock — série 100% inventada, sem endpoint real por trás.
export function gerarSerieEvolucaoMock(periodo: PeriodoDashboard, dataReferencia: Date = new Date()): PontoGrafico[] {
  switch (periodo) {
    case 'hoje':
      return HORAS_FUNCIONAMENTO.map((label, indice) => ({
        label,
        value: gerarValorDeterministico(indice, 2, 2, 0.3),
      }));

    case 'semana':
      return NOMES_DIAS_SEMANA.map((label, indice) => ({
        label,
        value: gerarValorDeterministico(indice, 4, 3, 0.4),
      }));

    case 'ano':
      return NOMES_MESES.map((label, indice) => ({
        label,
        value: gerarValorDeterministico(indice, 20, 10, 2),
      }));

    case 'mes':
    default: {
      const totalSemanas = contarSemanasDoMes(dataReferencia);

      return Array.from({ length: totalSemanas }, (_, indice) => ({
        label: `Sem ${indice + 1}`,
        value: gerarValorDeterministico(indice, 10, 6, 1.5),
      }));
    }
  }
}

/** Título/subtítulo do card do gráfico, de acordo com o período selecionado. */
export function descricaoPeriodoEvolucao(periodo: PeriodoDashboard): string {
  const descricoes: Record<PeriodoDashboard, string> = {
    hoje: 'Novas matrículas por horário de funcionamento hoje',
    semana: 'Novas matrículas por dia, nos últimos 7 dias',
    mes: 'Novas matrículas por semana, no mês corrente',
    ano: 'Novas matrículas por mês, no ano corrente',
  };

  return descricoes[periodo];
}

/**
 * ---------------------------------------------------------------------
 * Dados permanentemente mockados do restante do dashboard (agenda do dia e
 * resumo financeiro do mês) — TUDO 100% MOCK // TODO: mock, sem chamada de
 * API por trás. Diferente de `dashboard-mock-fallback.ts`, estes dados NÃO
 * são substituídos quando a API responde — não existe hoje um endpoint de
 * agenda agregada nem de fechamento financeiro mensal no backend.
 * ---------------------------------------------------------------------
 */

// TODO: mock — agenda do dia 100% inventada.
export const AULAS_HOJE_MOCK: AulaAgendadaDia[] = [
  { id: 'ag1', titulo: 'Musculação — Turma A', horario: '07:00', instrutor: 'Prof. Rafael', local: 'Sala 1', tipo: 'turma' },
  {
    id: 'ag2',
    titulo: 'Avaliação física',
    horario: '09:00',
    instrutor: 'Prof.ª Aline',
    local: 'Sala de avaliação',
    tipo: 'avaliacao',
    aluno: 'Diego Almeida Souza',
  },
  {
    id: 'ag3',
    titulo: 'Personal training',
    horario: '10:30',
    instrutor: 'Prof. Rafael',
    local: 'Sala 2',
    tipo: 'individual',
    aluno: 'Camila Rodrigues Santos',
  },
  { id: 'ag4', titulo: 'Treino funcional — Turma B', horario: '18:00', instrutor: 'Prof.ª Juliana', local: 'Área externa', tipo: 'turma' },
  { id: 'ag5', titulo: 'Spinning', horario: '19:30', instrutor: 'Prof. Marcos', local: 'Sala de bike', tipo: 'turma' },
];

// TODO: mock — resumo financeiro do mês 100% inventado (não existe endpoint com corte mensal).
export const RESUMO_FINANCEIRO_MOCK: ResumoFinanceiroMock = {
  receitaMes: 20830,
  despesaMes: 16700,
  variacaoPercentual: 6.5,
};
