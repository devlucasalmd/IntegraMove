import { AlunoResumoResponseDTO } from '../../alunos/models/aluno-resumo-response.model';
import { PlanoResponseDTO } from '../../planos/models/plano-response';
import { ReceitasResponseDTO } from '../../financeiro/receber/models/receita-response.model';
import { DespesaResponseDTO } from '../../financeiro/despesa/models/despesa-response.model';

/**
 * ---------------------------------------------------------------------
 * Dataset de FALLBACK usado exclusivamente quando as chamadas reais a
 * `AlunoService` / `PlanoService` / `ReceitaService` / `DespesaService`
 * falham (ex.: backend fora do ar). O objetivo é permitir que o dashboard
 * continue navegável em vez de travar a tela com um erro bloqueante —
 * quando isso acontece, o componente sinaliza `usandoDadosExemploAlunos`
 * / `usandoDadosExemploFinanceiro` para exibir uma nota discreta ("dados
 * de exemplo") nos cards afetados.
 *
 * Os shapes reaproveitam os DTOs reais para manter o componente
 * consistente com o resto da aplicação — o dado em si é inventado.
 * ---------------------------------------------------------------------
 */

// TODO: mock (fallback) — lista de alunos 100% inventada, usada só quando `AlunoService.listarAlunos()` falha.
export const ALUNOS_MOCK_FALLBACK: AlunoResumoResponseDTO[] = [
  { id: 'f-a1', nome: 'Ana Beatriz Ferreira', nomePlano: 'Mensal', status: 'ATIVO', pagamento: 'FEITO' },
  { id: 'f-a2', nome: 'Bruno Carvalho Lima', nomePlano: 'Trimestral', status: 'ATIVO', pagamento: 'EM_ABERTO' },
  { id: 'f-a3', nome: 'Camila Rodrigues Santos', nomePlano: 'Anual', status: 'ATIVO', pagamento: 'FEITO' },
  { id: 'f-a4', nome: 'Diego Almeida Souza', nomePlano: 'Semestral', status: 'ATIVO', pagamento: 'A_VENCER' },
  { id: 'f-a5', nome: 'Elaine Cristina Pereira', nomePlano: 'Mensal', status: 'ATIVO', pagamento: 'VENCIDO' },
  { id: 'f-a6', nome: 'Fábio Henrique Costa', nomePlano: 'Trimestral', status: 'INATIVO', pagamento: 'VENCIDO' },
  { id: 'f-a7', nome: 'Gabriela Martins Oliveira', nomePlano: 'Mensal', status: 'ATIVO', pagamento: 'FEITO' },
  { id: 'f-a8', nome: 'Henrique Barbosa Silva', nomePlano: 'Anual', status: 'ATIVO', pagamento: 'FEITO' },
  { id: 'f-a9', nome: 'Isabela Nunes Rocha', nomePlano: 'Semestral', status: 'ATIVO', pagamento: 'A_VENCER' },
  { id: 'f-a10', nome: 'João Vitor Ramos', nomePlano: 'Mensal', status: 'ATIVO', pagamento: 'EM_ABERTO' },
  { id: 'f-a11', nome: 'Karina Dias Moreira', nomePlano: 'Trimestral', status: 'ATIVO', pagamento: 'FEITO' },
  { id: 'f-a12', nome: 'Leonardo Teixeira Gomes', nomePlano: 'Mensal', status: 'INATIVO', pagamento: 'EM_ABERTO' },
  { id: 'f-a13', nome: 'Mariana Castro Alves', nomePlano: 'Anual', status: 'ATIVO', pagamento: 'FEITO' },
  { id: 'f-a14', nome: 'Natália Freitas Cardoso', nomePlano: 'Semestral', status: 'ATIVO', pagamento: 'VENCIDO' },
  { id: 'f-a15', nome: 'Otávio Pinheiro Duarte', nomePlano: 'Mensal', status: 'ATIVO', pagamento: 'FEITO' },
  { id: 'f-a16', nome: 'Patrícia Vieira Monteiro', nomePlano: 'Trimestral', status: 'ATIVO', pagamento: 'A_VENCER' },
];

