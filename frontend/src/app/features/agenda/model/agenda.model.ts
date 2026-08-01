export type TipoEvento = 'turma' | 'individual' | 'avaliacao';

export type StatusEvento = 'confirmado' | 'pendente' | 'cancelado';

export interface EventoAgenda {
  id: string;
  titulo: string;
  tipo: TipoEvento;
  instrutor: string;
  aluno?: string;
  local: string;
  diaSemana: number;
  horaInicio: number;
  horaFim: number;
  capacidade?: number;
  vagasOcupadas?: number;
  status: StatusEvento;
}

export interface EventoAgendaFormValue {
  titulo: string;
  tipo: TipoEvento;
  instrutor: string;
  aluno: string;
  local: string;
  diaSemana: number;
  horaInicio: number;
  horaFim: number;
  capacidade: number | null;
}

export const DIAS_SEMANA = ['Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb', 'Dom'];

export const HORA_INICIO_AGENDA = 6;

export const HORA_FIM_AGENDA = 22;
