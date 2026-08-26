import { StatusAluno } from '../../alunos/models/aluno-resumo-response.model';

/**
 * Payload retornado por `GET /alunos/{alunoId}/resumo`.
 *
 * `contratoAtivo` e `proximaParcelaPendente` são `null` quando o aluno não
 * possui contrato ativo / parcela em aberto — trate como estado válido, não erro.
 */
export type StatusContrato = 'ATIVO' | 'ENCERRADO' | 'CANCELADO';
export type TipoVenda = 'PLANO' | 'AVALIACAO' | 'DIARIA' | 'PRODUTO';
export type StatusVenda = 'PENDENTE' | 'CONCLUIDA' | 'CANCELADA';
export type FormaPagamento = 'DEBITO' | 'CREDITO' | 'PIX' | 'DINHEIRO' | 'BOLETO';

export type Periodicidade = 'DIARIA' | 'MENSAL' | 'TRIMESTRAL' | 'SEMESTRAL' | 'ANUAL';

export interface ContratoResumoDTO {
  contratoId: string;
  planoId: string;
  nomePlano: string | null;
  periodicidadePlano: Periodicidade | null;
  dataInicio: string;
  dataFim: string;
  diaVencimento: number | null;
  permiteRenovacaoAutomatica: boolean;
  status: StatusContrato;
}

export interface ProximaParcelaResumoDTO {
  financeiroId: string;
  dataVencimento: string;
  valor: number;
}

export interface PagamentoResumoDTO {
  financeiroId: string;
  dataPagamento: string;
  valor: number;
  formaPagamento: FormaPagamento;
}

export interface VendaResumoDTO {
  vendaId: string;
  tipo: TipoVenda;
  descricao: string;
  valor: number;
  dataVenda: string;
  status: StatusVenda;
}

export interface ResumoAlunoResponseDTO {
  alunoId: string;
  nome: string;
  cpf: string;
  telefone: string;
  email: string;
  statusAluno: StatusAluno;
  planoAtualId: string | null;
  nomePlanoAtual: string | null;
  contratoAtivo: ContratoResumoDTO | null;
  proximaParcelaPendente: ProximaParcelaResumoDTO | null;
  quantidadeParcelasEmAtraso: number;
  ultimosPagamentos: PagamentoResumoDTO[];
  vendasRecentes: VendaResumoDTO[];
}
