import { Routes } from '@angular/router';
import { LayoutComponent } from './core/layout/layout.component';
import { DashboardPageComponent } from './features/dashboard/pages/dashboard-page/dashboard-page.component';


export const routes: Routes = [

  {
    path: 'login',
    loadComponent: () => import('./features/login/page/login.component')
      .then(m => m.LoginComponent)
  },
  {
    path: 'aluno/login',
    loadComponent: () =>
      import('./features/login-aluno/login-aluno.component').then(
        (m) => m.LoginAlunoComponent
      ),
  },
  {
    path: 'aluno/:alunoId',
    loadChildren: () =>
      import('./features/portal-aluno/portal-aluno.routes').then(
        (m) => m.portalAlunoRoutes
      ),
  },
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', component: DashboardPageComponent },
      {
        path: 'alunos',
        loadChildren: () => import('./features/alunos/aluno.routes')
          .then(m => m.alunosRoutes)
      },
      {
        path: 'treino',
        loadChildren: () => import('./features/treinos/treinos.routes')
          .then(m => m.treinoRoutes)
      },
      {
        path: 'planos',
        loadChildren: () => import('./features/planos/planos.route')
          .then(m => m.planosRoutes)
      },
      {
        path: 'financeiro',
        loadChildren: () => import('./features/financeiro/financeiro.route')
          .then(m => m.financeiroRoutes)
      },
      {
        path: 'relatorios',
        loadChildren: () => import('./features/relatorios/relatorios.route')
          .then(m => m.relatoriosRoutes)
      },
      {
        path: 'agenda',
        loadChildren: () => import('./features/agenda/agenda.route')
          .then(m => m.agendaRoutes)
      },
      // {
      //   path: 'estoque',
      //   loadChildren: () => import('./features/')
      //     .then(m => m.estoqueRoutes)
      // }
    ]
  }
];
