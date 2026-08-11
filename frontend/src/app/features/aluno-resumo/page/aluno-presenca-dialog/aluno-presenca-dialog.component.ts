import { CommonModule, DatePipe } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { PresencaResumoDTO } from '../../model/presenca-resumo.model';

export interface AlunoPresencaDialogData {
  presencas: PresencaResumoDTO[];
}

@Component({
  selector: 'app-aluno-presenca-dialog',
  standalone: true,
  imports: [
    CommonModule,
    DatePipe,
    MatDialogModule,
    MatButtonModule,
    MatIconModule,
    MatListModule
  ],
  templateUrl: './aluno-presenca-dialog.component.html',
  styleUrl: './aluno-presenca-dialog.component.css'
})
export class AlunoPresencaDialogComponent {

  presencas: PresencaResumoDTO[];

  constructor(
    private dialogRef: MatDialogRef<AlunoPresencaDialogComponent>,

    @Inject(MAT_DIALOG_DATA)
    public data: AlunoPresencaDialogData
  ) {
    this.presencas = [...data.presencas].sort((a, b) => b.data.localeCompare(a.data));
  }

  fechar(): void {
    this.dialogRef.close();
  }
}
