export type StatusAluno = 'ATIVO' | 'INATIVO';

export type StatusPagamento =
  | 'FEITO'
  | 'EM_ABERTO'
  | 'A_VENCER'
  | 'VENCIDO';

export interface AlunoResumoResponseDTO {
  id: string;
  nome: string;
  nomePlano: string;
  pagamento: StatusPagamento;
  status: StatusAluno;
}
