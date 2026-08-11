import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { AuthAlunoService } from '../data/auth-aluno.service';

@Component({
  selector: 'app-portal-mais',
  standalone: true,
  imports: [CommonModule, RouterLink, MatIconModule],
  templateUrl: './portal-mais.component.html',
  styleUrl: './portal-mais.component.css',
})
export class PortalMaisComponent {
  private readonly auth = inject(AuthAlunoService);
  private readonly router = inject(Router);

  protected readonly nome = this.auth.nome;

  protected sair(): void {
    this.auth.logout();
    this.router.navigate(['/aluno/login']);
  }
}
