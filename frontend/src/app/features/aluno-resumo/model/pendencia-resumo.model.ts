export type StatusPendencia = 'PENDENTE' | 'RESOLVIDA';

export interface PendenciaResumoDTO {
  id: string;
  tipo: string;
  descricao: string;
  status: StatusPendencia;
}
