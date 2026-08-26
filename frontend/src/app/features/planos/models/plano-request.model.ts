import { Periodicidade } from './plano-response';

/**
 * Payload de `POST /planos` e `PUT /planos/{id}`.
 *
 * O `PUT` exige o corpo completo (não é um PATCH parcial). Atenção especial
 * ao campo `valor`: o backend rejeita com HTTP 400 qualquer `PUT` cujo
 * `valor` seja diferente do valor já persistido (regra de negócio de
 * imutabilidade do valor do plano).
 */
export interface PlanoRequestDTO {
  nome: string;
  valor: number;
  descricao: string;
  periodicidade: Periodicidade;
  duracaoDias: number;
  ativo: boolean;
}
