import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { cpfValidator } from './cpf.validator';
import { AuthAlunoService } from '../portal-aluno/data/auth-aluno.service';
import { AlunoService } from '../alunos/services/aluno.service';

@Component({
  selector: 'app-login-aluno',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
  ],
  templateUrl: './login-aluno.component.html',
  styleUrl: './login-aluno.component.scss',
})
export class LoginAlunoComponent {
  private readonly fb = inject(FormBuilder);
  private readonly router = inject(Router);
  private readonly authAlunoService = inject(AuthAlunoService);
  private readonly alunoService = inject(AlunoService);

  protected readonly hidePassword = signal(true);
  protected readonly isLoading = signal(false);
  protected readonly errorMessage = signal<string | null>(null);

  protected readonly form = this.fb.nonNullable.group({
    cpf: ['', [Validators.required, cpfValidator()]],
    senha: ['', [Validators.required, Validators.minLength(6)]],
  });

  protected togglePasswordVisibility(): void {
    this.hidePassword.update((v) => !v);
  }

  /** Aplica a máscara 000.000.000-00 enquanto o aluno digita. */
  protected onCpfInput(event: Event): void {
    const input = event.target as HTMLInputElement;
    const digits = input.value.replace(/\D/g, '').slice(0, 11);

    let formatted = digits;
    if (digits.length > 9) {
      formatted = `${digits.slice(0, 3)}.${digits.slice(3, 6)}.${digits.slice(6, 9)}-${digits.slice(9, 11)}`;
    } else if (digits.length > 6) {
      formatted = `${digits.slice(0, 3)}.${digits.slice(3, 6)}.${digits.slice(6, 9)}`;
    } else if (digits.length > 3) {
      formatted = `${digits.slice(0, 3)}.${digits.slice(3, 6)}`;
    }

    this.form.controls.cpf.setValue(formatted, { emitEvent: false });
  }

  protected onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.errorMessage.set(null);
    this.isLoading.set(true);

    const { senha } = this.form.getRawValue();

    // TODO: o backend ainda não expõe um endpoint de autenticação por CPF
    // (ex: POST /auth/aluno/login) que devolva o alunoId autenticado.
    // Enquanto isso não existe, validamos a senha localmente (simulação)
    // e buscamos o cadastro do aluno pela lista geral para popular a sessão.
    // Quando o endpoint real existir, substituir o bloco abaixo por:
    //   this.authService.login(cpfLimpo, senha).subscribe({...})
    if (senha.length < 8) {
      this.isLoading.set(false);
      this.errorMessage.set('CPF ou senha inválidos. Tente novamente.');
      return;
    }

    this.alunoService.listarAlunos().subscribe({
      next: (alunos) => {
        this.isLoading.set(false);
        const aluno = alunos[0];

        if (!aluno) {
          this.errorMessage.set('Não foi possível localizar seu cadastro. Fale com a recepção.');
          return;
        }

        this.authAlunoService.login({ alunoId: aluno.id, nome: aluno.nome });
        this.router.navigate(['/aluno', aluno.id]);
      },
      error: () => {
        this.isLoading.set(false);
        this.errorMessage.set('Não foi possível entrar agora. Tente novamente.');
      },
    });
  }
}
