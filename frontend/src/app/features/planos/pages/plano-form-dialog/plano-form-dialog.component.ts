import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';

import { PlanoService } from '../../plano.service';
import { Periodicidade, PlanoResponseDTO } from '../../models/plano-response';
import { PlanoRequestDTO } from '../../models/plano-request.model';

export interface PlanoFormDialogData {
  plano: PlanoResponseDTO | null;
}

const PERIODICIDADES: { value: Periodicidade; label: string }[] = [
  { value: 'DIARIA', label: 'Diária' },
  { value: 'MENSAL', label: 'Mensal' },
  { value: 'TRIMESTRAL', label: 'Trimestral' },
  { value: 'SEMESTRAL', label: 'Semestral' },
  { value: 'ANUAL', label: 'Anual' }
];

/**
 * Dialog de criação/edição de plano (tela `/planos`).
 *
 * Em modo edição, o campo `valor` é exibido desabilitado, pois o backend
 * não permite alterar o valor de um plano já criado (regra de negócio:
 * `ValorPlanoImutavelException`). O valor atual é reenviado inalterado no
 * payload do PUT via `getRawValue()`.
 */
@Component({
  selector: 'app-plano-form-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    MatCheckboxModule
  ],
  templateUrl: './plano-form-dialog.component.html',
  styleUrl: './plano-form-dialog.component.css'
})
export class PlanoFormDialogComponent implements OnInit {

  form!: FormGroup;
  salvando = false;
  erroEnvio: string | null = null;

  periodicidades = PERIODICIDADES;

  constructor(
    private fb: FormBuilder,
    private planoService: PlanoService,
    private dialogRef: MatDialogRef<PlanoFormDialogComponent>,

    @Inject(MAT_DIALOG_DATA)
    public data: PlanoFormDialogData
  ) {}

  get editando(): boolean {
    return !!this.data?.plano;
  }

  ngOnInit(): void {
    this.criarFormulario();
  }

  private criarFormulario(): void {
    const plano = this.data?.plano ?? null;

    this.form = this.fb.group({
      nome: [plano?.nome ?? '', Validators.required],
      valor: [plano?.valor ?? null, [Validators.required, Validators.min(0.01)]],
      descricao: [plano?.descricao ?? '', Validators.required],
      periodicidade: [plano?.periodicidade ?? '', Validators.required],
      duracaoDias: [plano?.duracaoDias ?? null, [Validators.required, Validators.min(1)]],
      ativo: [plano?.ativo ?? true]
    });

    if (this.editando) {
      this.form.get('valor')?.disable();
    }
  }

  salvar(): void {
    this.erroEnvio = null;

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const request = this.montarRequest();

    this.salvando = true;

    const operacao = this.editando
      ? this.planoService.atualizar(this.data.plano!.id, request)
      : this.planoService.criar(request);

    operacao.subscribe({
      next: (plano) => {
        this.salvando = false;
        this.dialogRef.close(plano);
      },
      error: (erro: HttpErrorResponse) => {
        this.salvando = false;
        this.erroEnvio = this.extrairMensagemErro(erro);
      }
    });
  }

  private montarRequest(): PlanoRequestDTO {
    const valores = this.form.getRawValue();

    return {
      nome: valores.nome,
      valor: Number(valores.valor),
      descricao: valores.descricao,
      periodicidade: valores.periodicidade,
      duracaoDias: Number(valores.duracaoDias),
      ativo: !!valores.ativo
    };
  }

  /**
   * Traduz o corpo de erro retornado pelo backend em uma mensagem amigável.
   *
   * - Exceptions de negócio chegam como `{ erro: "mensagem" }` (ex.:
   *   `ValorPlanoImutavelException` ao tentar alterar o valor de um plano
   *   já criado).
   * - Erros de validação de bean (`MethodArgumentNotValidException`) chegam
   *   como um mapa simples `{ campo: "mensagem" }`, sem a chave "erro".
   */
  private extrairMensagemErro(erro: HttpErrorResponse): string {
    const corpo = erro?.error;

    if (corpo && typeof corpo === 'object' && typeof corpo.erro === 'string') {
      return corpo.erro;
    }

    if (corpo && typeof corpo === 'object') {
      const mensagens = Object.values(corpo).filter((valor) => typeof valor === 'string') as string[];

      if (mensagens.length > 0) {
        return mensagens.join(' ');
      }
    }

    if (typeof corpo === 'string' && corpo.trim() !== '') {
      return corpo;
    }

    return 'Não foi possível salvar o plano. Verifique os dados e tente novamente.';
  }

  fechar(): void {
    if (this.salvando) {
      return;
    }

    this.dialogRef.close();
  }

  campoInvalido(campo: string): boolean {
    const control = this.form.get(campo);
    return !!control && control.invalid && control.touched;
  }
}
