import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { AuthAlunoService } from '../data/auth-aluno.service';

@Component({
  selector: 'app-portal-aluno-layout',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, RouterOutlet, MatIconModule],
  templateUrl: './portal-aluno-layout.component.html',
  styleUrl: './portal-aluno-layout.component.css',
})
export class PortalAlunoLayoutComponent {
  private readonly auth = inject(AuthAlunoService);
  private readonly router = inject(Router);

  protected readonly nome = this.auth.nome;
  protected readonly alunoId = this.auth.alunoId;
  protected readonly menuAberto = signal(false);

  protected get primeiroNome(): string {
    const nome = this.nome();
    return nome ? nome.split(' ')[0] : 'Aluno';
  }

  protected toggleMenu(): void {
    this.menuAberto.update((v) => !v);
  }

  protected fecharMenu(): void {
    this.menuAberto.set(false);
  }

  protected sair(): void {
    this.auth.logout();
    this.router.navigate(['/aluno/login']);
  }
}
