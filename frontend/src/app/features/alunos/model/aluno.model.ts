export interface AlunoRequestDTO {
  nome: string;
  dataNascimento: string;
  cpf: string;
  genero: string;
  telefone?: string;
  email: string;
  ativo: boolean;
  endereco?: Endereco | null;
}

export interface Endereco{
  cep: string;
  estado: string;
  cidade: string;
  rua: string;
  numero: string;
  bairro: string;
}
