import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  standalone: true,
  selector: 'app-header-user-menu',
  imports: [MatIconModule, MatMenuModule, MatDividerModule, RouterLink],
  templateUrl: './header-user-menu.component.html',
  styleUrls: ['./header-user-menu.component.css'],
})
export class HeaderUserMenuComponent {
  nomeUsuario = 'Lucas Amaral';
  nomeEmpresa = 'Integra Move';

  constructor(private router: Router) {}

  logout() {
    this.router.navigate(['/login']);
  }
}
