export type PerfilUsuario =
  | 'ADMINISTRADOR'
  | 'RECEPCIONISTA'
  | 'PROFESSOR'
  | 'PERSONAL';

export interface UsuarioResumoResponseDTO {
  id: string;
  nome: string;
  perfil: PerfilUsuario;
  ativo: boolean;
}
