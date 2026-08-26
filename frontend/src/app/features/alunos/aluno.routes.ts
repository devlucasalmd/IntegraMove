import { Routes } from '@angular/router';
import { AlunoFormComponent } from './pages/aluno-form/aluno-form.component';
import { AlunoListComponent } from './pages/aluno-list/aluno-list.component';
import { AlunoDetalheComponent } from './pages/aluno-detalhe/aluno-detalhe.component';
import { AvaliacaoListComponent } from '../avaliacoes/pages/avaliacao-list/avaliacao-list.component';
import { TreinoListComponent } from '../treinos/pages/treinos/list/treino-list.component';
import { AlunoTreinoListComponent } from '../aluno-treino/page/aluno-treino-list/aluno-treino-list.component';
import { AlunoTreinoFormComponent } from '../aluno-treino/page/aluno-treino-form/aluno-treino-form.component';
import { AlunoTreinoDetalheComponent } from '../aluno-treino/page/aluno-treino-detalhe/aluno-treino-detalhe.component';
import { AlunoFinanceiroComponent } from '../aluno-financeiro/page/aluno-financeiro.component';
import { AlunoResumoComponent } from '../aluno-resumo/page/aluno-resumo.component';
import { AlunoContratoComponent } from '../aluno-contrato/page/aluno-contrato.component';
import { AlunoVendasComponent } from '../aluno-vendas/page/aluno-vendas.component';

export const alunosRoutes: Routes = [
  {
    path: '',
    children: [
      { path: '', component: AlunoListComponent },
      { path: 'novo', component: AlunoFormComponent },

      {
        path: ':alunoId',
        component: AlunoDetalheComponent,
        children: [
          { path: '', redirectTo: 'resumo', pathMatch: 'full' },
          { path: 'resumo', component: AlunoResumoComponent },
          { path: 'contratos', component: AlunoContratoComponent },
          { path: 'vendas', component: AlunoVendasComponent },
          { path: 'financeiro', component: AlunoFinanceiroComponent},
          { path: 'avaliacoes', component: AvaliacaoListComponent },
          { path: 'treinos',
            children: [
            { path: '', component: AlunoTreinoListComponent},
            { path: 'nova', component: AlunoTreinoFormComponent},
            { path: ':fichaId', component: AlunoTreinoDetalheComponent}
          ]}
        ]
      }
    ]
  }
];
