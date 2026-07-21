import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import {  } from '../../../alunos/models/aluno-detalhe-response.model';
import { AvaliacaoService } from '../../services/avaliacao.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { AvaliacaoFormComponent } from '../avaliacao-form/avaliacao-form.component';
import { AvaliacaoResponseDTO } from '../../models/avaliacao-response.model';
import { AlunoResumoResponseDTO } from '../../../alunos/models/aluno-resumo-response.model';

@Component({
  selector: 'app-avaliacao-list',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    RouterModule,
    MatIconModule,
    MatDialogModule,
  ],
  templateUrl: './avaliacao-list.component.html',
  styleUrls: ['./avaliacao-list.component.css'],
})
export class AvaliacaoListComponent implements OnInit {
  alunoId!: string;
  aluno?: AlunoResumoResponseDTO;

  avaliacoes: AvaliacaoResponseDTO[] = [];
  carregando = false;

  constructor(
    private route: ActivatedRoute,
    private dialog: MatDialog,
    private avaliacaoService: AvaliacaoService,
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.alunoId = this.getAlunoId();
      console.log('✅ alunoId:', this.alunoId);

      this.carregarAvaliacoes();
    });
  }

  getAlunoId(): string {
    let route = this.route;

    while (route) {
      const id = route.snapshot.paramMap.get('alunoId');
      if (id) return id;

      route = route.parent!;
    }

    throw new Error('ID do aluno não encontrado');
  }

  carregarAvaliacoes() {

    this.carregando = true;

    this.avaliacaoService.listarAvaliacoes(this.alunoId).subscribe({
      next: (data) => {
        console.log('Dados recebidos:', data);
        this.avaliacoes = data;
        this.carregando = false;
      },
      error: (err) => {
        console.error('Erro ao carregar avaliações', err);
        this.carregando = false;

      },
    });
  }

  abrirNovaAvaliacao(): void {
    const dialogRef = this.dialog.open(AvaliacaoFormComponent, {
      width: '980px',
      maxWidth: '96vw',
      maxHeight: '90vh',
      disableClose: true,
      autoFocus: false,
      panelClass: 'dialog-profissional',
      data: {
        alunoId: this.alunoId,
        modoVisualizacao: false
      }
    });

    dialogRef.afterClosed().subscribe((atualizou: boolean) => {
      if (atualizou) {
        this.carregarAvaliacoes();
      }
    });
  }

  abrirVisualizacao(avaliacao: AvaliacaoResponseDTO): void {
    this.dialog.open(AvaliacaoFormComponent, {
      width: '980px',
      maxWidth: '96vw',
      maxHeight: '90vh',
      autoFocus: false,
      panelClass: 'dialog-profissional',
      data: {
        alunoId: this.alunoId,
        avaliacaoId: avaliacao.id,
        modoVisualizacao: true
      }
    });
  }

}
