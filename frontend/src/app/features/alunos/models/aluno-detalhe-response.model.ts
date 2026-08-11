import { StatusAluno } from "./aluno-resumo-response.model";

export interface AlunoDetalheResponseDTO {
  id: string;
  nome: string;
  dataNascimento?: string;
  cpf: string;
  genero?: string;
  telefone?: string;
  email?: string;
  status: StatusAluno;
  planoId?: string | null;
  nomePlano?: string | null;
  enderecoDTO?: EnderecoResponseDTO;
  // TODO: campo ainda não existe no backend — ver AlunoService.atualizarObservacao
  observacao?: string | null;
}

export interface EnderecoResponseDTO {
  cep?: string;
  estado?: string;
  cidade?: string;
  rua?: string;
  numero?: string;
  bairro?: string;
}
