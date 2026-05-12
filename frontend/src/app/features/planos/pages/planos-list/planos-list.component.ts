import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-planos-list',
  imports: [
    CommonModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule
],
  templateUrl: './planos-list.component.html',
  styleUrl: './planos-list.component.css'
})
export class PlanosListComponent {

}
