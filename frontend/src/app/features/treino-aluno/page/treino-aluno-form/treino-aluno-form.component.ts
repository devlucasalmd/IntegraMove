import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { TreinoResponseDTO } from '../../../treinos/models/treino-response.model';
import { TreinoService } from '../../../treinos/services/treino.service';
import { TreinoAlunoService } from '../../service/treino-aluno.service';
import { TreinoAlunoRequestDTO } from '../../model/treino-aluno-request.model';

@Component({
  selector: 'app-aluno-treino-form',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,
    MatSlideToggleModule,
  ],
  templateUrl: './treino-aluno-form.component.html',
  styleUrls: ['./treino-aluno-form.component.css'],
})
export class TreinoAlunoFormComponent implements OnInit {
  alunoId!: string;

  form!: FormGroup;

  treinos: TreinoResponseDTO[] = [];
  treinosSelecionados: TreinoResponseDTO[] = [];

  carregandoTreinos = false;
  salvando = false;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private treinoService: TreinoService,
    private treinoAlunoService: TreinoAlunoService,
  ) {}

  ngOnInit(): void {
    this.alunoId =
      this.route.snapshot.paramMap.get('alunoId') ||
      this.route.parent?.snapshot.paramMap.get('alunoId') ||
      this.route.parent?.parent?.snapshot.paramMap.get('alunoId') ||
      '';

    this.form = this.fb.group({
      treinosIds: [[], Validators.required],
      nome: ['', Validators.required],
      dataInicio: [this.obterDataAtual(), Validators.required],
      dataFim: [''],
      ativo: [true],
    });

    this.carregarTreinos();

    this.form.get('treinosIds')?.valueChanges.subscribe((treinosIds: string[]) => {
      this.aoSelecionarTreinos(treinosIds);
    });
  }

  carregarTreinos(): void {
    this.carregandoTreinos = true;

    this.treinoService.listarTreinos().subscribe({
      next: (response) => {
        this.treinos = response;
        this.carregandoTreinos = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar treinos:', erro);
        this.carregandoTreinos = false;
      },
    });
  }

  aoSelecionarTreinos(treinosIds: string[]): void {
    this.treinosSelecionados = this.treinos.filter((treino) =>
      treinosIds.includes(treino.id)
    );

    if (this.treinosSelecionados.length > 0 && !this.form.get('nome')?.value) {
      this.form.patchValue({
        nome: `Ficha - ${this.treinosSelecionados[0].nome}`,
      });
    }
  }

  salvar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const request: TreinoAlunoRequestDTO = {
      nome: this.form.value.nome,
      dataInicio: this.form.value.dataInicio,
      dataFim: this.form.value.dataFim || undefined,
      ativo: this.form.value.ativo,
      treinosIds: this.form.value.treinosIds,
    };

    this.salvando = true;

    this.treinoAlunoService.criarFicha(this.alunoId, request).subscribe({
      next: () => {
        this.salvando = false;
        this.voltar();
      },
      error: (erro) => {
        console.error('Erro ao criar ficha de treino:', erro);
        this.salvando = false;
      },
    });
  }

  voltar(): void {
    this.router.navigate(['../'], {
      relativeTo: this.route,
    });
  }

  campoInvalido(campo: string): boolean {
    const control = this.form.get(campo);
    return !!control && control.invalid && control.touched;
  }

  private obterDataAtual(): string {
    return new Date().toISOString().split('T')[0];
  }
}
