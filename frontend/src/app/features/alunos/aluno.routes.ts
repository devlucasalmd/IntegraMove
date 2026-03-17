import { Routes } from '@angular/router';
import { AlunoFormComponent } from './pages/aluno-form/aluno-form.component';
import { AlunoListComponent } from './pages/aluno-list/aluno-list.component';
import { AlunoDetalheComponent } from './pages/aluno-detalhe/aluno-detalhe.component';
import { AvaliacaoListComponent } from '../avaliacoes/pages/avaliacao-list/avaliacao-list.component';
import { AvaliacaoFormComponent } from '../avaliacoes/pages/avaliacao-form/avaliacao-form.component';

export const alunosRoutes: Routes = [
  {
    path: '',
    children: [
      { path: '', component: AlunoListComponent },
      { path: 'novo', component: AlunoFormComponent },

      {
        path: ':id',
        component: AlunoDetalheComponent,
        children: [
          { path: '', redirectTo: 'resumo', pathMatch: 'full' },
          // { path: 'resumo', component: AlunoResumoComponent },
          { path: 'avaliacoes', component: AvaliacaoListComponent },
          { path: 'avaliacoes/nova', component: AvaliacaoFormComponent }
        ]
      }
    ]
  }
];
