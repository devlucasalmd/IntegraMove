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

// TODO: importar o AuthService real do projeto quando ele existir,
// ex: import { AuthAlunoService } from '../../core/auth/auth-aluno.service';

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
  // private readonly authService = inject(AuthAlunoService);

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

    const { cpf, senha } = this.form.getRawValue();
    const cpfLimpo = cpf.replace(/\D/g, '');

    // TODO: substituir a simulação abaixo pela chamada real, ex:
    // this.authService.login(cpfLimpo, senha).subscribe({
    //   next: () => this.router.navigate(['/aluno/inicio']),
    //   error: () => {
    //     this.errorMessage.set('CPF ou senha inválidos. Tente novamente.');
    //     this.isLoading.set(false);
    //   },
    // });
    setTimeout(() => {
      this.isLoading.set(false);

      if (senha.length < 8) {
        this.errorMessage.set('CPF ou senha inválidos. Tente novamente.');
        return;
      }

      this.router.navigate(['/aluno/inicio']);
    }, 1200);
  }
}
