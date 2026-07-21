import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef
} from '@angular/material/dialog';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { TreinoService } from '../../../services/treino.service';
import { TreinoRequestDTO } from '../../../models/treinos-request.model';

@Component({
  selector: 'app-treino-dialog',
  standalone: true,
  templateUrl: './treino-dialog.component.html',
  styleUrls: ['./treino-dialog.component.css'],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
  ]
})

export class TreinoDialogComponent {

  treinoForm: FormGroup;
  salvando = false;

  niveis = [
    'Iniciante',
    'Intermediário',
    'Avançado'
  ];

  gruposMusculares = [
    { value: 'PEITO', label: 'Peito' },
    { value: 'COSTAS', label: 'Costas' },
    { value: 'PERNAS', label: 'Pernas' },
    { value: 'OMBROS', label: 'Ombros' },
    { value: 'BICEPS', label: 'Bíceps' },
    { value: 'TRICEPS', label: 'Tríceps' },
    { value: 'ABDOMEN', label: 'Abdômen' },
    { value: 'GLUTEOS', label: 'Glúteos' },
    { value: 'TRAPEZIO', label: 'Trapezio' },
    { value: 'ANTEBRACO', label: 'Antebraço' }
  ];

  constructor(
    private fb: FormBuilder,
    private treinoService: TreinoService,
    private dialogRef: MatDialogRef<TreinoDialogComponent>,

    @Inject(MAT_DIALOG_DATA)
    public data: any
  ) {

    this.treinoForm = this.fb.group({
      nome: ['', Validators.required],
      responsavel: ['', Validators.required],
      funcionalidade: ['', Validators.required],
      nivel: ['', Validators.required],
      grupoMuscular: ['', Validators.required],
      repeticoes: [''],
      observacoes: ['']
    });
  }

  salvar(): void {

    if (this.treinoForm.invalid) {
      this.treinoForm.markAllAsTouched();
      return;
    }

    this.salvando = true;

    const treino: TreinoRequestDTO = this.treinoForm.value;

    this.treinoService.criarTreino(treino)
      .subscribe({
        next: (response) => {
          this.salvando = false;
          this.dialogRef.close(response);
        },
        error: (err) => {
          console.error(err);
          this.salvando = false;
        }
      });
  }

  fechar(): void {
    if (this.salvando) {
      return;
    }
    this.dialogRef.close();
  }

  campoInvalido(campo: string): boolean {
    const control = this.treinoForm.get(campo);
    return !!control && control.invalid && control.touched;
  }
}
