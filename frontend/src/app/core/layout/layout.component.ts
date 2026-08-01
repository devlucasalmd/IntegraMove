import { Component } from '@angular/core';
import {
  RouterOutlet,
  RouterLinkWithHref,
  RouterModule,
} from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSidenav, MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { HeaderSearchComponent } from './header/search/header-search.component';
import { HeaderNotificationsComponent } from './header/notifications/header-notifications.component';
import { HeaderUserMenuComponent } from './header/user-menu/header-user-menu.component';
@Component({
  standalone: true,
  selector: 'app-layout',
  imports: [
  RouterOutlet,
  MatToolbarModule,
  MatIconModule,
  MatButtonModule,
  MatSidenavModule,
  MatListModule,
  RouterLinkWithHref,
  MatExpansionModule,
  RouterModule,
  HeaderSearchComponent,
  HeaderNotificationsComponent,
  HeaderUserMenuComponent,
],
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css'],
})
export class LayoutComponent {
  isMobile = false;
  treinoAberto = false;
  financasAberto = false;
  administradorAberto = false;

  constructor(
    private breakpointObserver: BreakpointObserver,
  ) {
    this.breakpointObserver
      .observe([Breakpoints.Handset, '(max-width: 900px)'])
      .subscribe((result) => {
        this.isMobile = result.matches;
      });
  }

  fecharMenuMobile(drawer: MatSidenav): void {
    if (this.isMobile) {
      drawer.close();
    }
  }

  toggleTreino(): void {
    this.treinoAberto = !this.treinoAberto;
  }

  toggleFinancas(): void {
    this.financasAberto = !this.financasAberto;
  }

  toggleAdministrador(): void {
    this.administradorAberto = !this.administradorAberto;
  }

}
