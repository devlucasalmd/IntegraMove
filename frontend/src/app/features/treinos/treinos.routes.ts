import { Routes } from '@angular/router';
import { ExercicioListComponent } from './pages/exercicios/exercicio-list.component';
import { TreinoListComponent } from './pages/treinos/list/treino-list.component';
import { ExercicioFormDialogComponent } from './pages/exercicios/exercicio-form-dialog/exercicio-form-dialog.component';
import { TreinoDetailComponent } from './pages/treinos/treino-detail/treino-detail.component';


export const treinoRoutes: Routes = [
  {
    path: '',
      children: [
        { path: '', redirectTo: 'treinos', pathMatch: 'full' },
        { path: 'treinos', component: TreinoListComponent },
        { path: 'treinos/:id', component: TreinoDetailComponent },
        { path: 'exercicios', component: ExercicioListComponent },
        // { path: 'exercicios/:id', component: ExercicioDetalheComponent },
        { path: 'exercicios/novo', component: ExercicioFormDialogComponent },
      ]
  }
]
