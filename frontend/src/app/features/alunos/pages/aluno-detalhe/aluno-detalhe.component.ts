import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { AlunoService } from '../../services/aluno.service';
import { AlunoDetalheResponseDTO } from '../../models/aluno-detalhe-response.model';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { AlunoCadastroDialogComponent } from '../aluno-cadastro-dialog/aluno-cadastro-dialog.component';

@Component({
  selector: 'app-aluno-detalhe',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
  ],
  templateUrl: './aluno-detalhe.component.html',
  styleUrls: ['./aluno-detalhe.component.css'],
})
export class AlunoDetalheComponent implements OnInit {
  aluno?: AlunoDetalheResponseDTO;
  carregando = false;

  constructor(
    private route: ActivatedRoute,
    private alunoService: AlunoService,
    private dialog: MatDialog,
  ) {}

  ngOnInit(): void {
    this.carregarAluno();
  }

  carregarAluno(): void {
    const id = this.route.snapshot.paramMap.get('alunoId');

    if (!id) {
      console.error('alunoId não encontrado na rota');
      return;
    }

    this.carregando = true;

    this.alunoService.buscarAlunoPorId(id).subscribe({
      next: (aluno) => {
        this.aluno = aluno;
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao buscar aluno:', erro);
        this.carregando = false;
      },
    });
  }

  abrirCadastro(): void {
    if (!this.aluno?.id) {
      return;
    }

    const dialogRef = this.dialog.open(AlunoCadastroDialogComponent, {
      width: '950px',
      maxWidth: '96vw',
      maxHeight: '90vh',
      autoFocus: false,
      disableClose: true,
      panelClass: 'dialog-profissional',
      data: {
        alunoId: this.aluno.id,
      },
    });

    dialogRef.afterClosed().subscribe((atualizou: boolean) => {
      if (atualizou) {
        this.carregarAluno();
      }
    });
  }

  alunoAtivo(): boolean {
    return this.aluno?.status === 'ATIVO';
  }

  alunoPossuiPlano(): boolean {
    return !!this.aluno?.planoId || !!this.aluno?.nomePlano;
  }

  obterNomePlano(): string {
    if (!this.aluno) {
      return 'Sem plano';
    }

    if (this.aluno.nomePlano && this.aluno.nomePlano.trim() !== '') {
      return this.aluno.nomePlano;
    }

    return 'Sem plano';
  }
}
