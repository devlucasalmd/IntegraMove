import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCheckboxModule,
    MatProgressSpinnerModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  private readonly fb = inject(FormBuilder);
  private readonly router = inject(Router);
  // private readonly authService = inject(AuthService);

  protected readonly hidePassword = signal(true);
  protected readonly isLoading = signal(false);
  protected readonly errorMessage = signal<string | null>(null);

  protected readonly form = this.fb.nonNullable.group({
    email: ['', [Validators.required, Validators.email]],
    senha: ['', [Validators.required, Validators.minLength(6)]],
    lembrar: [false],
  });

  protected togglePasswordVisibility(): void {
    this.hidePassword.update((v) => !v);
  }

  protected onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.errorMessage.set(null);
    this.isLoading.set(true);

    const { email, senha } = this.form.getRawValue();

    // TODO: substituir a simulação abaixo pela chamada real, ex:
    // this.authService.login(email, senha).subscribe({
    //   next: () => this.router.navigate(['/home']),
    //   error: (err) => {
    //     this.errorMessage.set('E-mail ou senha inválidos. Tente novamente.');
    //     this.isLoading.set(false);
    //   },
    // });
    setTimeout(() => {
      this.isLoading.set(false);

      if (senha.length < 8) {
        this.errorMessage.set('E-mail ou senha inválidos. Tente novamente.');
        return;
      }

      this.router.navigate(['/home']);
    }, 1200);
  }
}
