import { Component, OnInit } from '@angular/core';
import { AlunoService } from '../../services/aluno.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-aluno-list',
  templateUrl: './aluno-list.component.html',
  imports: [
    CommonModule,
    RouterModule,
    MatButtonModule
  ]
})
export class AlunoListComponent implements OnInit {

  alunos: any[] = [];

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
