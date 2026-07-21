import { TreinoItemResponseDTO } from "./treino-item-response";

export interface TreinoRequestDTO {
  id?: string;
  nome: string;
  responsavel: string;
  funcionalidade: string;
  nivel: string;
  repeticoes?: string;
  observacoes?: string;
  grupoMuscular: string;
  exercicios: TreinoItemResponseDTO[];
}
