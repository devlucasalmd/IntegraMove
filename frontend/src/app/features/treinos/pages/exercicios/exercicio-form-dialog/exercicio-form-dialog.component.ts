import { Component, Inject, OnInit } from '@angular/core';

import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ExercicioService } from '../../../services/exercicios.service';
import { ExercicioResponseDTO } from '../../../models/exercicio-response';
import { ExercicioRequestDTO } from '../../../models/exercicio-request.model';
import { MatRadioModule } from '@angular/material/radio';
import { ViewEncapsulation } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-exercicio-form-dialog',
  standalone: true,
  templateUrl: './exercicio-form-dialog.component.html',
  styleUrl: './exercicio-form-dialog.component.css',

  imports: [
    MatDialogModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatIconModule
],
})
export class ExercicioFormDialogComponent implements OnInit {
  isEdit = false;
  isView = false;

  form!: FormGroup;

  gruposMusculares = [
    { value: 'PEITO', label: 'Peito' },
    { value: 'COSTAS', label: 'Costas' },
    { value: 'PERNAS', label: 'Pernas' },
    { value: 'OMBROS', label: 'Ombros' },
    { value: 'BICEPS', label: 'Bíceps' },
    { value: 'TRICEPS', label: 'Tríceps' },
    { value: 'ABDOMEN', label: 'Abdômen' },
    { value: 'GLUTEOS', label: 'Glúteos' },
    { value: 'TRAPEZIO', label: 'Trapézio' },
    { value: 'ANTEBRACO', label: 'Antebraço' },
  ];

  intensidades = [
    { value: 'BAIXA', label: 'Baixa' },
    { value: 'MEDIA', label: 'Média' },
    { value: 'ALTA', label: 'Alta' },
  ];

  constructor(
    private fb: FormBuilder,
    private service: ExercicioService,
    private dialogRef: MatDialogRef<ExercicioFormDialogComponent>,
    @Inject(MAT_DIALOG_DATA)
    public data: {
      exercicio?: ExercicioResponseDTO;
      modo: 'criar' | 'editar' | 'visualizar';
    } | null,
  ) {}

  ngOnInit(): void {
    this.isEdit = this.data?.modo === 'editar';
    this.isView = this.data?.modo === 'visualizar';

    this.form = this.fb.group({
      nome: ['', Validators.required],
      grupoMuscular: ['', Validators.required],
      intensidade: ['', Validators.required],
      ativo: [true],
    });

    if (this.data?.exercicio) {
      this.form.patchValue(this.data.exercicio);
    }

    if (this.isView) {
      this.form.disable();
    }
  }

  salvar(): void {
    if (this.isView) {
      return;
    }

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload = this.form.getRawValue() as ExercicioRequestDTO;

    if (this.isEdit && this.data?.exercicio?.id) {
      this.service
        .atualizarExercicio(this.data.exercicio.id, payload)
        .subscribe(() => {
          this.dialogRef.close(true);
        });
    } else {
      this.service.cadastrarExercicio(payload).subscribe(() => {
        this.dialogRef.close(true);
      });
    }
  }

  habilitarEdicao(): void {
    this.isView = false;
    this.isEdit = true;

    if (this.data) {
      this.data.modo = 'editar';
    }

    this.form.enable();
  }

  fechar(): void {
    this.dialogRef.close();
  }
}
