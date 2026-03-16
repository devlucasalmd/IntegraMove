import { Routes } from '@angular/router';
import { AvaliacaoFormComponent } from './pages/avaliacao-form.component';

export const avaliacoesRoutes: Routes = [
  { path: 'avaliacao/:alunoId', component: AvaliacaoFormComponent }
];
