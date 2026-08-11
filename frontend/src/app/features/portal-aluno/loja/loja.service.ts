import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { delay } from 'rxjs/operators';
import { ProdutoLojaDTO } from './produto.model';

/**
 * TODO: substituir os dados mockados abaixo por uma chamada real via
 * HttpClient assim que o backend expuser o endpoint de produtos, ex:
 *   GET http://localhost:8080/loja/produtos
 * A assinatura pública (listarProdutos) já está pronta para isso.
 */
@Injectable({
  providedIn: 'root',
})
export class LojaService {
  private readonly produtosMock: ProdutoLojaDTO[] = [
    {
      id: '1',
      nome: 'Camiseta Dry-Fit IntegraMove',
      descricao: 'Tecido leve com tecnologia de secagem rápida, ideal para treinos intensos.',
      preco: 79.9,
      categoria: 'Vestuário',
      emEstoque: true,
    },
    {
      id: '2',
      nome: 'Coqueteleira 700ml',
      descricao: 'Coqueteleira com misturador de aço inox, livre de BPA.',
      preco: 34.9,
      categoria: 'Acessórios',
      emEstoque: true,
    },
    {
      id: '3',
      nome: 'Whey Protein Concentrado 900g',
      descricao: 'Suplemento proteico sabor chocolate, 25g de proteína por dose.',
      preco: 149.9,
      categoria: 'Suplementos',
      emEstoque: true,
    },
    {
      id: '4',
      nome: 'Luva de Treino Feminina',
      descricao: 'Proteção e aderência para exercícios com peso livre.',
      preco: 59.9,
      categoria: 'Acessórios',
      emEstoque: false,
    },
    {
      id: '5',
      nome: 'Toalha de Treino Microfibra',
      descricao: 'Alta absorção e secagem rápida, tamanho 30x90cm.',
      preco: 24.9,
      categoria: 'Acessórios',
      emEstoque: true,
    },
    {
      id: '6',
      nome: 'Creatina Monohidratada 300g',
      descricao: 'Pura, sem sabor, ideal para ganho de força e performance.',
      preco: 89.9,
      categoria: 'Suplementos',
      emEstoque: true,
    },
  ];

  listarProdutos(): Observable<ProdutoLojaDTO[]> {
    return of(this.produtosMock).pipe(delay(300));
  }
}
