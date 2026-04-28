import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatColumnDef, MatHeaderCell, MatHeaderCellDef, MatCell, MatCellDef, MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef, MatTableModule } from "@angular/material/table";
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatDividerModule } from '@angular/material/divider';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { TreinoResponseDTO } from '../../../models/treino-response.model';
import { TreinoService } from '../../../services/treino.service';


@Component({
  selector: 'app-treino-list',
  standalone: true,
  templateUrl: './treino-list.component.html',
  styleUrls: ['./treino-list.component.css'],
  imports: [
    CommonModule,
    RouterModule,
    MatButtonModule,
    MatTableModule,
    MatCardModule,
    MatIconModule,
    MatMenuModule,
    MatDividerModule,
    FormsModule,
    MatFormFieldModule
  ],
})
export class TreinoListComponent implements OnInit {

  treinos: TreinoResponseDTO[] = [];

  colunas: string[] = ['nome', 'responsavel', 'funcionalidade', 'nivel', 'repeticoes'];

  constructor(
    private treinoService: TreinoService
  ) {}

  ngOnInit(): void {
    this.carregarTreinos();
  }

  carregarTreinos() {
    this.treinoService.listarTreinos().subscribe({
        next: (data) => {
          this.treinos = data;
        },
        error: (err) => {
          console.error('Erro ao carregar treinos', err);
        }
      });
  }

}
