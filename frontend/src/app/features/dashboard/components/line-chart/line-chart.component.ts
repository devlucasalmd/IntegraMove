import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PeriodoDashboard, PontoGrafico } from '../../models/dashboard.model';

interface PontoSvg extends PontoGrafico {
  x: number;
  y: number;
}

interface TickEixoY {
  valor: number;
  y: number;
}

/**
 * Gráfico de linha simples, implementação própria em SVG (sem lib de
 * gráfico) — recebe uma lista de pontos `{ label, value }` e desenha uma
 * linha conectando os pontos, com eixo Y de escala automática (min/max) e
 * eixo X com os labels recebidos. Responsivo via `viewBox` + `width: 100%`.
 */
@Component({
  selector: 'app-line-chart',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './line-chart.component.html',
  styleUrl: './line-chart.component.css',
})
export class LineChartComponent implements OnChanges {
  @Input() pontos: PontoGrafico[] = [];
  /** Cor da linha e dos pontos — por padrão, o navy do IntegraMove. */
  @Input() corLinha = '#142e67';
  @Input() alturaViewBox = 260;
  @Input() larguraViewBox = 640;
  /**
   * Granularidade dos pontos recebidos (ex: 'hoje' | 'semana' | 'mes' | 'ano').
   * Puramente informativo — usado apenas no `aria-label` do SVG, não altera
   * a lógica de desenho do gráfico.
   */
  @Input() granularidade: PeriodoDashboard | string = '';

  private readonly paddingEsquerda = 46;
  private readonly paddingDireita = 16;
  private readonly paddingTopo = 16;
  private readonly paddingBase = 32;

  pontosSvg: PontoSvg[] = [];
  linhaPath = '';
  areaPath = '';
  ticksY: TickEixoY[] = [];
  private passoLabelX = 1;

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['pontos'] || changes['larguraViewBox'] || changes['alturaViewBox']) {
      this.recalcular();
    }
  }

  private recalcular(): void {
    const pontos = this.pontos ?? [];

    if (pontos.length === 0) {
      this.pontosSvg = [];
      this.linhaPath = '';
      this.areaPath = '';
      this.ticksY = [];
      return;
    }

    const valores = pontos.map((ponto) => ponto.value);
    let valorMinimo = Math.min(...valores);
    let valorMaximo = Math.max(...valores);

    if (valorMinimo === valorMaximo) {
      valorMinimo -= 1;
      valorMaximo += 1;
    }

    // Folga de 10% no topo/base da escala, pra linha não colar nas bordas.
    const folga = (valorMaximo - valorMinimo) * 0.1;
    valorMinimo -= folga;
    valorMaximo += folga;

    const larguraUtil = this.larguraViewBox - this.paddingEsquerda - this.paddingDireita;
    const alturaUtil = this.alturaViewBox - this.paddingTopo - this.paddingBase;
    const passoX = pontos.length > 1 ? larguraUtil / (pontos.length - 1) : 0;

    const escalaY = (valor: number): number => {
      const proporcao = (valor - valorMinimo) / (valorMaximo - valorMinimo);
      return this.paddingTopo + (1 - proporcao) * alturaUtil;
    };

    this.pontosSvg = pontos.map((ponto, indice) => ({
      ...ponto,
      x: this.paddingEsquerda + indice * passoX,
      y: escalaY(ponto.value),
    }));

    this.linhaPath = this.pontosSvg
      .map((ponto, indice) => `${indice === 0 ? 'M' : 'L'} ${ponto.x.toFixed(2)} ${ponto.y.toFixed(2)}`)
      .join(' ');

    const baseY = this.paddingTopo + alturaUtil;
    const primeiro = this.pontosSvg[0];
    const ultimo = this.pontosSvg[this.pontosSvg.length - 1];
    this.areaPath = `${this.linhaPath} L ${ultimo.x.toFixed(2)} ${baseY.toFixed(2)} L ${primeiro.x.toFixed(2)} ${baseY.toFixed(2)} Z`;

    const totalTicks = 4;
    this.ticksY = Array.from({ length: totalTicks + 1 }, (_, indice) => {
      const valor = valorMinimo + ((valorMaximo - valorMinimo) * indice) / totalTicks;
      return { valor, y: escalaY(valor) };
    }).reverse();

    this.passoLabelX = pontos.length > 8 ? Math.ceil(pontos.length / 8) : 1;
  }

  mostrarLabel(indice: number): boolean {
    return indice % this.passoLabelX === 0 || indice === this.pontos.length - 1;
  }

  formatarValorEixo(valor: number): string {
    if (Math.abs(valor) >= 1000) {
      return `${(valor / 1000).toFixed(1)}k`;
    }

    return Math.round(valor).toString();
  }

  /** Rótulo de acessibilidade do SVG, incluindo a granularidade quando informada. */
  descricaoAriaLabel(): string {
    return this.granularidade ? `Gráfico de evolução (${this.granularidade})` : 'Gráfico de evolução';
  }
}
