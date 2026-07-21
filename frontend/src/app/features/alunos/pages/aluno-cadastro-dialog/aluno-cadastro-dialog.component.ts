import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { AlunoRequestDTO } from '../../models/aluno-request.model';
import { AlunoService } from '../../services/aluno.service';
import { AlunoDetalheResponseDTO } from '../../models/aluno-detalhe-response.model';

@Component({
  selector: 'app-aluno-cadastro-dialog',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    MatSlideToggleModule
  ],
  templateUrl: './aluno-cadastro-dialog.component.html',
  styleUrl: './aluno-cadastro-dialog.component.css'
})
export class AlunoCadastroDialogComponent implements OnInit {

  aluno?: AlunoDetalheResponseDTO;

  form!: FormGroup;

  modoEdicao = false;
  carregando = false;
  salvando = false;

  generos = [
    { value: 'MASCULINO', label: 'Masculino' },
    { value: 'FEMININO', label: 'Feminino' },
    { value: 'OUTRO', label: 'Outro' }
  ];

  statusOptions = [
    { value: 'ATIVO', label: 'Ativo' },
    { value: 'INATIVO', label: 'Inativo' }
  ];

  constructor(
    private fb: FormBuilder,
    private alunoService: AlunoService,
    private dialogRef: MatDialogRef<AlunoCadastroDialogComponent>,

    @Inject(MAT_DIALOG_DATA)
    public data: { alunoId: string }
  ) {}

  ngOnInit(): void {
    this.criarFormulario();
    this.carregarAluno();
  }

  criarFormulario(): void {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      cpf: ['', Validators.required],
      dataNascimento: [''],
      genero: [''],
      telefone: [''],
      email: ['', Validators.email],
      status: ['ATIVO', Validators.required],

      enderecoDTO: this.fb.group({
        cep: [''],
        estado: [''],
        cidade: [''],
        bairro: [''],
        rua: [''],
        numero: ['']
      })
    });

    this.form.disable();
  }

  carregarAluno(): void {

    if (!this.data?.alunoId) {
      console.error('alunoId não recebido no modal:', this.data);
      return;
    }

    this.carregando = true;

    this.alunoService.buscarAlunoPorId(this.data.alunoId).subscribe({
      next: (response) => {
        this.aluno = response;

        this.form.patchValue({
          nome: response.nome,
          cpf: response.cpf,
          dataNascimento: response.dataNascimento,
          genero: response.genero,
          telefone: response.telefone,
          email: response.email,
          status: response.status,
          enderecoDTO: {
            cep: response.enderecoDTO?.cep || '',
            estado: response.enderecoDTO?.estado || '',
            cidade: response.enderecoDTO?.cidade || '',
            bairro: response.enderecoDTO?.bairro || '',
            rua: response.enderecoDTO?.rua || '',
            numero: response.enderecoDTO?.numero || ''
          }
        });

        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar aluno:', erro);
        this.carregando = false;
      }
    });
  }

  habilitarEdicao(): void {
    this.modoEdicao = true;
    this.form.enable();
  }

  cancelarEdicao(): void {
    this.modoEdicao = false;
    this.form.disable();

    if (this.aluno) {
      this.carregarAluno();
    }
  }

  salvar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload: AlunoRequestDTO = this.form.getRawValue();

    this.salvando = true;

    this.alunoService.atualizarAluno(this.data.alunoId, payload).subscribe({
      next: () => {
        this.salvando = false;
        this.dialogRef.close(true);
      },
      error: (erro) => {
        console.error('Erro ao atualizar aluno:', erro);
        this.salvando = false;
      }
    });
  }

  fechar(): void {
    if (this.salvando) {
      return;
    }

    this.dialogRef.close(false);
  }

  alunoAtivo(): boolean {
    return this.form.get('status')?.value === 'ATIVO';
  }

  campoInvalido(campo: string): boolean {
    const control = this.form.get(campo);
    return !!control && control.invalid && control.touched;
  }

  campoEnderecoInvalido(campo: string): boolean {
    const control = this.form.get(`enderecoDTO.${campo}`);
    return !!control && control.invalid && control.touched;
  }
}
