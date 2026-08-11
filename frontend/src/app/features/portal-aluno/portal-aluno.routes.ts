import { Routes } from '@angular/router';
import { PortalAlunoLayoutComponent } from './layout/portal-aluno-layout.component';
import { alunoAuthGuard } from './data/aluno-auth.guard';

export const portalAlunoRoutes: Routes = [
  {
    path: '',
    component: PortalAlunoLayoutComponent,
    canActivate: [alunoAuthGuard],
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./home/portal-home.component').then((m) => m.PortalHomeComponent),
      },
      {
        path: 'treino',
        loadComponent: () =>
          import('./treino/portal-treino.component').then((m) => m.PortalTreinoComponent),
      },
      {
        path: 'avaliacoes',
        loadComponent: () =>
          import('./avaliacoes/portal-avaliacoes.component').then(
            (m) => m.PortalAvaliacoesComponent
          ),
      },
      {
        path: 'financeiro',
        loadComponent: () =>
          import('./financeiro/portal-financeiro.component').then(
            (m) => m.PortalFinanceiroComponent
          ),
      },
      {
        path: 'loja',
        loadComponent: () =>
          import('./loja/portal-loja.component').then((m) => m.PortalLojaComponent),
      },
      {
        path: 'noticias',
        loadComponent: () =>
          import('./noticias/portal-noticias.component').then((m) => m.PortalNoticiasComponent),
      },
      {
        path: 'mais',
        loadComponent: () =>
          import('./mais/portal-mais.component').then((m) => m.PortalMaisComponent),
      },
    ],
  },
];
