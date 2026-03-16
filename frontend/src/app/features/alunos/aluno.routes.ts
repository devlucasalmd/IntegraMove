import { Routes } from '@angular/router';
import { AlunoFormComponent } from './pages/aluno-form/aluno-form.component';
import { AlunoListComponent } from './pages/aluno-list/aluno-list.component';

export const alunosRoutes: Routes = [
  { path: '', component: AlunoListComponent },
  { path: 'novo', component: AlunoFormComponent }
];
