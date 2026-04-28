import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { TreinoService } from '../../../services/treino.service';
import { ExercicioService } from '../../../services/exercicios.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ExercicioResponseDTO } from '../../../models/exercicio-response';

@Component({
  selector: 'app-treino-form',
  templateUrl: './treino-form.component.html',
  styleUrls: ['./treino-form.component.css']
})
export class TreinoFormComponent implements OnInit {

  form!: FormGroup;
  listaExercicios: ExercicioResponseDTO[] = [];
  loading = false;

  constructor(
    private fb: FormBuilder,
    private treinoService: TreinoService,
    private exercicioService: ExercicioService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.carregarExercicios();
  }

  initForm() {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      responsavel: ['', Validators.required],
      funcionalidade: [''],
      nivel: [''],
      repeticoes: [''],
      observacoes: [''],
      exercicios: this.fb.array([])
    });
  }

  get exercicios(): FormArray {
    return this.form.get('exercicios') as FormArray;
  }

  carregarExercicios() {
    this.exercicioService.listarExercicios().subscribe({
      next: (data) => this.listaExercicios = data,
      error: () => this.snackBar.open('Erro ao carregar exercícios', 'Fechar')
    });
  }

  adicionarExercicio() {
    this.exercicios.push(
      this.fb.group({
        exercicioId: ['', Validators.required],
        series: [3, Validators.required],
        repeticoes: [10, Validators.required],
        carga: [0],
        descanso: [60],
        ordem: [this.exercicios.length + 1]
      })
    );
  }

  removerExercicio(index: number) {
    this.exercicios.removeAt(index);
    this.atualizarOrdem();
  }

  atualizarOrdem() {
    this.exercicios.controls.forEach((ctrl, index) => {
      ctrl.get('ordem')?.setValue(index + 1);
    });
  }

  salvar() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.loading = true;

    this.treinoService.criar(this.form.value).subscribe({
      next: () => {
        this.loading = false;
        this.snackBar.open('Treino criado com sucesso!', 'OK', {
          duration: 3000
        });
        this.router.navigate(['/treinos']);
      },
      error: () => {
        this.loading = false;
        this.snackBar.open('Erro ao salvar treino', 'Fechar');
      }
    });
  }
}
