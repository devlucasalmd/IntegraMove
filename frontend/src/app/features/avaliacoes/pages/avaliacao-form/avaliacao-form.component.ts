import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';

import { AvaliacaoService } from '../../services/avaliacao.service';
import { TemplateAvaliacaoService } from '../../../templates-avaliacao/service/template-avaliacao.service';
import { TemplateAvaliacaoResponseDTO } from '../../../templates-avaliacao/model/template-avaliacao.model';
import { AvaliacaoRealizadaRequestDTO } from '../../models/avaliacao-request.model';
import { AvaliacaoRealizadaResponseDTO } from '../../models/avaliacao-response.model';

export interface AvaliacaoFormDialogData {
  alunoId: string;
}

/**
 * Dialog de registro de uma nova avaliação física aplicada a um aluno
 * (aba "Avaliações" do perfil do aluno).
 *
 * Ao selecionar um template (`GET /templates-avaliacao`, apenas ativos), o
 * formulário monta dinamicamente um `FormArray` com um input numérico para
 * cada campo definido no template. Trocar de template reconstrói o
 * `FormArray` do zero.
 */
@Component({
  selector: 'app-avaliacao-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatDatepickerModule,
    MatIconModule
  ],
  providers: [provideNativeDateAdapter()],
  templateUrl: './avaliacao-form.component.html',
  styleUrl: './avaliacao-form.component.css'
})
export class AvaliacaoFormComponent implements OnInit {

  form!: FormGroup;

  templatesAtivos: TemplateAvaliacaoResponseDTO[] = [];
  templateSelecionado: TemplateAvaliacaoResponseDTO | null = null;

  carregandoTemplates = false;
  salvando = false;
  erroEnvio: string | null = null;

  constructor(
    private fb: FormBuilder,
    private avaliacaoService: AvaliacaoService,
    private templateService: TemplateAvaliacaoService,
    private dialogRef: MatDialogRef<AvaliacaoFormComponent>,

    @Inject(MAT_DIALOG_DATA)
    public data: AvaliacaoFormDialogData
  ) {}

  get valoresFormArray(): FormArray {
    return this.form.get('valores') as FormArray;
  }

  ngOnInit(): void {
    this.criarFormulario();
    this.carregarTemplatesAtivos();
    this.observarMudancaDeTemplate();
  }

  private criarFormulario(): void {
    this.form = this.fb.group({
      templateId: [null, Validators.required],
      dataAvaliacao: [new Date(), Validators.required],
      valores: this.fb.array([])
    });
  }

  private carregarTemplatesAtivos(): void {
    this.carregandoTemplates = true;

    this.templateService.listarAtivos().subscribe({
      next: (templates) => {
        this.templatesAtivos = templates;
        this.carregandoTemplates = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar templates de avaliação:', erro);
        this.templatesAtivos = [];
        this.erroEnvio = 'Não foi possível carregar os templates de avaliação disponíveis.';
        this.carregandoTemplates = false;
      }
    });
  }

  private observarMudancaDeTemplate(): void {
    this.form.get('templateId')?.valueChanges.subscribe((templateId: string) => {
      this.templateSelecionado = this.templatesAtivos.find((template) => template.id === templateId) ?? null;
      this.reconstruirValoresFormArray();
    });
  }

  private reconstruirValoresFormArray(): void {
    const grupos = this.templateSelecionado
      ? [...this.templateSelecionado.campos]
          .sort((a, b) => a.ordem - b.ordem)
          .map((campo) => this.fb.group({
            nomeCampo: [campo.nome],
            unidade: [campo.unidade],
            valor: [null, Validators.required]
          }))
      : [];

    this.form.setControl('valores', this.fb.array(grupos));
  }

  salvar(): void {
    this.erroEnvio = null;

    if (this.form.invalid || this.valoresFormArray.length === 0) {
      this.form.markAllAsTouched();
      return;
    }

    const request = this.montarRequest();

    this.salvando = true;

    this.avaliacaoService.criar(request).subscribe({
      next: (avaliacao: AvaliacaoRealizadaResponseDTO) => {
        this.salvando = false;
        this.dialogRef.close(avaliacao);
      },
      error: (erro: HttpErrorResponse) => {
        this.salvando = false;
        this.erroEnvio = this.extrairMensagemErro(erro);
      }
    });
  }

  private montarRequest(): AvaliacaoRealizadaRequestDTO {
    const valores = this.form.value;

    return {
      alunoId: this.data.alunoId,
      templateId: valores.templateId,
      dataAvaliacao: this.formatarData(valores.dataAvaliacao),
      valores: (valores.valores as { nomeCampo: string; valor: number }[]).map((v) => ({
        nomeCampo: v.nomeCampo,
        valor: Number(v.valor)
      }))
    };
  }

  private formatarData(data: Date | string): string {
    if (!data) {
      return '';
    }

    if (typeof data === 'string') {
      return data;
    }

    const ano = data.getFullYear();
    const mes = String(data.getMonth() + 1).padStart(2, '0');
    const dia = String(data.getDate()).padStart(2, '0');

    return `${ano}-${mes}-${dia}`;
  }

  /**
   * Traduz o corpo de erro retornado pelo backend em uma mensagem amigável.
   *
   * - `TemplateAvaliacaoNaoEncontradoException` (404).
   * - `TemplateAvaliacaoInativoException` (400) — fallback de segurança,
   *   já que o select só lista templates ativos.
   * - `ValoresAvaliacaoInvalidosException` (400) — fallback, já que os
   *   valores são montados dinamicamente a partir do template selecionado.
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

    return 'Não foi possível registrar a avaliação. Verifique os dados e tente novamente.';
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

  valorCampoInvalido(index: number): boolean {
    const grupo = this.valoresFormArray.at(index);
    const control = grupo?.get('valor');
    return !!control && control.invalid && control.touched;
  }
}
