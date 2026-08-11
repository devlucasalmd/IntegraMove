import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { LojaService } from './loja.service';
import { ProdutoLojaDTO } from './produto.model';

@Component({
  selector: 'app-portal-loja',
  standalone: true,
  imports: [CommonModule, MatIconModule, MatProgressSpinnerModule],
  templateUrl: './portal-loja.component.html',
  styleUrl: './portal-loja.component.css',
})
export class PortalLojaComponent implements OnInit {
  private readonly lojaService = inject(LojaService);

  protected produtos: ProdutoLojaDTO[] = [];
  protected categoriaSelecionada = 'Todos';
  protected carregando = true;

  ngOnInit(): void {
    this.lojaService.listarProdutos().subscribe((produtos) => {
      this.produtos = produtos;
      this.carregando = false;
    });
  }

  protected get categorias(): string[] {
    return ['Todos', ...new Set(this.produtos.map((p) => p.categoria))];
  }

  protected get produtosFiltrados(): ProdutoLojaDTO[] {
    if (this.categoriaSelecionada === 'Todos') return this.produtos;
    return this.produtos.filter((p) => p.categoria === this.categoriaSelecionada);
  }

  protected selecionarCategoria(categoria: string): void {
    this.categoriaSelecionada = categoria;
  }
}
