import { Routes } from '@angular/router';
import { ExercicioListComponent } from './pages/exercicios/exercicio-list.component';


export const treinoRoutes: Routes = [
  {
    path: '',
      children: [
        { path: 'exercicio', component: ExercicioListComponent },
        // { path: 'novo', component: ExercicioFormComponent },
      ]
  }
]
