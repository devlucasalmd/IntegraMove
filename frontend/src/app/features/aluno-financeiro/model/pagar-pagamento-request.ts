import { FormaPagamento } from './pagamento-response.model';

export interface PagarPagamentoRequestDTO {
  formaPagamento: FormaPagamento;
  dataPagamento?: string | null;
}
