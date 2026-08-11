import { Component, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatMenuModule } from '@angular/material/menu';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { UsuariosFormComponent } from '../../pages/usuarios-form/usuarios-form.component';

type FiltroTipo = 'todos' | TipoUsuario;
type FiltroStatus = 'todos' | 'ativo' | 'inativo';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatChipsModule,
    MatMenuModule,
    MatDialogModule,
    MatProgressBarModule,
  ],
  templateUrl: './usuarios-list.component.html',
  styleUrl: './usuarios-list.component.css',
})
export class UsuariosComponent implements OnInit {
  colunas = ['nome', 'email', 'telefone', 'tipo', 'status', 'dataCadastro', 'acoes'];
  tiposUsuario = TIPOS_USUARIO;

  carregando = signal(true);
  usuarios = signal<Usuario[]>([]);
  termoBusca = signal('');
  filtroTipo = signal<FiltroTipo>('todos');
  filtroStatus = signal<FiltroStatus>('todos');

  usuariosFiltrados = computed(() => {
    const termo = this.termoBusca().toLowerCase().trim();
    const tipo = this.filtroTipo();
    const status = this.filtroStatus();

    return this.usuarios().filter((u) => {
      const combinaTermo =
        !termo || u.nome.toLowerCase().includes(termo) || u.email.toLowerCase().includes(termo);
      const combinaTipo = tipo === 'todos' || u.tipo === tipo;
      const combinaStatus = status === 'todos' || u.status === status;
      return combinaTermo && combinaTipo && combinaStatus;
    });
  });

  constructor(private dialog: MatDialog) {}

  ngOnInit(): void {
    this.usuarios.set(this.gerarUsuariosMock());
    this.carregando.set(false);
  }

  onBuscaChange(valor: string): void {
    this.termoBusca.set(valor);
  }

  onFiltroTipoChange(valor: FiltroTipo): void {
    this.filtroTipo.set(valor);
  }

  onFiltroStatusChange(valor: FiltroStatus): void {
    this.filtroStatus.set(valor);
  }

  labelTipo(tipo: TipoUsuario): string {
    return this.tiposUsuario.find((t) => t.valor === tipo)?.label ?? tipo;
  }

  abrirNovoUsuario(): void {
    const dialogRef = this.dialog.open(UsuariosFormComponent, {
      width: '480px',
      data: { usuario: null },
    });

    dialogRef.afterClosed().subscribe((resultado: UsuarioFormValue | null) => {
      if (!resultado) {
        return;
      }

      const novoUsuario: Usuario = {
        id: crypto.randomUUID(),
        nome: resultado.nome,
        email: resultado.email,
        telefone: resultado.telefone,
        tipo: resultado.tipo,
        status: 'ativo',
        dataCadastro: new Date(),
      };

      this.usuarios.update((lista) => [novoUsuario, ...lista]);
    });
  }

  editarUsuario(usuario: Usuario): void {
    const dialogRef = this.dialog.open(UsuariosFormComponent, {
      width: '480px',
      data: { usuario },
    });

    dialogRef.afterClosed().subscribe((resultado: UsuarioFormValue | null) => {
      if (!resultado) {
        return;
      }

      this.usuarios.update((lista) =>
        lista.map((u) => (u.id === usuario.id ? { ...u, ...resultado } : u))
      );
    });
  }

  alternarStatus(usuario: Usuario): void {
    this.usuarios.update((lista) =>
      lista.map((u) =>
        u.id === usuario.id ? { ...u, status: u.status === 'ativo' ? 'inativo' : 'ativo' } : u
      )
    );
  }

  removerUsuario(usuario: Usuario): void {
    this.usuarios.update((lista) => lista.filter((u) => u.id !== usuario.id));
  }

  private gerarUsuariosMock(): Usuario[] {
    return [
      {
        id: '1',
        nome: 'Lucas Almeida',
        email: 'lucas.almeida@integramove.com',
        telefone: '(12) 99123-4567',
        tipo: 'admin',
        status: 'ativo',
        dataCadastro: new Date('2025-02-10'),
      },
      {
        id: '2',
        nome: 'Rafael Duarte',
        email: 'rafael.duarte@integramove.com',
        telefone: '(12) 98877-2211',
        tipo: 'instrutor',
        status: 'ativo',
        dataCadastro: new Date('2025-05-22'),
      },
      {
        id: '3',
        nome: 'Aline Ferreira',
        email: 'aline.ferreira@integramove.com',
        telefone: '(12) 99654-3321',
        tipo: 'instrutor',
        status: 'ativo',
        dataCadastro: new Date('2025-06-14'),
      },
      {
        id: '4',
        nome: 'Camila Rocha',
        email: 'camila.rocha@integramove.com',
        telefone: '(12) 98123-9988',
        tipo: 'recepcao',
        status: 'inativo',
        dataCadastro: new Date('2024-11-30'),
      },
      {
        id: '5',
        nome: 'Carlos Eduardo Nunes',
        email: 'carlos.nunes@integramove.com',
        telefone: '(12) 99777-6655',
        tipo: 'recepcao',
        status: 'ativo',
        dataCadastro: new Date('2026-01-08'),
      },
    ];
  }
}
