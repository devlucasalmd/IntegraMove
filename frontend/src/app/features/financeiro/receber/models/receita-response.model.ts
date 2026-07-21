export interface ReceitaResponseDTO {
  id: string;
  descricao: string;
  categoria: string;
  valor: number;
  dataVencimento: string;
  dataRecebimento: string | null;
  formaRecebimento: string | null;
  status: string;
  cliente: string | null;
  observacoes: string | null;
}
