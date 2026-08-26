/**
 * Modelos referentes à aba "Avaliações" do perfil do aluno.
 *
 * `nomeTemplate` e cada `nomeCampo`/`unidade` em `valores` são snapshots
 * tirados no momento em que a avaliação foi aplicada — não mudam
 * retroativamente mesmo que o template original seja editado ou inativado
 * depois.
 */
export interface ValorCampoResponseDTO {
  nomeCampo: string;
  unidade: string | null;
  valor: number;
}

export interface AvaliacaoRealizadaResponseDTO {
  id: string;
  alunoId: string;
  templateId: string;
  nomeTemplate: string;
  dataAvaliacao: string;
  valores: ValorCampoResponseDTO[];
  createdAt: string;
}
