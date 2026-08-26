/**
 * Modelos referentes à aba "Vendas" do perfil do aluno.
 *
 * `GET /vendas/aluno/{alunoId}` retorna a lista de vendas do aluno.
 * `POST /vendas` registra uma nova venda (PLANO, AVALIACAO, DIARIA ou PRODUTO).
 *
 * Quando `tipo` é PLANO, a venda gera automaticamente um Contrato — nesse
 * caso `contratoId` vem preenchido na resposta.
 */
export type TipoVenda = 'PLANO' | 'AVALIACAO' | 'DIARIA' | 'PRODUTO';
export type StatusVenda = 'PENDENTE' | 'CONCLUIDA' | 'CANCELADA';
export type Periodicidade = 'MENSAL' | 'TRIMESTRAL' | 'SEMESTRAL' | 'ANUAL';

export interface VendaResponseDTO {
  id: string;
  alunoId: string;
  tipo: TipoVenda;
  planoId: string | null;
  descricao: string | null;
  valor: number;
  periodicidade: Periodicidade | null;
  dataVenda: string;
  status: StatusVenda;
  contratoId: string | null;
  createdAt: string;
  updatedAt: string;
}

/**
 * Payload de `POST /vendas`.
 *
 * Para `tipo = PLANO`: preencher `planoId` e `diaVencimento`
 * (obrigatório) e `permiteRenovacaoAutomatica`; `descricao`/`valor` não se
 * aplicam (o valor é derivado do plano no backend).
 *
 * Para os demais tipos: preencher `descricao` e `valor`; `planoId`,
 * `diaVencimento` e `permiteRenovacaoAutomatica` não se aplicam.
 */
export interface VendaRequestDTO {
  alunoId: string;
  tipo: TipoVenda;
  planoId: string | null;
  descricao: string | null;
  valor: number | null;
  dataVenda: string;
  diaVencimento: number | null;
  permiteRenovacaoAutomatica: boolean | null;
}
