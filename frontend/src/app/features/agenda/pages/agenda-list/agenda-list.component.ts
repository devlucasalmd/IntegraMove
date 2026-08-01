import { Component, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatTooltipModule } from '@angular/material/tooltip';
import { AgendaFormDialogComponent } from '../agenda-form/agenda-form.component';
import {
  DIAS_SEMANA,
  EventoAgenda,
  EventoAgendaFormValue,
  HORA_FIM_AGENDA,
  HORA_INICIO_AGENDA,
} from '../../model/agenda.model';

type FiltroTipo = 'todos' | 'turma' | 'individual' | 'avaliacao';

@Component({
  selector: 'app-agenda',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatButtonToggleModule,
    MatDialogModule,
    MatTooltipModule,
  ],
  templateUrl: './agenda-list.component.html',
  styleUrl: './agenda-list.component.css',
})
export class AgendaListComponent implements OnInit {
  diasSemana = DIAS_SEMANA;
  horas = Array.from({ length: HORA_FIM_AGENDA - HORA_INICIO_AGENDA }, (_, i) => HORA_INICIO_AGENDA + i);
  horaInicioAgenda = HORA_INICIO_AGENDA;

  eventos = signal<EventoAgenda[]>([]);
  filtro = signal<FiltroTipo>('todos');
  semanaAtual = signal(this.obterInicioSemana(new Date()));

  eventosFiltrados = computed(() => {
    const tipo = this.filtro();
    return this.eventos().filter((e) => tipo === 'todos' || e.tipo === tipo);
  });

  labelSemana = computed(() => {
    const inicio = this.semanaAtual();
    const fim = new Date(inicio);
    fim.setDate(fim.getDate() + 6);
    return `${this.formatarData(inicio)} – ${this.formatarData(fim)}`;
  });

  constructor(private dialog: MatDialog) {}

  ngOnInit(): void {
    this.eventos.set(this.gerarEventosMock());
  }

  estiloEvento(evento: EventoAgenda) {
    const linhaInicio = evento.horaInicio - this.horaInicioAgenda + 2;
    const linhaFim = evento.horaFim - this.horaInicioAgenda + 2;
    return {
      'grid-row': `${linhaInicio} / ${linhaFim}`,
      'grid-column': `${evento.diaSemana + 2}`,
    };
  }

  semanaAnterior(): void {
    const nova = new Date(this.semanaAtual());
    nova.setDate(nova.getDate() - 7);
    this.semanaAtual.set(nova);
  }

  proximaSemana(): void {
    const nova = new Date(this.semanaAtual());
    nova.setDate(nova.getDate() + 7);
    this.semanaAtual.set(nova);
  }

  semanaHoje(): void {
    this.semanaAtual.set(this.obterInicioSemana(new Date()));
  }

  onFiltroChange(valor: FiltroTipo): void {
    this.filtro.set(valor);
  }

  abrirNovoEvento(): void {
    const dialogRef = this.dialog.open(AgendaFormDialogComponent, {
      width: '480px',
      data: { evento: null },
    });

    dialogRef.afterClosed().subscribe((resultado: EventoAgendaFormValue | null) => {
      if (!resultado) {
        return;
      }

      const novoEvento: EventoAgenda = {
        id: crypto.randomUUID(),
        titulo: resultado.titulo,
        tipo: resultado.tipo,
        instrutor: resultado.instrutor,
        aluno: resultado.aluno || undefined,
        local: resultado.local,
        diaSemana: resultado.diaSemana,
        horaInicio: resultado.horaInicio,
        horaFim: resultado.horaFim,
        capacidade: resultado.capacidade ?? undefined,
        vagasOcupadas: 0,
        status: 'confirmado',
      };

      this.eventos.update((lista) => [...lista, novoEvento]);
    });
  }

  editarEvento(evento: EventoAgenda): void {
    const dialogRef = this.dialog.open(AgendaFormDialogComponent, {
      width: '480px',
      data: { evento },
    });

    dialogRef.afterClosed().subscribe((resultado: EventoAgendaFormValue | null) => {
      if (!resultado) {
        return;
      }

      this.eventos.update((lista) =>
        lista.map((e) =>
          e.id === evento.id
            ? {
                ...e,
                titulo: resultado.titulo,
                tipo: resultado.tipo,
                instrutor: resultado.instrutor,
                aluno: resultado.aluno || undefined,
                local: resultado.local,
                diaSemana: resultado.diaSemana,
                horaInicio: resultado.horaInicio,
                horaFim: resultado.horaFim,
                capacidade: resultado.capacidade ?? undefined,
              }
            : e
        )
      );
    });
  }

  removerEvento(evento: EventoAgenda, event: MouseEvent): void {
    event.stopPropagation();
    this.eventos.update((lista) => lista.filter((e) => e.id !== evento.id));
  }

  private obterInicioSemana(data: Date): Date {
    const copia = new Date(data);
    const diaSemana = (copia.getDay() + 6) % 7;
    copia.setDate(copia.getDate() - diaSemana);
    copia.setHours(0, 0, 0, 0);
    return copia;
  }

  private formatarData(data: Date): string {
    return data.toLocaleDateString('pt-BR', { day: '2-digit', month: '2-digit' });
  }

  private gerarEventosMock(): EventoAgenda[] {
    return [
      {
        id: '1',
        titulo: 'Spinning',
        tipo: 'turma',
        instrutor: 'Prof. Rafael',
        local: 'Sala 2',
        diaSemana: 0,
        horaInicio: 7,
        horaFim: 8,
        capacidade: 20,
        vagasOcupadas: 16,
        status: 'confirmado',
      },
      {
        id: '2',
        titulo: 'Avaliação Física',
        tipo: 'avaliacao',
        instrutor: 'Prof. Aline',
        aluno: 'Marina Souza',
        local: 'Sala de Avaliação',
        diaSemana: 0,
        horaInicio: 10,
        horaFim: 11,
        status: 'confirmado',
      },
      {
        id: '3',
        titulo: 'Funcional',
        tipo: 'turma',
        instrutor: 'Prof. Rafael',
        local: 'Área externa',
        diaSemana: 1,
        horaInicio: 18,
        horaFim: 19,
        capacidade: 15,
        vagasOcupadas: 15,
        status: 'confirmado',
      },
      {
        id: '4',
        titulo: 'Personal Trainer',
        tipo: 'individual',
        instrutor: 'Prof. Carlos',
        aluno: 'João Pedro',
        local: 'Sala 1',
        diaSemana: 2,
        horaInicio: 19,
        horaFim: 20,
        status: 'pendente',
      },
      {
        id: '5',
        titulo: 'Yoga',
        tipo: 'turma',
        instrutor: 'Prof. Camila',
        local: 'Sala 3',
        diaSemana: 3,
        horaInicio: 8,
        horaFim: 9,
        capacidade: 12,
        vagasOcupadas: 9,
        status: 'confirmado',
      },
      {
        id: '6',
        titulo: 'Avaliação Física',
        tipo: 'avaliacao',
        instrutor: 'Prof. Aline',
        aluno: 'Carlos Eduardo',
        local: 'Sala de Avaliação',
        diaSemana: 3,
        horaInicio: 17,
        horaFim: 18,
        status: 'pendente',
      },
    ];
  }
}
