import { Component, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import {
  KpiCard,
  TransacaoFinanceira,
  FrequenciaAluno,
  AvaliacaoFisica,
  PeriodoFiltro,
} from '../../models/relatorio.model';

@Component({
  selector: 'app-relatorios',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatTabsModule,
    MatTableModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    MatIconModule,
    MatProgressBarModule,
  ],
  templateUrl: './relatorios-list.component.html',
  styleUrl: './relatorios-list.component.css',
})
export class RelatoriosListComponent implements OnInit {
  carregando = signal(true);
  periodoSelecionado = signal<PeriodoFiltro>('30d');

  kpis = signal<KpiCard[]>([]);
  transacoes = signal<TransacaoFinanceira[]>([]);
  frequencias = signal<FrequenciaAluno[]>([]);
  avaliacoes = signal<AvaliacaoFisica[]>([]);

  colunasFinanceiro = ['data', 'aluno', 'descricao', 'valor', 'status'];
  colunasFrequencia = ['aluno', 'plano', 'checkinsMes', 'ultimoCheckin', 'situacao'];
  colunasAvaliacoes = ['aluno', 'data', 'peso', 'percentualGordura', 'responsavel'];

  totalRecebido = computed(() =>
    this.transacoes()
      .filter((t) => t.status === 'pago')
      .reduce((soma, t) => soma + t.valor, 0)
  );

  totalPendente = computed(() =>
    this.transacoes()
      .filter((t) => t.status !== 'pago')
      .reduce((soma, t) => soma + t.valor, 0)
  );

  ngOnInit(): void {
    this.carregarDados();
  }

  onPeriodoChange(periodo: PeriodoFiltro): void {
    this.periodoSelecionado.set(periodo);
    this.carregarDados();
  }

  exportarRelatorio(formato: 'pdf' | 'csv'): void {
    console.log(`Exportando relatório em ${formato}`);
  }

  private carregarDados(): void {
    this.carregando.set(true);

    setTimeout(() => {
      this.kpis.set(this.gerarKpisMock());
      this.transacoes.set(this.gerarTransacoesMock());
      this.frequencias.set(this.gerarFrequenciasMock());
      this.avaliacoes.set(this.gerarAvaliacoesMock());
      this.carregando.set(false);
    }, 400);
  }

  private gerarKpisMock(): KpiCard[] {
    return [
      { label: 'Receita no período', value: 'R$ 18.420,00', icon: 'payments', trend: 8.4, trendLabel: 'vs. período anterior' },
      { label: 'Alunos ativos', value: '312', icon: 'group', trend: 3.1, trendLabel: 'vs. período anterior' },
      { label: 'Inadimplência', value: 'R$ 1.860,00', icon: 'warning', trend: -12.5, trendLabel: 'vs. período anterior' },
      { label: 'Frequência média', value: '68%', icon: 'trending_up', trend: 5.2, trendLabel: 'vs. período anterior' },
    ];
  }

  private gerarTransacoesMock(): TransacaoFinanceira[] {
    return [
      { data: new Date('2026-07-28'), aluno: 'Marina Souza', descricao: 'Plano Mensal', valor: 129.9, status: 'pago' },
      { data: new Date('2026-07-27'), aluno: 'Carlos Eduardo', descricao: 'Plano Trimestral', valor: 349.9, status: 'pago' },
      { data: new Date('2026-07-25'), aluno: 'Beatriz Lima', descricao: 'Plano Mensal', valor: 129.9, status: 'atrasado' },
      { data: new Date('2026-07-22'), aluno: 'João Pedro', descricao: 'Avaliação Física', valor: 60.0, status: 'pendente' },
      { data: new Date('2026-07-20'), aluno: 'Fernanda Costa', descricao: 'Plano Anual', valor: 1199.0, status: 'pago' },
    ];
  }

  private gerarFrequenciasMock(): FrequenciaAluno[] {
    return [
      { aluno: 'Marina Souza', plano: 'Mensal', checkinsMes: 22, ultimoCheckin: new Date('2026-07-31'), situacao: 'ativo' },
      { aluno: 'Carlos Eduardo', plano: 'Trimestral', checkinsMes: 14, ultimoCheckin: new Date('2026-07-29'), situacao: 'ativo' },
      { aluno: 'Beatriz Lima', plano: 'Mensal', checkinsMes: 3, ultimoCheckin: new Date('2026-07-10'), situacao: 'risco' },
      { aluno: 'João Pedro', plano: 'Mensal', checkinsMes: 0, ultimoCheckin: new Date('2026-06-15'), situacao: 'inativo' },
    ];
  }

  private gerarAvaliacoesMock(): AvaliacaoFisica[] {
    return [
      { aluno: 'Marina Souza', data: new Date('2026-07-15'), peso: 62.4, percentualGordura: 21.2, responsavel: 'Prof. Rafael' },
      { aluno: 'Carlos Eduardo', data: new Date('2026-07-10'), peso: 81.1, percentualGordura: 17.8, responsavel: 'Prof. Rafael' },
      { aluno: 'Fernanda Costa', data: new Date('2026-07-05'), peso: 58.9, percentualGordura: 24.6, responsavel: 'Prof. Aline' },
    ];
  }
}
