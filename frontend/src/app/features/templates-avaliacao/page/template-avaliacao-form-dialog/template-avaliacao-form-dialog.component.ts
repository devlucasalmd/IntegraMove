import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatChipsModule } from '@angular/material/chips';
import { MatTooltipModule } from '@angular/material/tooltip';

import { TemplateAvaliacaoService } from '../../service/template-avaliacao.service';
import { CampoTemplateResponseDTO, TemplateAvaliacaoResponseDTO } from '../../model/template-avaliacao.model';
import {
  AtualizarTemplateAvaliacaoRequestDTO,
  CampoTemplateRequestDTO,
  TemplateAvaliacaoRequestDTO
} from '../../model/template-avaliacao-request.model';

export interface TemplateAvaliacaoFormDialogData {
  template: TemplateAvaliacaoResponseDTO | null;
}

/**
 * Dialog de criação/edição de template de avaliação física
 * (tela `/administrador/avaliacao`).
 *
 * Quando o template em edição já possui avaliações vinculadas
 * (`possuiAvaliacoesVinculadas === true`), a lista de campos fica
 * somente leitura — o backend rejeita qualquer alteração nos campos nesse
 * caso (`CamposTemplateAvaliacaoImutaveisException`). Por isso o PUT é
 * enviado com `campos: null`, preservando os campos já persistidos.
 */
@Component({
  selector: 'app-template-avaliacao-form-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatCheckboxModule,
    MatChipsModule,
    MatTooltipModule
  ],
  templateUrl: './template-avaliacao-form-dialog.component.html',
  styleUrl: './template-avaliacao-form-dialog.component.css'
})
export class TemplateAvaliacaoFormDialogComponent implements OnInit {

  form!: FormGroup;
  salvando = false;
  erroEnvio: string | null = null;

  constructor(
    private fb: FormBuilder,
    private templateService: TemplateAvaliacaoService,
    private dialogRef: MatDialogRef<TemplateAvaliacaoFormDialogComponent>,

    @Inject(MAT_DIALOG_DATA)
    public data: TemplateAvaliacaoFormDialogData
  ) {}

  get editando(): boolean {
    return !!this.data?.template;
  }

  /**
   * `true` quando os campos do template não podem mais ser editados
   * (já existe ao menos uma avaliação aplicada com este template).
   */
  get somenteLeituraCampos(): boolean {
    return this.editando && this.data.template!.possuiAvaliacoesVinculadas === true;
  }

  get camposFormArray(): FormArray {
    return this.form.get('campos') as FormArray;
  }

  get camposSomenteLeitura(): CampoTemplateResponseDTO[] {
    if (!this.data?.template) {
      return [];
    }

    return [...this.data.template.campos].sort((a, b) => a.ordem - b.ordem);
  }

  ngOnInit(): void {
    this.criarFormulario();
  }

  private criarFormulario(): void {
    const template = this.data?.template ?? null;

    this.form = this.fb.group({
      nome: [template?.nome ?? '', Validators.required],
      descricao: [template?.descricao ?? '', Validators.required],
      ativo: [template?.ativo ?? true],
      campos: this.fb.array([])
    });

    if (!this.somenteLeituraCampos) {
      const camposIniciais = template?.campos ? [...template.campos].sort((a, b) => a.ordem - b.ordem) : [];

      if (camposIniciais.length > 0) {
        camposIniciais.forEach((campo) => this.adicionarCampo(campo));
      } else {
        this.adicionarCampo();
      }
    }
  }

  adicionarCampo(campo?: CampoTemplateResponseDTO): void {
    const proximaOrdem = campo?.ordem ?? this.camposFormArray.length + 1;

    const grupo = this.fb.group({
      nome: [campo?.nome ?? '', Validators.required],
      unidade: [campo?.unidade ?? ''],
      ordem: [proximaOrdem, [Validators.required, Validators.min(1)]]
    });

    this.camposFormArray.push(grupo);
  }

  removerCampo(index: number): void {
    this.camposFormArray.removeAt(index);
  }

  salvar(): void {
    this.erroEnvio = null;

    if (this.form.get('nome')?.invalid || this.form.get('descricao')?.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    if (!this.somenteLeituraCampos) {
      if (this.camposFormArray.length === 0) {
        this.erroEnvio = 'Adicione ao menos um campo ao template.';
        return;
      }

      if (this.camposFormArray.invalid) {
        this.camposFormArray.markAllAsTouched();
        return;
      }
    }

    this.salvando = true;

    const operacao = this.editando
      ? this.templateService.atualizar(this.data.template!.id, this.montarRequestAtualizacao())
      : this.templateService.criar(this.montarRequestCriacao());

    operacao.subscribe({
      next: (template) => {
        this.salvando = false;
        this.dialogRef.close(template);
      },
      error: (erro: HttpErrorResponse) => {
        this.salvando = false;
        this.erroEnvio = this.extrairMensagemErro(erro);
      }
    });
  }

  private montarCamposRequest(): CampoTemplateRequestDTO[] {
    return this.camposFormArray.value.map((campo: any) => ({
      nome: campo.nome,
      unidade: campo.unidade ? campo.unidade : null,
      ordem: Number(campo.ordem)
    }));
  }

  private montarRequestCriacao(): TemplateAvaliacaoRequestDTO {
    const valores = this.form.getRawValue();

    return {
      nome: valores.nome,
      descricao: valores.descricao,
      campos: this.montarCamposRequest()
    };
  }

  private montarRequestAtualizacao(): AtualizarTemplateAvaliacaoRequestDTO {
    const valores = this.form.getRawValue();

    return {
      nome: valores.nome,
      descricao: valores.descricao,
      ativo: !!valores.ativo,
      campos: this.somenteLeituraCampos ? null : this.montarCamposRequest()
    };
  }

  /**
   * Traduz o corpo de erro retornado pelo backend em uma mensagem amigável.
   *
   * - Exceptions de negócio chegam como `{ erro: "mensagem" }` (ex.:
   *   `CamposTemplateAvaliacaoImutaveisException`, tratada aqui como
   *   fallback de segurança caso o modo somente-leitura seja burlado).
   * - Erros de validação de bean chegam como um mapa simples
   *   `{ campo: "mensagem" }`, sem a chave "erro".
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

    return 'Não foi possível salvar o template. Verifique os dados e tente novamente.';
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

  campoDoArrayInvalido(index: number, campo: string): boolean {
    const grupo = this.camposFormArray.at(index);
    const control = grupo?.get(campo);
    return !!control && control.invalid && control.touched;
  }
}
