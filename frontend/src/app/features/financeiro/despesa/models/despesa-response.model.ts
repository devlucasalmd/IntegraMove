export interface DespesaResponseDTO {
  id: string;
  descricao: string;
  categoria: string;
  valor: number;
  dataVencimento: string;
  dataPagamento: string | null;
  formaPagamento: string | null;
  status: string;
  fornecedor: string | null;
  observacoes: string | null;
}
