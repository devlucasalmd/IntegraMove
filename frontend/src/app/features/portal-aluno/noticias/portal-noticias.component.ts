import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { NoticiasService } from './noticias.service';
import { NoticiaDTO } from './noticia.model';

@Component({
  selector: 'app-portal-noticias',
  standalone: true,
  imports: [CommonModule, MatIconModule, MatProgressSpinnerModule],
  templateUrl: './portal-noticias.component.html',
  styleUrl: './portal-noticias.component.css',
})
export class PortalNoticiasComponent implements OnInit {
  private readonly noticiasService = inject(NoticiasService);

  protected noticias: NoticiaDTO[] = [];
  protected carregando = true;

  ngOnInit(): void {
    this.noticiasService.listarNoticias().subscribe((noticias) => {
      this.noticias = noticias;
      this.carregando = false;
    });
  }

  protected iconePorCategoria(categoria?: string): string {
    switch (categoria) {
      case 'Promoção':
        return 'local_offer';
      case 'Novidade':
        return 'auto_awesome';
      default:
        return 'campaign';
    }
  }
}