// TODO: mock (fallback) — planos 100% inventados (nomes batendo com `ALUNOS_MOCK_FALLBACK.nomePlano`).
export const PLANOS_MOCK_FALLBACK: PlanoResponseDTO[] = [
  { id: 'f-p1', nome: 'Mensal', valor: 99.9, descricao: 'Acesso livre à academia, renovação mensal.', periodicidade: 'MENSAL', duracaoDias: 30, ativo: true },
  { id: 'f-p2', nome: 'Trimestral', valor: 269.9, descricao: 'Plano trimestral com desconto progressivo.', periodicidade: 'TRIMESTRAL', duracaoDias: 90, ativo: true },
  { id: 'f-p3', nome: 'Semestral', valor: 479.9, descricao: 'Plano semestral com aulas coletivas inclusas.', periodicidade: 'SEMESTRAL', duracaoDias: 180, ativo: true },
  { id: 'f-p4', nome: 'Anual', valor: 899.9, descricao: 'Plano anual, melhor custo-benefício.', periodicidade: 'ANUAL', duracaoDias: 365, ativo: true },
];

// TODO: mock (fallback) — receitas por categoria 100% inventadas, usadas só quando `ReceitaService.listarReceitas()` falha.
export const RECEITAS_MOCK_FALLBACK: ReceitasResponseDTO[] = [
  { categoria: 'Mensalidades', total: 15200, recebido: 12400, pendente: 2800 },
  { categoria: 'Personal Training', total: 3200, recebido: 2600, pendente: 600 },
  { categoria: 'Produtos', total: 1450, recebido: 1450, pendente: 0 },
  { categoria: 'Avaliações Físicas', total: 980, recebido: 780, pendente: 200 },
];

// TODO: mock (fallback) — despesas 100% inventadas, usadas só quando `DespesaService.listarDespesas()` falha.
export const DESPESAS_MOCK_FALLBACK: DespesaResponseDTO[] = [
  {
    id: 'f-d1',
    descricao: 'Aluguel do espaço',
    categoria: 'Aluguel',
    valor: 3800,
    dataVencimento: '2026-08-05',
    dataPagamento: null,
    formaPagamento: null,
    status: 'EM_ABERTO',
    fornecedor: 'Imobiliária Central',
    observacoes: null,
  },
  {
    id: 'f-d2',
    descricao: 'Manutenção de esteiras',
    categoria: 'Equipamentos',
    valor: 1200,
    dataVencimento: '2026-07-20',
    dataPagamento: '2026-07-19',
    formaPagamento: 'PIX',
    status: 'PAGA',
    fornecedor: 'Fit Equipamentos',
    observacoes: null,
  },
  {
    id: 'f-d3',
    descricao: 'Folha de pagamento',
    categoria: 'Salários',
    valor: 9800,
    dataVencimento: '2026-08-05',
    dataPagamento: null,
    formaPagamento: null,
    status: 'EM_ABERTO',
    fornecedor: null,
    observacoes: null,
  },
  {
    id: 'f-d4',
    descricao: 'Conta de energia elétrica',
    categoria: 'Energia',
    valor: 640,
    dataVencimento: '2026-07-28',
    dataPagamento: null,
    formaPagamento: null,
    status: 'VENCIDA',
    fornecedor: 'Companhia de Energia',
    observacoes: null,
  },
  {
    id: 'f-d5',
    descricao: 'Conta de água',
    categoria: 'Água',
    valor: 210,
    dataVencimento: '2026-08-10',
    dataPagamento: null,
    formaPagamento: null,
    status: 'A_VENCER',
    fornecedor: 'Saneamento Municipal',
    observacoes: null,
  },
  {
    id: 'f-d6',
    descricao: 'Internet fibra',
    categoria: 'Internet',
    valor: 180,
    dataVencimento: '2026-07-15',
    dataPagamento: '2026-07-14',
    formaPagamento: 'Boleto',
    status: 'PAGA',
    fornecedor: 'NetFibra',
    observacoes: null,
  },
  {
    id: 'f-d7',
    descricao: 'Campanha de mídia social',
    categoria: 'Marketing',
    valor: 550,
    dataVencimento: '2026-08-12',
    dataPagamento: null,
    formaPagamento: null,
    status: 'A_VENCER',
    fornecedor: 'Agência Impulso',
    observacoes: null,
  },
  {
    id: 'f-d8',
    descricao: 'Revisão de equipamentos de musculação',
    categoria: 'Equipamentos',
    valor: 320,
    dataVencimento: '2026-07-30',
    dataPagamento: null,
    formaPagamento: null,
    status: 'VENCIDA',
    fornecedor: 'Fit Equipamentos',
    observacoes: null,
  },
];
