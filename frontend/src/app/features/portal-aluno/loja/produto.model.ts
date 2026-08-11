export interface ProdutoLojaDTO {
  id: string;
  nome: string;
  descricao: string;
  preco: number;
  categoria: string;
  emEstoque: boolean;
  imagemUrl?: string;
}
