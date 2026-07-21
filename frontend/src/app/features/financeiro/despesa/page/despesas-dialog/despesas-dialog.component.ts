import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { DespesaService } from '../../service/despesa.service';
import { DespesaResponseDTO } from '../../models/despesa-response.model';
import { DespesaRequestDTO } from '../../models/despesa-request.model';


@Component({
  selector: 'app-despesa-dialog',
  standalone: true,
  templateUrl: './despesas-dialog.component.html',
  styleUrls: ['./despesas-dialog.component.css'],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule
  ]
})
export class DespesaDialogComponent {

  despesaForm: FormGroup;
  salvando = false;

  categorias = [
    { value: 'ALUGUEL', label: 'Aluguel' },
    { value: 'LUZ', label: 'Luz' },
    { value: 'AGUA', label: 'Água' },
    { value: 'INTERNET', label: 'Internet' },
    { value: 'SALARIO', label: 'Salário' },
    { value: 'MANUTENCAO', label: 'Manutenção' },
    { value: 'EQUIPAMENTO', label: 'Equipamento' },
    { value: 'MARKETING', label: 'Marketing' },
    { value: 'CONTABILIDADE', label: 'Contabilidade' },
    { value: 'SISTEMA', label: 'Sistema' },
    { value: 'OUTROS', label: 'Outros' }
  ];

  constructor(
    private fb: FormBuilder,
    private despesaService: DespesaService,
    private dialogRef: MatDialogRef<DespesaDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { despesa?: DespesaResponseDTO } | null
  ) {

    const despesa = data?.despesa;

    this.despesaForm = this.fb.group({
      descricao: [despesa?.descricao ?? '', Validators.required],
      categoria: [despesa?.categoria ?? '', Validators.required],
      valor: [despesa?.valor ?? null, Validators.required],
      dataVencimento: [despesa?.dataVencimento ?? '', Validators.required],
      fornecedor: [despesa?.fornecedor ?? ''],
      observacoes: [despesa?.observacoes ?? '']
    });
  }

  salvar(): void {

    if (this.despesaForm.invalid) {
      this.despesaForm.markAllAsTouched();
      return;
    }

    this.salvando = true;

    const request: DespesaRequestDTO = {
      ...this.despesaForm.value,
      valor: Number(this.despesaForm.value.valor)
    };

    const operacao = this.data?.despesa
      ? this.despesaService.atualizar(this.data.despesa.id, request)
      : this.despesaService.criar(request);

    operacao.subscribe({
      next: response => {
        this.salvando = false;
        this.dialogRef.close(response);
      },
      error: erro => {
        console.error(erro);
        this.salvando = false;
      }
    });
  }

  fechar(): void {
    if (!this.salvando) {
      this.dialogRef.close();
    }
  }

  campoInvalido(campo: string): boolean {
    const control = this.despesaForm.get(campo);
    return !!control && control.invalid && control.touched;
  }


}
