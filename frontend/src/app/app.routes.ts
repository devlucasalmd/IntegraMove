import { Routes } from '@angular/router';
import { LayoutComponent } from './core/layout/layout.component';
import { HomePageComponent } from './features/home/pages/home-page.component';
import { AlunoFormComponent } from './features/alunos/aluno-form.component';
import { AvaliacaoFormComponent } from './features/avaliacoes/avaliacao-form.component';

export const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', component: HomePageComponent },
      { path: 'alunos', component: AlunoFormComponent },
      { path: 'avaliacao', component: AvaliacaoFormComponent}
    ]
  }
];
