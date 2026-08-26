import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';

import { VendaService } from '../../service/venda.service';
import { TipoVenda, VendaRequestDTO, VendaResponseDTO } from '../../model/venda.model';
import { PlanoResponseDTO } from '../../../planos/models/plano-response';

export interface VendaFormDialogData {
  alunoId: string;
  planosAtivos: PlanoResponseDTO[];
}

const TIPOS_VENDA: { value: TipoVenda; label: string }[] = [
  { value: 'PLANO', label: 'Plano' },
  { value: 'AVALIACAO', label: 'Avaliação' },
  { value: 'DIARIA', label: 'Diária' },
  { value: 'PRODUTO', label: 'Produto' }
];

function valorMaiorQueZero(): ValidatorFn {
  return (control): ValidationErrors | null => {
    const valor = control.value;

    if (valor === null || valor === undefined || valor === '') {
      return null;
    }

    return Number(valor) > 0 ? null : { valorInvalido: true };
  };
}

/**
 * Dialog de criação de venda (aba "Vendas" do perfil do aluno).
 *
 * Os campos exibidos variam conforme `tipo`:
 * - PLANO: planoId, diaVencimento, permiteRenovacaoAutomatica
 * - AVALIACAO / DIARIA / PRODUTO: descricao, valor
 */
@Component({
  selector: 'app-venda-form-dialog',
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
    MatCheckboxModule,
    MatDatepickerModule
  ],
  providers: [provideNativeDateAdapter()],
  templateUrl: './venda-form-dialog.component.html',
  styleUrl: './venda-form-dialog.component.css'
})
export class VendaFormDialogComponent implements OnInit {

  form!: FormGroup;
  salvando = false;
  erroEnvio: string | null = null;

  tiposVenda = TIPOS_VENDA;

  constructor(
    private fb: FormBuilder,
    private vendaService: VendaService,
    private dialogRef: MatDialogRef<VendaFormDialogComponent>,

    @Inject(MAT_DIALOG_DATA)
    public data: VendaFormDialogData
  ) {}

  ngOnInit(): void {
    this.criarFormulario();
    this.observarMudancaDeTipo();
  }

  private criarFormulario(): void {
    this.form = this.fb.group({
      tipo: ['', Validators.required],
      dataVenda: [new Date(), Validators.required],

      planoId: [null],
      diaVencimento: [null],
      permiteRenovacaoAutomatica: [false],

      descricao: [''],
      valor: [null]
    });
  }

  private observarMudancaDeTipo(): void {
    this.form.get('tipo')?.valueChanges.subscribe((tipo: TipoVenda) => {
      this.atualizarValidadoresPorTipo(tipo);
    });

    this.form.get('planoId')?.valueChanges.subscribe(() => {
      if (this.tipoSelecionado) {
        this.atualizarValidadoresPorTipo(this.tipoSelecionado);
      }
    });
  }

  /** Plano DIARIA não tem "dia do mês" de vencimento — cada dia de vigência é sua própria cobrança. */
  get planoSelecionadoEhDiario(): boolean {
    const planoId = this.form?.get('planoId')?.value;
    const plano = this.data.planosAtivos.find((p) => p.id === planoId);
    return plano?.periodicidade === 'DIARIA';
  }

  private atualizarValidadoresPorTipo(tipo: TipoVenda): void {
    const planoId = this.form.get('planoId');
    const diaVencimento = this.form.get('diaVencimento');
    const permiteRenovacaoAutomatica = this.form.get('permiteRenovacaoAutomatica');
    const descricao = this.form.get('descricao');
    const valor = this.form.get('valor');

    if (tipo === 'PLANO') {
      descricao?.clearValidators();
      descricao?.setValue('', { emitEvent: false });
      valor?.clearValidators();
      valor?.setValue(null, { emitEvent: false });

      planoId?.setValidators([Validators.required]);

      if (this.planoSelecionadoEhDiario) {
        diaVencimento?.clearValidators();
        diaVencimento?.setValue(null, { emitEvent: false });
      } else {
        diaVencimento?.setValidators([Validators.required, Validators.min(1), Validators.max(31)]);
      }
    } else {
      planoId?.clearValidators();
      planoId?.setValue(null, { emitEvent: false });
      diaVencimento?.clearValidators();
      diaVencimento?.setValue(null, { emitEvent: false });
      permiteRenovacaoAutomatica?.setValue(false, { emitEvent: false });

      descricao?.setValidators([Validators.required]);
      valor?.setValidators([Validators.required, valorMaiorQueZero()]);
    }

    planoId?.updateValueAndValidity({ emitEvent: false });
    diaVencimento?.updateValueAndValidity({ emitEvent: false });
    descricao?.updateValueAndValidity({ emitEvent: false });
    valor?.updateValueAndValidity({ emitEvent: false });
  }

  get tipoSelecionado(): TipoVenda | '' {
    return this.form?.get('tipo')?.value ?? '';
  }

  get ehVendaDePlano(): boolean {
    return this.tipoSelecionado === 'PLANO';
  }

  salvar(): void {
    this.erroEnvio = null;

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const request = this.montarRequest();

    this.salvando = true;

    this.vendaService.criar(request).subscribe({
      next: (venda) => {
        this.salvando = false;
        this.dialogRef.close(venda);
      },
      error: (erro: HttpErrorResponse) => {
        this.salvando = false;
        this.erroEnvio = this.extrairMensagemErro(erro);
      }
    });
  }

  private montarRequest(): VendaRequestDTO {
    const valores = this.form.getRawValue();

    return {
      alunoId: this.data.alunoId,
      tipo: valores.tipo,
      dataVenda: this.formatarData(valores.dataVenda),
      planoId: valores.tipo === 'PLANO' ? valores.planoId : null,
      diaVencimento: valores.tipo === 'PLANO' && !this.planoSelecionadoEhDiario ? valores.diaVencimento : null,
      permiteRenovacaoAutomatica: valores.tipo === 'PLANO' ? !!valores.permiteRenovacaoAutomatica : null,
      descricao: valores.tipo !== 'PLANO' ? valores.descricao : null,
      valor: valores.tipo !== 'PLANO' ? Number(valores.valor) : null
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
   * - Exceptions de negócio chegam como `{ erro: "mensagem" }`.
   * - Erros de validação de bean (`MethodArgumentNotValidException`) chegam
   *   como um mapa simples `{ campo: "mensagem" }`, sem a chave "erro".
   */
  private extrairMensagemErro(erro: HttpErrorResponse): string {
    const corpo = erro?.error;

    if (corpo && typeof corpo === 'object' && typeof corpo.erro === 'string') {
      return this.mapearMensagemDeNegocio(corpo.erro);
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

    return 'Não foi possível registrar a venda. Verifique os dados e tente novamente.';
  }

  private mapearMensagemDeNegocio(mensagem: string): string {
    const mensagemNormalizada = mensagem.toLowerCase();

    if (mensagemNormalizada.includes('contrato ativo')) {
      return 'Aluno já possui um plano ativo.';
    }

    if (mensagemNormalizada.includes('dia de vencimento') || mensagemNormalizada.includes('diavencimento')) {
      return 'Informe o dia de vencimento (entre 1 e 31).';
    }

    return mensagem;
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
