import { PerfilUsuario } from './usuario-resumo-response.model';

export interface UsuarioDetalheResponseDTO {
  id: string;
  nome: string;
  cpf: string;
  telefone: string;
  email: string;
  perfil: PerfilUsuario;
  ativo: boolean;
}
