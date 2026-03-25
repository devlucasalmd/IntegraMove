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
import { AlunoResponseDTO } from '../../models/aluno-response.model';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-aluno-list',
  standalone: true,
  templateUrl: './aluno-list.component.html',
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
    MatFormFieldModule
  ],
})
export class AlunoListComponent implements OnInit {
  alunos: AlunoResponseDTO[] = [];

  alunosFiltrados: AlunoResponseDTO[] = [];

  filtro: string = '';

  colunas: string[] = ['nome', 'plano', 'pagamento', 'status', 'acoes'];

  constructor(private alunoService: AlunoService) {}

  ngOnInit(): void {
    this.carregarAlunos();
  }

  carregarAlunos() {
    this.alunoService.listarAlunos().subscribe({
      next: (data) => (this.alunos = data),
      error: (err) => console.error(err),
    });
  }

    filtrarAlunos() {
    const termo = this.filtro.toLowerCase();

    this.alunosFiltrados = this.alunos.filter((aluno) =>
      aluno.nome.toLowerCase().includes(termo)
    );
  }

}
