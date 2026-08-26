import { CommonModule } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';

import { PagamentoService } from '../../service/pagamento.service';
import { FormaPagamento, PagamentoResponseDTO } from '../../model/pagamento-response.model';

export interface PagamentoFormDialogData {
  pagamento: PagamentoResponseDTO;
  descricao: string;
}

const FORMAS_PAGAMENTO: { value: FormaPagamento; label: string }[] = [
  { value: 'PIX', label: 'Pix' },
  { value: 'DEBITO', label: 'Débito' },
  { value: 'CREDITO', label: 'Crédito' },
  { value: 'DINHEIRO', label: 'Dinheiro' },
  { value: 'BOLETO', label: 'Boleto' }
];

/**
 * Dialog de registro de pagamento de uma parcela (aba "Financeiro" do perfil do aluno).
 *
 * `dataPagamento` é opcional — se o usuário limpar o campo, o backend usa a
 * data atual automaticamente.
 */
@Component({
  selector: 'app-pagamento-form-dialog',
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
    MatDatepickerModule
  ],
  providers: [provideNativeDateAdapter()],
  templateUrl: './pagamento-form-dialog.component.html',
  styleUrl: './pagamento-form-dialog.component.css'
})
export class PagamentoFormDialogComponent {

  form: FormGroup;
  salvando = false;
  erroEnvio: string | null = null;

  formasPagamento = FORMAS_PAGAMENTO;

  constructor(
    private fb: FormBuilder,
    private pagamentoService: PagamentoService,
    private dialogRef: MatDialogRef<PagamentoFormDialogComponent>,

    @Inject(MAT_DIALOG_DATA)
    public data: PagamentoFormDialogData
  ) {
    this.form = this.fb.group({
      formaPagamento: [null, Validators.required],
      dataPagamento: [new Date()]
    });
  }

  salvar(): void {
    this.erroEnvio = null;

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const valores = this.form.getRawValue();

    this.salvando = true;

    this.pagamentoService.pagarPagamento(this.data.pagamento.id, {
      formaPagamento: valores.formaPagamento,
      dataPagamento: valores.dataPagamento ? this.formatarData(valores.dataPagamento) : null
    }).subscribe({
      next: (pagamento) => {
        this.salvando = false;
        this.dialogRef.close(pagamento);
      },
      error: (erro: HttpErrorResponse) => {
        this.salvando = false;
        this.erroEnvio = this.extrairMensagemErro(erro);
      }
    });
  }

  private formatarData(data: Date | string): string {
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
   * - Erros de validação de bean chegam como um mapa simples `{ campo: "mensagem" }`.
   * - `formaPagamento` ausente é bloqueado no front pelo Validators.required,
   *   mas se ainda assim chegar ao backend sem essa informação, a exception
   *   de domínio cai no handler genérico (500, mensagem não descritiva) —
   *   por isso o fallback abaixo cobre esse caso.
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

    return 'Não foi possível registrar o pagamento. Verifique a forma de pagamento selecionada e tente novamente.';
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
