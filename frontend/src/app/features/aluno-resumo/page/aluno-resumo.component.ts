import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTooltipModule } from '@angular/material/tooltip';

import { AlunoService } from '../../alunos/services/aluno.service';
import { AlunoResumoService } from '../service/aluno-resumo.service';
import { PendenciaResumoDTO } from '../model/pendencia-resumo.model';
import { PresencaResumoDTO } from '../model/presenca-resumo.model';
import { DocumentoResumoDTO } from '../model/documento-resumo.model';
import { AlunoPresencaDialogComponent } from './aluno-presenca-dialog/aluno-presenca-dialog.component';

@Component({
  selector: 'app-aluno-resumo',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    DatePipe,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatInputModule,
    MatTooltipModule
  ],
  templateUrl: './aluno-resumo.component.html',
  styleUrl: './aluno-resumo.component.css'
})
export class AlunoResumoComponent implements OnInit {

  alunoId!: string;

  // Informações financeiras — TODO: mock (ver AlunoResumoService.buscarDiaPagamento)
  diaPagamento: number | null = null;
  carregandoDiaPagamento = false;

  // Pendências — TODO: mock (ver AlunoResumoService.listarPendencias)
  pendencias: PendenciaResumoDTO[] = [];
  carregandoPendencias = false;

  // Presenças — TODO: mock (ver AlunoResumoService.listarPresencasDoMes)
  presencas: PresencaResumoDTO[] = [];
  carregandoPresencas = false;

  // Observação do aluno
  observacao = '';
  editandoObservacao = false;
  salvandoObservacao = false;

  // Documentos — TODO: mock (ver AlunoResumoService.listarDocumentos/enviarDocumento)
  documentos: DocumentoResumoDTO[] = [];
  carregandoDocumentos = false;
  enviandoDocumento = false;

  constructor(
    private route: ActivatedRoute,
    private alunoService: AlunoService,
    private alunoResumoService: AlunoResumoService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.alunoId = this.route.parent?.snapshot.paramMap.get('alunoId') ?? '';

    if (!this.alunoId) {
      this.alunoId = this.route.snapshot.paramMap.get('alunoId') ?? '';
    }

    this.carregarResumo();
  }

  carregarResumo(): void {
    this.carregarObservacao();
    this.carregarDiaPagamento();
    this.carregarPendencias();
    this.carregarPresencas();
    this.carregarDocumentos();
  }

  carregarObservacao(): void {
    this.alunoService.buscarAlunoPorId(this.alunoId).subscribe({
      next: (aluno) => {
        this.observacao = aluno.observacao ?? '';
      },
      error: (erro) => {
        console.error('Erro ao carregar observação do aluno:', erro);
      }
    });
  }

  carregarDiaPagamento(): void {
    this.carregandoDiaPagamento = true;

    this.alunoResumoService.buscarDiaPagamento(this.alunoId).subscribe({
      next: (dia) => {
        this.diaPagamento = dia;
        this.carregandoDiaPagamento = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar dia de pagamento:', erro);
        this.carregandoDiaPagamento = false;
      }
    });
  }

  carregarPendencias(): void {
    this.carregandoPendencias = true;

    this.alunoResumoService.listarPendencias(this.alunoId).subscribe({
      next: (pendencias) => {
        this.pendencias = pendencias;
        this.carregandoPendencias = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar pendências do aluno:', erro);
        this.carregandoPendencias = false;
      }
    });
  }

  carregarPresencas(): void {
    this.carregandoPresencas = true;

    this.alunoResumoService.listarPresencasDoMes(this.alunoId).subscribe({
      next: (presencas) => {
        this.presencas = presencas;
        this.carregandoPresencas = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar presenças do aluno:', erro);
        this.carregandoPresencas = false;
      }
    });
  }

  carregarDocumentos(): void {
    this.carregandoDocumentos = true;

    this.alunoResumoService.listarDocumentos(this.alunoId).subscribe({
      next: (documentos) => {
        this.documentos = documentos;
        this.carregandoDocumentos = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar documentos do aluno:', erro);
        this.carregandoDocumentos = false;
      }
    });
  }

  get totalPendenciasAbertas(): number {
    return this.pendencias.filter(pendencia => pendencia.status === 'PENDENTE').length;
  }

  get totalPresencasMes(): number {
    return this.presencas.length;
  }

  abrirHistoricoPresencas(): void {
    this.dialog.open(AlunoPresencaDialogComponent, {
      width: '480px',
      maxWidth: '96vw',
      autoFocus: false,
      data: {
        presencas: this.presencas
      }
    });
  }

  habilitarEdicaoObservacao(): void {
    this.editandoObservacao = true;
  }

  cancelarEdicaoObservacao(): void {
    this.editandoObservacao = false;
    this.carregarObservacao();
  }

  salvarObservacao(): void {
    this.salvandoObservacao = true;

    this.alunoService.atualizarObservacao(this.alunoId, this.observacao).subscribe({
      next: () => {
        this.salvandoObservacao = false;
        this.editandoObservacao = false;
        this.snackBar.open('Observação salva com sucesso.', 'Fechar', { duration: 3000 });
      },
      error: (erro) => {
        // O endpoint PATCH /alunos/{id}/observacao ainda não existe no
        // backend, então em desenvolvimento essa chamada falha (404).
        // Mantemos a alteração aplicada localmente para não travar o uso
        // da tela enquanto o endpoint não é implementado.
        console.warn('Endpoint de observação ainda não implementado no backend:', erro);
        this.salvandoObservacao = false;
        this.editandoObservacao = false;
        this.snackBar.open(
          'Observação atualizada localmente (endpoint do backend ainda não existe).',
          'Fechar',
          { duration: 4000 }
        );
      }
    });
  }

  onArquivoSelecionado(event: Event): void {
    const input = event.target as HTMLInputElement;

    if (!input.files || input.files.length === 0) {
      return;
    }

    const arquivo = input.files[0];
    this.enviandoDocumento = true;

    this.alunoResumoService.enviarDocumento(this.alunoId, arquivo).subscribe({
      next: (documento) => {
        this.documentos = [documento, ...this.documentos];
        this.enviandoDocumento = false;
        this.snackBar.open('Documento anexado com sucesso.', 'Fechar', { duration: 3000 });
      },
      error: (erro) => {
        console.error('Erro ao enviar documento:', erro);
        this.enviandoDocumento = false;
        this.snackBar.open('Erro ao anexar documento.', 'Fechar', { duration: 3000 });
      }
    });

    input.value = '';
  }

  baixarDocumento(documento: DocumentoResumoDTO): void {
    window.open(documento.url, '_blank');
  }

  getPendenciaIcon(pendencia: PendenciaResumoDTO): string {
    return pendencia.status === 'PENDENTE' ? 'error_outline' : 'check_circle';
  }

  getPendenciaClass(pendencia: PendenciaResumoDTO): string {
    return pendencia.status === 'PENDENTE' ? 'pendente' : 'resolvida';
  }
}
