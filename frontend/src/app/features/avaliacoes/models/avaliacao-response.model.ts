export interface AvaliacaoResponseDTO {
  id: string,
  alunoId: string;
  dataAvaliacao: string;

  remadaBracoD: number;
  remadaBracoE: number;

  elevacaoLatD: number;
  elevacaoLatE: number;

  extensaoJoelhoD: number;
  extensaoJoelhoE: number;

  flexaoJoelhoD: number;
  flexaoJoelhoE: number;

  extensaoQuadrilD: number;
  extensaoQuadrilE: number;
}
