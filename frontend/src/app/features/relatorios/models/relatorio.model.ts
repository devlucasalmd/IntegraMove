export interface KpiCard {
  label: string;
  value: string;
  icon: string;
  trend: number;
  trendLabel: string;
}

export interface TransacaoFinanceira {
  data: Date;
  aluno: string;
  descricao: string;
  valor: number;
  status: 'pago' | 'pendente' | 'atrasado';
}

export interface FrequenciaAluno {
  aluno: string;
  plano: string;
  checkinsMes: number;
  ultimoCheckin: Date;
  situacao: 'ativo' | 'inativo' | 'risco';
}

export interface AvaliacaoFisica {
  aluno: string;
  data: Date;
  peso: number;
  percentualGordura: number;
  responsavel: string;
}

export type PeriodoFiltro = '7d' | '30d' | '90d' | 'personalizado';
