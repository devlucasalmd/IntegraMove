export type StatusFinanceiro = 'PENDENTE' | 'PAGO' | 'ATRASADO' | 'CANCELADO';
export type FormaPagamento = 'DEBITO' | 'CREDITO' | 'PIX' | 'DINHEIRO' | 'BOLETO';

export interface PagamentoResponseDTO {
  id: string;
  alunoId: string;
  vendaId: string;
  contratoId: string;
  valor: number;
  numeroParcela: number;
  totalParcelas: number;
  dataVencimento: string;
  dataPagamento: string | null;
  status: StatusFinanceiro;
  formaPagamento: FormaPagamento | null;
}
