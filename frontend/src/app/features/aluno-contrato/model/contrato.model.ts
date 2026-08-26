/**
 * Modelos referentes à aba "Contratos" do perfil do aluno.
 *
 * `GET /contratos/aluno/{alunoId}` retorna a lista de contratos do aluno.
 * Não existe criação manual de contrato nesta aba — um contrato só é
 * criado indiretamente ao registrar uma Venda do tipo PLANO.
 */
export type StatusContrato = 'ATIVO' | 'ENCERRADO' | 'CANCELADO';

export interface ContratoResponseDTO {
  id: string;
  alunoId: string;
  vendaId: string;
  planoId: string;
  dataInicio: string;
  dataFim: string;
  diaVencimento: number | null;
  permiteRenovacaoAutomatica: boolean;
  status: StatusContrato;
  documentoUrl: string | null;
  createdAt: string;
  updatedAt: string;
}
