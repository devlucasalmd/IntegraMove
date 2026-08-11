import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthAlunoService } from './auth-aluno.service';

/**
 * Protege as rotas do Portal do Aluno.
 * Redireciona para o login caso não haja sessão ativa, ou caso o
 * :alunoId da URL não corresponda ao aluno logado.
 */
export const alunoAuthGuard: CanActivateFn = (route) => {
  const auth = inject(AuthAlunoService);
  const router = inject(Router);

  const alunoIdRota = route.paramMap.get('alunoId');

  if (!auth.estaLogado() || auth.alunoId() !== alunoIdRota) {
    return router.createUrlTree(['/aluno/login']);
  }

  return true;
};
