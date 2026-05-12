import { Component } from '@angular/core';
import { RouterOutlet, RouterLinkWithHref, RouterModule, Router } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';

@Component({
  standalone: true,
  selector: 'app-layout',
  imports: [
    RouterOutlet,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatSidenavModule,
    MatListModule,
    MatMenuModule,
    RouterLinkWithHref,
    MatSidenavModule,
    MatExpansionModule,
    MatDividerModule,
    RouterModule
],
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})


export class LayoutComponent {

  constructor(private router: Router) {}

  logout() {
    this.router.navigate(['/login']);
  }
}
