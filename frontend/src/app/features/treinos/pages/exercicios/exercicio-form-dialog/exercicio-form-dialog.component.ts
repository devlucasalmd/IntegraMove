import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ExercicioService } from '../../../services/exercicios.service';
import { ExercicioResponseDTO } from '../../../models/exercicio-response';
import { ExercicioRequestDTO } from '../../../models/exercicio-request.model';
import { MatRadioModule } from '@angular/material/radio';
import { ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-exercicio-form-dialog',
  standalone: true,
  templateUrl: './exercicio-form-dialog.component.html',
  styleUrl: './exercicio-form-dialog.component.css',

  imports: [
    CommonModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    // TablerIconsModule.pick({ IconBarbell })
  ],
})
export class ExercicioFormDialogComponent implements OnInit{

  isEdit = false;

  form! : FormGroup;

  gruposMusculares: string[] = [
    'PEITO',
    'COSTAS',
    'PERNAS',
    'OMBRO',
    'BICEPS',
    'TRICEPS'
  ];

  intensidades: string[] = [
    'BAIXA',
    'MEDIA',
    'ALTA'
  ];

  constructor(
    private fb: FormBuilder,
    private service: ExercicioService,
    private dialogRef: MatDialogRef<ExercicioFormDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ExercicioResponseDTO | null
  ) {}

  ngOnInit(): void {

    this.form = this.fb.group({
    nome: ['', Validators.required],
    grupoMuscular: ['', Validators.required],
    intensidade: ['', Validators.required],
    ativo: true
  });
    if (this.data) {
      this.isEdit = true;
      this.form.patchValue(this.data);
    }
  }

  salvar(): void {

    if (this.form.invalid) return;

    const payload = this.form.getRawValue() as ExercicioRequestDTO;

    if (this.isEdit && this.data?.id) {
      // ⚠️ você precisa ter esse método no service
      this.service.atualizarExercicio(this.data.id, payload).subscribe(() => {
        this.dialogRef.close(true);
      });
    } else {
      this.service.cadastrarExercicio(payload).subscribe(() => {
        this.dialogRef.close(true);
      });
    }
  }

  fechar() {
    this.dialogRef.close();
  }
}
