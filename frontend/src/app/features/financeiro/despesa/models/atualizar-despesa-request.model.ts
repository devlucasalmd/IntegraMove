export interface AtualizarDespesaRequestDTO {
  descricao: string;
  categoria: string;
  valor: number;
  dataVencimento: string;
  fornecedor?: string | null;
  observacoes?: string | null;
}
