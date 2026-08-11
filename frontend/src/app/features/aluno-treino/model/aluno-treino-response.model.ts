export interface AlunoTreinoResponseDTO {
  id: string;
  alunoId: string;
  treinosIds: string[];
  nome: string;
  dataInicio: string;
  dataFim?: string;
  ativo: boolean;

  treinoNome?: string;
  funcionalidade?: string;
  nivel?: string;
  grupoMuscular?: string;
}
