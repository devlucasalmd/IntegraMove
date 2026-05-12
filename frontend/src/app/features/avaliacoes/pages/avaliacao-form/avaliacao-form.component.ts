import { Component, OnInit } from '@angular/core';
import {
  ReactiveFormsModule,
  FormBuilder,
  Validators,
  FormGroup,
} from '@angular/forms';

import { ActivatedRoute, Router } from '@angular/router';

import { AvaliacaoService } from '../../services/avaliacao.service';

import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';

import { AvaliacaoRequestDTO } from '../../models/avaliacao.model';

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
    CommonModule,
  ],
  templateUrl: './avaliacao-form.component.html',
  styleUrls: ['./avaliacao-form.component.css'],
})
export class AvaliacaoFormComponent implements OnInit {
  avaliacaoForm: FormGroup;

  alunoId!: string;

  // 🔥 ID da avaliação para visualização
  avaliacaoId?: string;

  // 🔥 controla se é modo visualização
  modoVisualizacao = false;

  constructor(
    private fb: FormBuilder,
    private avaliacaoService: AvaliacaoService,
    private route: ActivatedRoute,
    private router: Router,
  ) {
    this.avaliacaoForm = this.fb.group({
      dataAvaliacao: ['', Validators.required],

      remadaBracoD: [null, Validators.required],
      remadaBracoE: [null, Validators.required],

      elevacaoLatD: [null, Validators.required],
      elevacaoLatE: [null, Validators.required],

      extensaoJoelhoD: [null, Validators.required],
      extensaoJoelhoE: [null, Validators.required],

      flexaoJoelhoD: [null, Validators.required],
      flexaoJoelhoE: [null, Validators.required],

      extensaoQuadrilD: [null, Validators.required],
      extensaoQuadrilE: [null, Validators.required],
    });
  }

  ngOnInit(): void {
    try {
      // 🔥 id do aluno vindo da rota pai
      this.alunoId = this.getAlunoId();

      console.log('✅ alunoId:', this.alunoId);

      // 🔥 pega id da avaliação
      this.avaliacaoId = this.route.snapshot.paramMap.get('avaliacaoId')!;

      console.log('📌 avaliacaoId:', this.avaliacaoId);

      // 🔥 se existir avaliação -> visualização
      if (this.avaliacaoId) {
        this.modoVisualizacao = true;

        this.buscarAvaliacao(this.avaliacaoId);
      }
    } catch (e) {
      console.error('❌ Erro ao obter IDs', e);
    }
  }

  getAlunoId(): string {
    let route: ActivatedRoute | null = this.route;

    while (route) {
      // 🔥 procura somente o parametro "id"
      const alunoId = route.snapshot.paramMap.get('alunoId');

      // 🔥 mas IGNORA se for a rota da avaliação
      if (alunoId && !route.snapshot.paramMap.get('avaliacaoId')) {
        return alunoId;
      }

      route = route.parent;
    }

    throw new Error('ID do aluno não encontrado');
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

  private formatarData(data: any): string {
    if (!data) return '';

    const date = new Date(data);

    return date.toLocaleDateString('sv-SE');
  }

  salvar(): void {
    // 🔥 impede salvar em modo visualização
    if (this.modoVisualizacao) {
      return;
    }

    if (!this.alunoId) {
      console.error('❌ alunoId está undefined');

      return;
    }

    if (this.avaliacaoForm.invalid) {
      return;
    }

    const formValue = this.avaliacaoForm.value;

    const avaliacao: AvaliacaoRequestDTO = {
      alunoId: this.alunoId,

      dataAvaliacao: this.formatarData(formValue.dataAvaliacao),

      ...formValue,
    };

    console.log('🚀 Dados enviados:', avaliacao);

    this.avaliacaoService
      .cadastrarAvaliacao(this.alunoId, avaliacao)
      .subscribe({
        next: (response) => {
          console.log('✅ Avaliação salva com sucesso', response);

          this.avaliacaoForm.reset();

          // 🔥 volta para listagem
          this.router.navigate(['../'], {
            relativeTo: this.route,
          });
        },

        error: (err) => {
          console.error('❌ Erro ao salvar avaliação', err);
        },
      });
  }

  cancelar(): void {
    this.router.navigate(['/alunos', this.alunoId, 'avaliacoes']);
  }
}
