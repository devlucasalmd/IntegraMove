import { Routes } from '@angular/router';
import { ExercicioListComponent } from './pages/exercicios/exercicio-list.component';
import { TreinoListComponent } from './pages/treinos/list/treino-list.component';


export const treinoRoutes: Routes = [
  {
    path: '',
      children: [
        { path: '', component: TreinoListComponent},
        { path: 'exercicios', component: ExercicioListComponent },
        // { path: 'novo', component: ExercicioFormComponent },
      ]
  }
]
