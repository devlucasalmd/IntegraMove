import { StatusAluno } from "./aluno-resumo-response.model";

export interface AlunoRequestDTO {
  nome: string;
  dataNascimento: string;
  cpf: string;
  genero: string;
  telefone?: string;
  email: string;
  status: StatusAluno;
  enderecoDTO?: EnderecoDTO | null;
}

export interface EnderecoDTO {
  cep: string;
  estado: string;
  cidade: string;
  rua: string;
  numero: string;
  bairro: string;
}
