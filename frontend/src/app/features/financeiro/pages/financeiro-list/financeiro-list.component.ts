
import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-financeiro-list',
  imports: [
    MatCardModule,
    MatIconModule
],
  templateUrl: './financeiro-list.component.html',
  styleUrl: './financeiro-list.component.css'
})
export class FinanceiroListComponent {

}
