import { Component, Inject } from '@angular/core';
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
import { MatSelectModule } from '@angular/material/select';
import { ReceitaRequestDTO } from '../../models/receita-request.model';
import { ReceitaService } from '../../service/receita.service';

@Component({
  selector: 'app-receita-dialog',
  standalone: true,
  templateUrl: './receita-dialog.component.html',
  styleUrls: ['./receita-dialog.component.css'],

  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
  ],
})
export class ReceitaDialogComponent {
  receitaForm: FormGroup;
  salvando = false;

  categorias = [
    {
      value: 'MENSALIDADE',
      label: 'Mensalidade',
    },
    {
      value: 'MATRICULA',
      label: 'Matrícula',
    },
    {
      value: 'AVALIACAO',
      label: 'Avaliação',
    },
    {
      value: 'PERSONAL',
      label: 'Personal',
    },
    {
      value: 'OUTROS',
      label: 'Outros',
    },
  ];

  constructor(
    private fb: FormBuilder,
    private receitaService: ReceitaService,
    private dialogRef: MatDialogRef<ReceitaDialogComponent>,

    @Inject(MAT_DIALOG_DATA)
    public data: any,
  ) {
    this.receitaForm = this.fb.group({
      descricao: [data?.receita?.descricao || '', Validators.required],

      categoria: [data?.receita?.categoria || '', Validators.required],

      valor: [
        data?.receita?.valor || null,
        [Validators.required, Validators.min(0)],
      ],

      dataVencimento: [
        data?.receita?.dataVencimento || '',
        Validators.required,
      ],

      cliente: [data?.receita?.cliente || ''],

      observacoes: [data?.receita?.observacoes || ''],
    });
  }

  salvar(): void {
    if (this.receitaForm.invalid) {
      this.receitaForm.markAllAsTouched();
      return;
    }

    this.salvando = true;

    const request: ReceitaRequestDTO = {
      ...this.receitaForm.value,
      valor: Number(this.receitaForm.value.valor),
    };

    const operacao = this.data?.receita
      ? this.receitaService.atualizar(this.data.receita.id, request)
      : this.receitaService.criar(request);

    operacao.subscribe({
      next: (response) => {
        this.salvando = false;
        this.dialogRef.close(response);
      },

      error: (erro) => {
        console.error(erro);
        this.salvando = false;
      },
    });
  }

  fechar(): void {
    if (this.salvando) {
      return;
    }
    this.dialogRef.close();
  }

  campoInvalido(campo: string): boolean {
    const control = this.receitaForm.get(campo);

    return !!control && control.invalid && control.touched;
  }
}
