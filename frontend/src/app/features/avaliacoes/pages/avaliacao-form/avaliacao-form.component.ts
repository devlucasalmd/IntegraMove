import { Component, Inject, OnInit } from '@angular/core';
import {
  ReactiveFormsModule,
  FormBuilder,
  Validators,
  FormGroup,
} from '@angular/forms';

import { ActivatedRoute, Router } from '@angular/router';

import { AvaliacaoService } from '../../services/avaliacao.service';

import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';


import { AvaliacaoRequestDTO } from '../../models/avaliacao-request.model';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-avaliacao-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCardModule,
    MatIcon
],
  templateUrl: './avaliacao-form.component.html',
  styleUrls: ['./avaliacao-form.component.css'],
})
export class AvaliacaoFormComponent implements OnInit {

  avaliacaoForm!: FormGroup;

  alunoId!: string;

  avaliacaoId?: string;

  modoVisualizacao = false;
  salvando = false;
  carregando = false;

  constructor(
    private fb: FormBuilder,
    private avaliacaoService: AvaliacaoService,
    private dialogRef: MatDialogRef<AvaliacaoFormComponent>,

    @Inject(MAT_DIALOG_DATA)
    public data: {
      alunoId: string;
      avaliacaoId?: string;
      modoVisualizacao?: boolean;
    }
  ){}

  ngOnInit(): void {
    this.modoVisualizacao = !!this.data.modoVisualizacao;

    this.avaliacaoForm = this.fb.group({
      dataAvaliacao: ['', Validators.required],

      remadaBracoD: [null],
      remadaBracoE: [null],
      elevacaoLatD: [null],
      elevacaoLatE: [null],

      extensaoJoelhoD: [null],
      extensaoJoelhoE: [null],
      flexaoJoelhoD: [null],
      flexaoJoelhoE: [null],

      extensaoQuadrilD: [null],
      extensaoQuadrilE: [null]
    });

    if (this.data.avaliacaoId) {
      this.carregarAvaliacao();
    } else {
      this.avaliacaoForm.patchValue({
        dataAvaliacao: this.dataAtual()
      });
    }

    if (this.modoVisualizacao) {
      this.avaliacaoForm.disable();
    }
  }

  carregarAvaliacao(): void {
    this.carregando = true;

    this.avaliacaoService.buscarPorId(this.data.alunoId, this.data.avaliacaoId!).subscribe({
      next: (response) => {
        this.avaliacaoForm.patchValue(response);

        if (this.modoVisualizacao) {
          this.avaliacaoForm.disable();
        }

        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar avaliação:', erro);
        this.carregando = false;
      }
    });
  }

  salvar(): void {
    if (this.modoVisualizacao) {
      return;
    }

    if (this.avaliacaoForm.invalid) {
      this.avaliacaoForm.markAllAsTouched();
      return;
    }

    const request: AvaliacaoRequestDTO = this.avaliacaoForm.getRawValue();

    this.salvando = true;

    this.avaliacaoService.criar(this.data.alunoId, request).subscribe({
      next: () => {
        this.salvando = false;
        this.dialogRef.close(true);
      },
      error: (erro) => {
        console.error('Erro ao salvar avaliação:', erro);
        this.salvando = false;
      }
    });
  }

  fechar(): void {
    if (this.salvando) {
      return;
    }

    this.dialogRef.close(false);
  }

  buscarAvaliacao(avaliacaoId: string): void {
    this.avaliacaoService.buscarPorId(this.alunoId, avaliacaoId).subscribe({
      next: (response) => {
        console.log('✅ Avaliação encontrada:', response);

        this.avaliacaoForm.patchValue({
          ...response,

          dataAvaliacao: response.dataAvaliacao,
        });

        // 🔥 desabilita formulário
        this.avaliacaoForm.disable();
      },

      error: (err) => {
        console.error('❌ Erro ao buscar avaliação', err);
      },
    });
  }

  campoInvalido(campo: string): boolean {
    const control = this.avaliacaoForm.get(campo);
    return !!control && control.invalid && control.touched;
  }

  private dataAtual(): string {
    return new Date().toISOString().split('T')[0];
  }

}
