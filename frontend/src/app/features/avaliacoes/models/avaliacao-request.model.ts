/**
 * Payload de `POST /avaliacoes`.
 *
 * `valores` deve conter exatamente os mesmos `nomeCampo` definidos no
 * template selecionado (nem a mais, nem a menos) — como o formulário é
 * gerado dinamicamente a partir dos campos do template escolhido, isso é
 * garantido naturalmente pela UI.
 */
export interface ValorCampoRequestDTO {
  nomeCampo: string;
  valor: number;
}

export interface AvaliacaoRealizadaRequestDTO {
  alunoId: string;
  templateId: string;
  dataAvaliacao: string;
  valores: ValorCampoRequestDTO[];
}
