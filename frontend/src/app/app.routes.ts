import { Routes } from '@angular/router';
import { LayoutComponent } from './core/layout/layout.component';
import { HomePageComponent } from './features/home/pages/home-page.component';
import { AlunoFormComponent } from './features/alunos/pages/aluno-form.component';

export const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', component: HomePageComponent },
      { path: 'alunos', component: AlunoFormComponent }
    ]
  }
];
