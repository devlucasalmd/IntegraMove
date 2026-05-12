export interface TreinoItemRequestDTO {
  exercicioId: string;
  series: number;
  repeticoes: string;
  carga: number;
  descanso: number;
  observacao?: string;
  ordem: number;
}
