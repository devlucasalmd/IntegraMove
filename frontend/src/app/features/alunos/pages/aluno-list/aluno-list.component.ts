import { Component, OnInit } from '@angular/core';
import { AlunoService } from '../../services/aluno.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import {MatTableModule} from '@angular/material/table';
@Component({
  selector: 'app-aluno-list',
  templateUrl: './aluno-list.component.html',
  imports: [
    CommonModule,
    RouterModule,
    MatButtonModule,
    MatTableModule,
    MatCardModule,
    MatIconModule
  ]
})
export class AlunoListComponent implements OnInit {

  alunos: any[] = [];

  colunas: string[] = ['nome', 'cpf', 'ativo', 'acoes'];

  constructor(private alunoService: AlunoService) {}

  ngOnInit(): void {
    this.carregarAlunos();
  }

  carregarAlunos() {
    this.alunoService.listarAlunos()
      .subscribe({
        next: (data) => this.alunos = data,
        error: (err) => console.error(err)
      });
  }

}
