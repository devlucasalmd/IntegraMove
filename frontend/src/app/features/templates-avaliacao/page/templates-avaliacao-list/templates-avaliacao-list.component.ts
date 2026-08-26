import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';

import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTooltipModule } from '@angular/material/tooltip';

import { TemplateAvaliacaoService } from '../../service/template-avaliacao.service';
import { TemplateAvaliacaoResponseDTO } from '../../model/template-avaliacao.model';
import { AtualizarTemplateAvaliacaoRequestDTO } from '../../model/template-avaliacao-request.model';
import {
  TemplateAvaliacaoFormDialogComponent,
  TemplateAvaliacaoFormDialogData
} from '../template-avaliacao-form-dialog/template-avaliacao-form-dialog.component';

/**
 * Tela de gestão de templates de avaliação física (`/administrador/avaliacao`).
 *
 * Lista todos os templates cadastrados (ativos e inativos) via
 * `GET /templates-avaliacao/todos` e permite criar (`POST /templates-avaliacao`),
 * editar e ativar/inativar (`PUT /templates-avaliacao/{id}`) templates.
 */
@Component({
  selector: 'app-templates-avaliacao-list',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    MatTooltipModule
  ],
  templateUrl: './templates-avaliacao-list.component.html',
  styleUrl: './templates-avaliacao-list.component.css'
})
export class TemplatesAvaliacaoListComponent implements OnInit {

  templates: TemplateAvaliacaoResponseDTO[] = [];

  carregando = false;
  erro: string | null = null;

  atualizandoStatusId: string | null = null;

  displayedColumns: string[] = [
    'nome',
    'descricao',
    'campos',
    'status',
    'acoes'
  ];

  constructor(
    private templateService: TemplateAvaliacaoService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.carregarTemplates();
  }

  carregarTemplates(): void {
    this.carregando = true;
    this.erro = null;

    this.templateService.listarTodos().subscribe({
      next: (templates) => {
        this.templates = templates;
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar templates de avaliação:', erro);
        this.templates = [];
        this.erro = 'Não foi possível carregar os templates de avaliação. Tente novamente em instantes.';
        this.carregando = false;
      }
    });
  }

  abrirNovoTemplate(): void {
    this.abrirFormulario(null);
  }

  editarTemplate(template: TemplateAvaliacaoResponseDTO): void {
    this.abrirFormulario(template);
  }

  private abrirFormulario(template: TemplateAvaliacaoResponseDTO | null): void {
    const dialogRef = this.dialog.open<
      TemplateAvaliacaoFormDialogComponent,
      TemplateAvaliacaoFormDialogData,
      TemplateAvaliacaoResponseDTO | undefined
    >(TemplateAvaliacaoFormDialogComponent, {
      width: '680px',
      maxWidth: '96vw',
      autoFocus: false,
      disableClose: true,
      panelClass: 'dialog-profissional',
      data: { template }
    });

    dialogRef.afterClosed().subscribe((templateSalvo) => {
      if (!templateSalvo) {
        return;
      }

      this.carregarTemplates();
      this.snackBar.open(
        template ? 'Template atualizado com sucesso.' : 'Template cadastrado com sucesso.',
        'Fechar',
        { duration: 3000 }
      );
    });
  }

  alternarStatus(template: TemplateAvaliacaoResponseDTO): void {
    if (this.atualizandoStatusId) {
      return;
    }

    const request: AtualizarTemplateAvaliacaoRequestDTO = {
      nome: template.nome,
      descricao: template.descricao,
      ativo: !template.ativo,
      campos: null
    };

    this.atualizandoStatusId = template.id;

    this.templateService.atualizar(template.id, request).subscribe({
      next: () => {
        this.atualizandoStatusId = null;
        this.carregarTemplates();
        this.snackBar.open(
          request.ativo ? 'Template ativado com sucesso.' : 'Template inativado com sucesso.',
          'Fechar',
          { duration: 3000 }
        );
      },
      error: (erro) => {
        console.error('Erro ao alternar status do template:', erro);
        this.atualizandoStatusId = null;
        this.snackBar.open('Não foi possível alterar o status do template.', 'Fechar', { duration: 4000 });
      }
    });
  }

  classeStatus(template: TemplateAvaliacaoResponseDTO): string {
    return template.ativo ? 'success' : 'neutro';
  }
}
