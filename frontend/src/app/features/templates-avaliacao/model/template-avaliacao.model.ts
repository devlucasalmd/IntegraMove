/**
 * Modelos referentes à tela de gestão de templates de avaliação física
 * (`/administrador/avaliacao`).
 *
 * Um template define quais campos (medidas) compõem uma avaliação física
 * (ex.: "Carga supino" em kg, "Percentual de gordura" em %). Ao aplicar uma
 * avaliação em um aluno (feature `avaliacoes`), os valores de `nomeCampo` e
 * `unidade` são copiados (snapshot) para a avaliação realizada — por isso
 * os campos de um template já utilizado não podem mais ser alterados.
 */
export interface CampoTemplateResponseDTO {
  nome: string;
  unidade: string | null;
  ordem: number;
}

export interface TemplateAvaliacaoResponseDTO {
  id: string;
  nome: string;
  descricao: string;
  ativo: boolean;
  campos: CampoTemplateResponseDTO[];
  createdAt: string;
  updatedAt: string;
  /**
   * `true` quando já existe ao menos uma `AvaliacaoRealizada` aplicada com
   * este template. Nesse caso o backend rejeita qualquer alteração na lista
   * de campos (`CamposTemplateAvaliacaoImutaveisException`).
   */
  possuiAvaliacoesVinculadas: boolean;
}
