import { Component, OnInit } from '@angular/core';
import { AlunoService } from '../../services/aluno.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatMenuModule } from '@angular/material/menu';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDividerModule } from '@angular/material/divider';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { AlunoResumoResponseDTO } from '../../models/aluno-resumo-response.model';
@Component({
  selector: 'app-aluno-list',
  standalone: true,
  templateUrl: './aluno-list.component.html',
  styleUrls: ['./aluno-list.component.css'],
  imports: [
    CommonModule,
    RouterModule,
    MatButtonModule,
    MatTableModule,
    MatCardModule,
    MatIconModule,
    MatMenuModule,
    MatDividerModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule
  ],
})
export class AlunoListComponent implements OnInit {
  alunos: AlunoResumoResponseDTO[] = [];
  alunosFiltrados: AlunoResumoResponseDTO[] = [];

  filtro: string = '';
  carregando = false;

  colunas: string[] = ['aluno', 'plano', 'pagamento', 'status', 'acoes'];

  constructor(private alunoService: AlunoService) {}

  ngOnInit(): void {
    this.carregarAlunos();
  }

  carregarAlunos(): void {
    this.carregando = true;

    this.alunoService.listarAlunos().subscribe({
      next: (response) => {
        console.log('Alunos recebidos:', response);

        this.alunos = response;
        this.alunosFiltrados = response;

        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao listar alunos:', erro);
        this.carregando = false;
      }
    });
  }

  filtrarAlunos(): void {
    const termo = this.filtro.trim().toLowerCase();

    if (!termo) {
      this.alunosFiltrados = this.alunos;
      return;
    }

    this.alunosFiltrados = this.alunos.filter(aluno =>
      aluno.nome.toLowerCase().includes(termo) ||
      aluno.nomePlano?.toLowerCase().includes(termo) ||
      aluno.pagamento?.toLowerCase().includes(termo) ||
      aluno.status?.toLowerCase().includes(termo)
    );
  }

  limparFiltro(): void {
    this.filtro = '';
    this.alunosFiltrados = this.alunos;
  }


  formatarPagamento(pagamento: string): string {
    const labels: Record<string, string> = {
      FEITO: 'Feito',
      EM_ABERTO: 'Em aberto',
      A_VENCER: 'A vencer',
      VENCIDO: 'Vencido'
    };

    return labels[pagamento] || pagamento;
  }

  formatarStatus(status: string): string {
    const labels: Record<string, string> = {
      ATIVO: 'Ativo',
      INATIVO: 'Inativo'
    };

    return labels[status] || status;
  }

  inicialNome(nome: string): string {
    return nome?.charAt(0).toUpperCase() || '?';
  }

  totalAtivos(): number {
    return this.alunos.filter(aluno => aluno.status === 'ATIVO').length;
  }

  totalInativos(): number {
    return this.alunos.filter(aluno => aluno.status === 'INATIVO').length;
  }

}
