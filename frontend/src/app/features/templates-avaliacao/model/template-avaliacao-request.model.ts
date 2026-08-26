/**
 * Payloads de criação/atualização de template de avaliação física.
 */
export interface CampoTemplateRequestDTO {
  nome: string;
  unidade: string | null;
  ordem: number;
}

/**
 * Payload de `POST /templates-avaliacao`.
 *
 * Não possui campo `ativo` — todo template novo nasce ativo por padrão no
 * backend. É obrigatório informar ao menos 1 campo.
 */
export interface TemplateAvaliacaoRequestDTO {
  nome: string;
  descricao: string;
  campos: CampoTemplateRequestDTO[];
}

/**
 * Payload de `PUT /templates-avaliacao/{id}`.
 *
 * `campos` é opcional (nullable): enviar `null` quando o template já possui
 * avaliações vinculadas (`possuiAvaliacoesVinculadas === true`) para que o
 * backend preserve os campos existentes sem tentar validá-los como uma
 * alteração.
 */
export interface AtualizarTemplateAvaliacaoRequestDTO {
  nome: string;
  descricao: string;
  ativo: boolean;
  campos: CampoTemplateRequestDTO[] | null;
}
