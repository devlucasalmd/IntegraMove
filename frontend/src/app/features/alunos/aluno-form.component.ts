import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  Validators,
  FormGroup,
} from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';
import { AlunoService } from '../../core/services/aluno.service';
import { AlunoRequestDTO } from '../model/aluno.model';
@Component({
  selector: 'app-aluno-form',
  standalone: true,
  imports: [
    CommonModule,
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
  ],
  templateUrl: './aluno-form.component.html',
  styleUrls: ['./aluno-form.component.css'],
})
export class AlunoFormComponent {
  alunoForm: FormGroup;

  generos = ['MASCULINO', 'FEMININO', 'OUTROS'];

  constructor(
    private fb: FormBuilder,
    private alunoService: AlunoService,
  ) {
    this.alunoForm = this.fb.group({
      nome: ['', Validators.required],
      dataNascimento: ['', Validators.required],
      cpf: ['', Validators.required],
      genero: ['', Validators.required],
      telefone: [''],
      email: ['', [Validators.required, Validators.email]],
      enderecoDTO: this.fb.group({
        rua: [''],
        numero: [''],
        bairro: [''],
        cidade: [''],
        estado: [''],
        cep: [''],
      }),
      ativo: [true],
    });
  }

  private formatarData(data: Date): string {
    return data.toLocaleDateString('sv-SE');
  }

  private formatarCpf(cpf: string): string {

  const numeros = cpf.replace(/\D/g, '');

  if (numeros.length !== 11) {
    return cpf;
  }

  return numeros.replace(
    /(\d{3})(\d{3})(\d{3})(\d{2})/,
    '$1.$2.$3-$4'
  );
}

  salvar() {
    if (this.alunoForm.invalid) return;

    const formValue = this.alunoForm.value;

    const aluno: AlunoRequestDTO = {
      nome: formValue.nome,
      dataNascimento: this.formatarData(formValue.dataNascimento),
      cpf: this.formatarCpf(formValue.cpf),
      genero: formValue.genero,
      telefone: formValue.telefone,
      email: formValue.email,
      ativo: !!formValue.ativo,
      enderecoDTO: formValue.enderecoDTO,
    };

    console.log('Dados sendo enviados: ', formValue);

    this.alunoService.cadastrarAluno(aluno).subscribe({
      next: (response) => {
        console.log('Aluno salvo com sucesso:', response);
        this.alunoForm.reset();
      },
      error: (err) => {
        console.error('Erro ao salvar aluno:', err);
      },
    });
  }

  fechar() {
    return;
  }
}
