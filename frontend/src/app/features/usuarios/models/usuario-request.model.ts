import { PerfilUsuario } from './usuario-resumo-response.model';

export interface UsuarioRequestDTO {
  nome: string;
  cpf?: string;
  telefone: string;
  email: string;
  senha?: string;
  perfil: PerfilUsuario;
  ativo?: boolean;
}
