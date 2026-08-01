export type TipoUsuario = 'admin' | 'instrutor' | 'recepcao';

export type StatusUsuario = 'ativo' | 'inativo';

export interface Usuario {
  id: string;
  nome: string;
  email: string;
  telefone: string;
  tipo: TipoUsuario;
  status: StatusUsuario;
  dataCadastro: Date;
}

export interface UsuarioFormValue {
  nome: string;
  email: string;
  telefone: string;
  tipo: TipoUsuario;
}

export const TIPOS_USUARIO: { valor: TipoUsuario; label: string }[] = [
  { valor: 'admin', label: 'Administrador' },
  { valor: 'instrutor', label: 'Instrutor' },
  { valor: 'recepcao', label: 'Recepção' },
];
