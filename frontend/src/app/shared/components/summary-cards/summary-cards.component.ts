import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

export type SummaryCardVariant = 'total' | 'success' | 'warning' | 'danger';

export interface SummaryCardData {
  icon: string;
  label: string;
  /** Já formatado (moeda, número, texto) — o componente não formata nada. */
  value: string;
  variant: SummaryCardVariant;
}

@Component({
  selector: 'app-summary-cards',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatIconModule],
  templateUrl: './summary-cards.component.html',
  styleUrl: './summary-cards.component.css',
})
export class SummaryCardsComponent {
  @Input() cards: SummaryCardData[] = [];
}
