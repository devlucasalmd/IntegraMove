import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [
    ReactiveFormsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule
]
})
export class LoginComponent implements OnInit {

  form!: FormGroup;
  loading = false;

  constructor(
    private router:Router,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  entrar() {
    if (this.form.invalid) return;

    console.log('CLIQUEI');

    const { email, senha } = this.form.value;

    this.loading = true;

    setTimeout(() => {

    // 🔥 LOGIN MOCK
      if (email === 'admin@email.com' && senha === '123456') {

        localStorage.setItem('auth', 'true');
        this.router.navigate(['/']);

      } else {
          alert('Usuário ou senha inválidos');
      }

      this.loading = false;

    }, 1000);
  }
}
