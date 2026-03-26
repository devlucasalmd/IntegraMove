import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { AlunoResponseDTO } from '../../../alunos/models/aluno-response.model';
import { AvaliacaoService } from '../../services/avaliacao.service';

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

  constructor(private route: ActivatedRoute, private avaliacaoService: AvaliacaoService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.alunoId = params.get('id')!;
      this.carregarAvaliacoes();
    });
  }

  carregarAvaliacoes() {
    this.avaliacaoService.listarAvaliacoes(this.alunoId)
      .subscribe({
        next: (data) => {
          this.avaliacoes = data;
        },
        error: (err) => {
          console.error('Erro ao carregar avaliações', err);
        }
      });
  }
}
