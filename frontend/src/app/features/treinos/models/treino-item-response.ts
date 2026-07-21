export interface TreinoItemResponseDTO {
  treinoItemId?: string;
  exercicioId: string;
  nomeExercicio: string,
  series: number;
  repeticoes: string;
  carga: number;
  descanso: number;
  ordem: number;
  observacao?: string;
}
