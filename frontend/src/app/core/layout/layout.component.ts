import { Component } from '@angular/core';
import {
  RouterOutlet,
  RouterLinkWithHref,
  RouterModule,
  Router,
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
    RouterModule,
  ],
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css'],
})
export class LayoutComponent {
  isMobile = false;
  treinoAberto = false;
  financasAberto = false;

  constructor(
    private router: Router,
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
  
  logout() {
    this.router.navigate(['/login']);
  }
}
