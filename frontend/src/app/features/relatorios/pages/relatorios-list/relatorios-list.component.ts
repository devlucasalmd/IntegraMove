import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-relatorios-list',
  imports: [
    CommonModule,
    MatCardModule,
    MatIconModule
],
  templateUrl: './relatorios-list.component.html',
  styleUrl: './relatorios-list.component.css'
})
export class RelatoriosListComponent {

}
