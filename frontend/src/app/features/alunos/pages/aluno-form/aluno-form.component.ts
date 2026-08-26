import { Component, OnInit } from '@angular/core';
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
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';
import { TablerIconComponent, provideTablerIcons } from 'angular-tabler-icons';
import { provideNativeDateAdapter } from '@angular/material/core';
import { HttpErrorResponse } from '@angular/common/http';
import { AlunoService } from '../../services/aluno.service';
import { AlunoRequestDTO } from '../../models/aluno-request.model';
import { Router } from '@angular/router';

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
    MatFormFieldModule,
    MatInputModule,
    MatCheckboxModule,
    MatRadioModule,
    MatDatepickerModule,
    TablerIconComponent,
  ],
  templateUrl: './aluno-form.component.html',
  styleUrls: ['./aluno-form.component.css'],
  providers: [provideNativeDateAdapter(), provideTablerIcons({})],
})
export class AlunoFormComponent implements OnInit {

  alunoForm!: FormGroup;

  generos: string[] = [
    'MASCULINO',
    'FEMININO',
    'OUTRO'
  ];

  salvando = false;
  erroEnvio: string | null = null;

  constructor(
    private fb: FormBuilder,
    private alunoService: AlunoService,
    private router: Router
    ) {}

  ngOnInit(): void {
    this.criarFormulario();
  }

  private criarFormulario(): void {
    this.alunoForm = this.fb.group({
      nome: ['', Validators.required],
      cpf: ['', Validators.required],
      genero: ['', Validators.required],
      dataNascimento: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefone: ['', Validators.required],
      status: [true],

      enderecoDTO: this.fb.group({
        rua: [''],
        numero: [''],
        cep: [''],
        bairro: [''],
        cidade: [''],
        estado: ['']
      })
    });
  }

  salvar(): void {
    this.erroEnvio = null;

    if (this.alunoForm.invalid) {
      this.alunoForm.markAllAsTouched();
      return;
    }

    this.salvando = true;

    const formValue = this.alunoForm.getRawValue();

    const request: AlunoRequestDTO = {
      nome: formValue.nome,
      cpf: formValue.cpf,
      genero: formValue.genero,
      dataNascimento: this.formatarData(formValue.dataNascimento),
      telefone: formValue.telefone,
      email: formValue.email,
      status: formValue.status ? 'ATIVO' : 'INATIVO',
      enderecoDTO: {
        rua: formValue.enderecoDTO.rua,
        numero: formValue.enderecoDTO.numero,
        cep: formValue.enderecoDTO.cep,
        bairro: formValue.enderecoDTO.bairro,
        cidade: formValue.enderecoDTO.cidade,
        estado: formValue.enderecoDTO.estado
      }
    };

    this.alunoService.cadastrarAluno(request).subscribe({
      next: () => {
        this.salvando = false;
        this.fechar();
      },
      error: (erro: HttpErrorResponse) => {
        console.error('Erro ao salvar aluno:', erro);
        this.salvando = false;
        this.erroEnvio = this.extrairMensagemErro(erro);
      }
    });
  }

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

    return 'Não foi possível salvar o aluno. Verifique os dados e tente novamente.';
  }

  fechar(): void {
    this.router.navigate(['/alunos']);
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
}
