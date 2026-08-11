export interface AlunoTreinoRequestDTO{
  nome: string;
  dataInicio: string;
  dataFim?: string;
  ativo: boolean;
  treinosIds: string[];
}
