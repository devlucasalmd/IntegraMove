import { Routes } from '@angular/router';
import { FinanceiroListComponent } from './pages/financeiro-list/financeiro-list.component';
import { DespesasComponent } from './despesa/page/despesas.component';
import { ReceitasComponent } from './receber/pages/receitas.component';

export const financeiroRoutes: Routes = [

  { path: '', component: FinanceiroListComponent },
  { path: 'despesas', component: DespesasComponent},
  { path: 'receitas', component: ReceitasComponent}

];
