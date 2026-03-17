import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { AlunoService } from '../../services/aluno.service';
import { AlunoResponseDTO } from '../../models/aluno-response.model';

@Component({
  selector: 'app-aluno-detalhe',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatCardModule,
    MatButtonModule
  ],
  templateUrl: './aluno-detalhe.component.html',
  styleUrls: ['./aluno-detalhe.component.css']
})
export class AlunoDetalheComponent implements OnInit {

  aluno?: AlunoResponseDTO;

  constructor(
    private route: ActivatedRoute,
    private alunoService: AlunoService
  ) {}

  ngOnInit(): void {

    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.alunoService.buscarAlunoPorId(id)
        .subscribe(aluno => {
          this.aluno = aluno;
        });
    }

  }

}
