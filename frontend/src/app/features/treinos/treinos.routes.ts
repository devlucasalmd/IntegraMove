import { Routes } from '@angular/router';
import { ExercicioListComponent } from './pages/exercicios/exercicio-list.component';


export const treinoRoutes: Routes = [
  {
    path: '',
      children: [
        { path: 'exercicios', component: ExercicioListComponent },
        // { path: 'novo', component: ExercicioFormComponent },
      ]
  }
]
