export interface ReceitaRequestDTO {
  descricao: string;
  categoria: string;
  valor: number;
  dataVencimento: string;
  cliente?: string | null;
  observacoes?: string | null;
}


export interface AtualizarReceitaRequestDTO {
  descricao: string;
  categoria: string;
  valor: number;
  dataVencimento: string;
  cliente?: string | null;
  observacoes?: string | null;
}


export interface ReceberReceitaRequestDTO {
  dataRecebimento: string;
  formaRecebimento: string;
}
