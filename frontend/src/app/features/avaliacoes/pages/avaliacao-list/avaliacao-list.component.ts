import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatExpansionModule } from '@angular/material/expansion';

import { AvaliacaoService } from '../../services/avaliacao.service';
import { AvaliacaoRealizadaResponseDTO } from '../../models/avaliacao-response.model';
import { AvaliacaoFormComponent, AvaliacaoFormDialogData } from '../avaliacao-form/avaliacao-form.component';

/**
 * Aba "Avaliações" do perfil do aluno.
 *
 * Lista o histórico de avaliações físicas aplicadas ao aluno
 * (`GET /avaliacoes/aluno/{alunoId}`), ordenadas por data decrescente
 * (ordenação feita no client, já que o backend não garante ordenação).
 * Permite registrar uma nova avaliação via dialog (`POST /avaliacoes`).
 */
@Component({
  selector: 'app-avaliacao-list',
  standalone: true,
  imports: [
    CommonModule,
    DatePipe,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    MatExpansionModule
  ],
  templateUrl: './avaliacao-list.component.html',
  styleUrl: './avaliacao-list.component.css'
})
export class AvaliacaoListComponent implements OnInit {

  alunoId!: string;

  avaliacoes: AvaliacaoRealizadaResponseDTO[] = [];

  carregando = false;
  erro: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private avaliacaoService: AvaliacaoService
  ) {}

  ngOnInit(): void {
    this.alunoId = this.route.parent?.snapshot.paramMap.get('alunoId') ?? '';

    if (!this.alunoId) {
      this.alunoId = this.route.snapshot.paramMap.get('alunoId') ?? '';
    }

    this.carregarAvaliacoes();
  }

  carregarAvaliacoes(): void {
    this.carregando = true;
    this.erro = null;

    this.avaliacaoService.listarPorAluno(this.alunoId).subscribe({
      next: (avaliacoes) => {
        this.avaliacoes = [...avaliacoes].sort((a, b) => b.dataAvaliacao.localeCompare(a.dataAvaliacao));
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar avaliações do aluno:', erro);
        this.avaliacoes = [];
        this.erro = 'Não foi possível carregar as avaliações deste aluno. Tente novamente em instantes.';
        this.carregando = false;
      }
    });
  }

  abrirNovaAvaliacao(): void {
    const dialogRef = this.dialog.open<AvaliacaoFormComponent, AvaliacaoFormDialogData, AvaliacaoRealizadaResponseDTO | undefined>(
      AvaliacaoFormComponent,
      {
        width: '640px',
        maxWidth: '96vw',
        autoFocus: false,
        disableClose: true,
        panelClass: 'dialog-profissional',
        data: { alunoId: this.alunoId }
      }
    );

    dialogRef.afterClosed().subscribe((avaliacao) => {
      if (!avaliacao) {
        return;
      }

      this.carregarAvaliacoes();
      this.snackBar.open('Avaliação registrada com sucesso.', 'Fechar', { duration: 3000 });
    });
  }
}
