import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { FormsModule } from '@angular/forms';
import { ExercicioResponseDTO } from '../../../../models/exercicio-response';
import { ExercicioService } from '../../../../services/exercicios.service';
import { finalize } from 'rxjs';
import { TreinoItemRequestDTO } from '../../../../models/treino-item-request';
import { TreinoItemService } from '../../../../services/treino-item.service';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-exercicio-dialog',
  standalone: true,
  templateUrl: './exercicio-dialog.component.html',
  styleUrls: ['./exercicio-dialog.component.css'],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatDialogModule,
    MatInputModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    FormsModule,
    MatIcon,
  ],
})
export class ExercicioDialogComponent implements OnInit {
  form!: FormGroup;

  filtro = '';
  exercicioSelecionado: ExercicioResponseDTO | null = null;

  exercicios: ExercicioResponseDTO[] = [];
  exerciciosAdicionados: ExercicioResponseDTO[] = [];

  carregandoExercicios = false;
  salvando = false;
  finalizouComAlteracao = false;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ExercicioDialogComponent>,
    private exercicioService: ExercicioService,
    private treinoItemService: TreinoItemService,
    @Inject(MAT_DIALOG_DATA)
    public data: {
      treinoId: string;
      treinoNome: string;
      grupoMuscular: string;
    },
  ) {
    this.form = this.fb.group({
      ordem: [1, [Validators.required, Validators.min(1)]],
      series: [3, [Validators.required, Validators.min(1)]],
      repeticoes: ['', [Validators.required]],
      carga: [0, [Validators.min(0)]],
      descanso: [60, [Validators.required, Validators.min(0)]],
      observacao: [''],
    });
  }

  ngOnInit(): void {
    this.carregarExercicios();
  }

  carregarExercicios(): void {
    this.carregandoExercicios = true;

    this.exercicioService.listarExercicios()
      .pipe(finalize(() => (this.carregandoExercicios = false)))
      .subscribe({
        next: (response) => {
          this.exercicios = response.filter(
            (exercicio) => exercicio.grupoMuscular === this.data.grupoMuscular,
          );
        },
        error: (erro) => {
          console.error('Erro ao carregar exercícios:', erro);
        },
      });
  }

  get exerciciosFiltrados(): ExercicioResponseDTO[] {
    const termo = this.filtro.trim().toLowerCase();

    if (!termo) {
      return this.exercicios;
    }

    return this.exercicios.filter(
      (exercicio) =>
        exercicio.nome.toLowerCase().includes(termo) ||
        exercicio.grupoMuscular?.toLowerCase().includes(termo),
    );
  }

  adicionar(): void {
    if (!this.exercicioSelecionado) {
      return;
    }

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const request: TreinoItemRequestDTO = {
      exercicioId: this.exercicioSelecionado.id,
      series: this.form.value.series,
      repeticoes: this.form.value.repeticoes,
      carga: this.form.value.carga,
      descanso: this.form.value.descanso,
      observacao: this.form.value.observacao,
      ordem: this.form.value.ordem,
    };

    this.salvando = true;

    this.treinoItemService
      .adicionarItemAoTreino(this.data.treinoId, request)
      .pipe(finalize(() => (this.salvando = false)))
      .subscribe({
        next: () => {
          this.finalizouComAlteracao = true;

          this.exerciciosAdicionados.push(this.exercicioSelecionado!);

          this.exercicioSelecionado = null;
          this.filtro = '';

          this.form.reset({
            ordem: this.exerciciosAdicionados.length + 1,
            series: 3,
            repeticoes: '',
            carga: 0,
            descanso: 60,
            observacao: '',
          });
        },
        error: (erro) => {
          console.error('Erro ao adicionar exercício ao treino:', erro);
        },
      });
  }

  selecionar(exercicio: ExercicioResponseDTO): void {
    this.exercicioSelecionado = exercicio;
  }

  finalizar(): void {
    this.dialogRef.close(this.finalizouComAlteracao);
  }

  campoInvalido(campo: string): boolean {
    const control = this.form.get(campo);
    return !!control && control.invalid && control.touched;
  }

  jaAdicionado(exercicio: ExercicioResponseDTO): boolean {
    return this.exerciciosAdicionados.some((item) => item.id === exercicio.id);
  }

  fechar() {
    this.dialogRef.close();
  }
}
