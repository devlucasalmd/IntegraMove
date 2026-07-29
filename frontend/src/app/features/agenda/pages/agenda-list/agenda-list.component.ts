
import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-agenda-list',
  imports: [
    MatCardModule,
    MatIconModule
],
  templateUrl: './agenda-list.component.html',
  styleUrl: './agenda-list.component.css'
})
export class AgendaListComponent {

}
