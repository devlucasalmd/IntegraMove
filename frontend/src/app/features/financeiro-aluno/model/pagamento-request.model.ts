export interface PagamentoRequestDTO {
  planoId: string;
  valor: number;
  dataVencimento: string;
  observacoes?: string | null;
}
