import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';

import { MatDialog, MatDialogModule } from '@angular/material/dialog';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    MatDialogModule,
    MatButtonModule
],
  templateUrl: './home-page.component.html'
})
export class HomePageComponent {

  constructor(private dialog: MatDialog) {}
}
