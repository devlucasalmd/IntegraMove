import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import {
  DIAS_SEMANA,
  EventoAgenda,
  EventoAgendaFormValue,
  HORA_FIM_AGENDA,
  HORA_INICIO_AGENDA
 } from '../../model/agenda.model';

@Component({
  selector: 'app-agenda-form-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
  ],
  templateUrl: './agenda-form.component.html',
})
export class AgendaFormDialogComponent {
  form: FormGroup;
  diasSemana = DIAS_SEMANA;
  horas = Array.from({ length: HORA_FIM_AGENDA - HORA_INICIO_AGENDA + 1 }, (_, i) => HORA_INICIO_AGENDA + i);

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AgendaFormDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { evento: EventoAgenda | null }
  ) {
    const evento = data.evento;

    this.form = this.fb.group({
      titulo: [evento?.titulo ?? '', Validators.required],
      tipo: [evento?.tipo ?? 'turma', Validators.required],
      instrutor: [evento?.instrutor ?? '', Validators.required],
      aluno: [evento?.aluno ?? ''],
      local: [evento?.local ?? '', Validators.required],
      diaSemana: [evento?.diaSemana ?? 0, Validators.required],
      horaInicio: [evento?.horaInicio ?? HORA_INICIO_AGENDA, Validators.required],
      horaFim: [evento?.horaFim ?? HORA_INICIO_AGENDA + 1, Validators.required],
      capacidade: [evento?.capacidade ?? null],
    });
  }

  get ehTurma(): boolean {
    return this.form.get('tipo')?.value === 'turma';
  }

  salvar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.dialogRef.close(this.form.value as EventoAgendaFormValue);
  }

  cancelar(): void {
    this.dialogRef.close(null);
  }
}
