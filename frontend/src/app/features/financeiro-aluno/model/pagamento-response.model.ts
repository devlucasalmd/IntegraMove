export interface PagamentoResponseDTO {
  id: string;
  alunoId: string;
  planoId: string;
  valor: number;
  dataVencimento: string;
  dataPagamento: string | null;
  formaPagamento: string | null;
  status: string;
  observacoes: string | null;
}
