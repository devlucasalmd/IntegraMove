import { Injectable, signal } from '@angular/core';

const STORAGE_KEY = 'imv_aluno_sessao';

interface SessaoAluno {
  alunoId: string;
  nome: string;
}

/**
 * Guarda a sessão do aluno logado no Portal do Aluno.
 *
 * TODO: quando o backend expuser um endpoint de autenticação real
 * (ex: POST /auth/aluno/login), substituir a persistência local por
 * um token JWT retornado pela API, mantendo a mesma interface pública
 * (login/logout/estaLogado) para não impactar quem já consome o serviço.
 */
@Injectable({
  providedIn: 'root',
})
export class AuthAlunoService {
  readonly alunoId = signal<string | null>(null);
  readonly nome = signal<string | null>(null);

  constructor() {
    this.restaurarSessao();
  }

  login(sessao: SessaoAluno): void {
    this.alunoId.set(sessao.alunoId);
    this.nome.set(sessao.nome);
    localStorage.setItem(STORAGE_KEY, JSON.stringify(sessao));
  }

  logout(): void {
    this.alunoId.set(null);
    this.nome.set(null);
    localStorage.removeItem(STORAGE_KEY);
  }

  estaLogado(): boolean {
    return !!this.alunoId();
  }

  private restaurarSessao(): void {
    const bruto = localStorage.getItem(STORAGE_KEY);
    if (!bruto) return;

    try {
      const sessao: SessaoAluno = JSON.parse(bruto);
      this.alunoId.set(sessao.alunoId);
      this.nome.set(sessao.nome);
    } catch {
      localStorage.removeItem(STORAGE_KEY);
    }
  }
}
