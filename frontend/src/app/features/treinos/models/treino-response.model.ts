import { TreinoItemResponseDTO } from "./treino-item-response";

export interface TreinoResponseDTO {
  id: string;
  nome: string;
  responsavel: string;
  funcionalidade: string;
  nivel: string;
  repeticoes?: string;
  observacoes?: string;
  grupoMuscular: String;
  exercicios: TreinoItemResponseDTO[];
}
