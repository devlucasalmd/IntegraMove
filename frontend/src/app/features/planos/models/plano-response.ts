export type Periodicidade = 'DIARIA' | 'MENSAL' | 'TRIMESTRAL' | 'SEMESTRAL' | 'ANUAL';

export interface PlanoResponseDTO {
  id: string;
  nome: string;
  valor: number;
  descricao: string;
  periodicidade: Periodicidade;
  duracaoDias: number;
  ativo: boolean;
  createdAt?: string;
  updatedAt?: string;
}
