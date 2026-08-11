import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { delay } from 'rxjs/operators';
import { NoticiaDTO } from './noticia.model';

/**
 * TODO: substituir os dados mockados abaixo por uma chamada real via
 * HttpClient assim que o backend expuser o endpoint de notícias, ex:
 *   GET http://localhost:8080/noticias
 * A assinatura pública (listarNoticias) já está pronta para isso.
 */
@Injectable({
  providedIn: 'root',
})
export class NoticiasService {
  private readonly noticiasMock: NoticiaDTO[] = [
    {
      id: '1',
      titulo: 'Novo horário de funcionamento aos sábados',
      resumo: 'A partir deste mês, a academia funcionará das 7h às 14h aos sábados, sem alterações nos demais dias.',
      dataPublicacao: '2026-07-28',
      categoria: 'Aviso',
    },
    {
      id: '2',
      titulo: 'Nova sala de spinning inaugurada',
      resumo: 'Contamos agora com 12 bicicletas novas e aulas todos os dias às 6h30 e 19h.',
      dataPublicacao: '2026-07-20',
      categoria: 'Novidade',
    },
    {
      id: '3',
      titulo: 'Manutenção programada nos equipamentos de musculação',
      resumo: 'Na próxima terça-feira, das 10h às 12h, alguns equipamentos ficarão indisponíveis para manutenção preventiva.',
      dataPublicacao: '2026-07-15',
      categoria: 'Aviso',
    },
    {
      id: '4',
      titulo: 'Promoção de aniversário: traga um amigo',
      resumo: 'Durante o mês de aniversário da IntegraMove, alunos ativos podem convidar um amigo para treinar gratuitamente por uma semana.',
      dataPublicacao: '2026-07-05',
      categoria: 'Promoção',
    },
  ];

  listarNoticias(): Observable<NoticiaDTO[]> {
    return of(this.noticiasMock).pipe(delay(300));
  }
}
