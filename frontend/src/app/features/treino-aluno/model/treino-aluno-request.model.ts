export interface TreinoAlunoRequestDTO{
  nome: string;
  dataInicio: string;
  dataFim?: string;
  ativo: boolean;
  treinosIds: string[];
}
