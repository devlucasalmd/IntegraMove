import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { AlunoResponseDTO } from '../../../alunos/models/aluno-response.model';

@Component({
  selector: 'app-avaliacao-list',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    RouterModule
  ],
  templateUrl: './avaliacao-list.component.html',
  styleUrls: ['./avaliacao-list.component.css']
})
export class AvaliacaoListComponent implements OnInit {

  alunoId!: string;
  aluno?: AlunoResponseDTO;


  avaliacoes: any[] = [];

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.alunoId = this.route.snapshot.paramMap.get('id')!;
  }

}
