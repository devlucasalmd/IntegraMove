import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { TemplateAvaliacaoResponseDTO } from '../model/template-avaliacao.model';
import {
  AtualizarTemplateAvaliacaoRequestDTO,
  TemplateAvaliacaoRequestDTO
} from '../model/template-avaliacao-request.model';

@Injectable({
  providedIn: 'root'
})
export class TemplateAvaliacaoService {

  private apiUrl = 'http://localhost:8080/templates-avaliacao';

  constructor(private http: HttpClient) {}

  /**
   * GET /templates-avaliacao — apenas templates ATIVOS.
   *
   * Usado no select de "Nova Avaliação" (feature `avaliacoes`), já que só
   * templates ativos podem ser aplicados a um aluno.
   */
  listarAtivos(): Observable<TemplateAvaliacaoResponseDTO[]> {
    return this.http.get<TemplateAvaliacaoResponseDTO[]>(this.apiUrl);
  }

  /**
   * GET /templates-avaliacao/todos — ativos e inativos.
   *
   * Usado na tela de gestão de templates (`/administrador/avaliacao`).
   */
  listarTodos(): Observable<TemplateAvaliacaoResponseDTO[]> {
    return this.http.get<TemplateAvaliacaoResponseDTO[]>(`${this.apiUrl}/todos`);
  }

  /**
   * POST /templates-avaliacao
   */
  criar(dto: TemplateAvaliacaoRequestDTO): Observable<TemplateAvaliacaoResponseDTO> {
    return this.http.post<TemplateAvaliacaoResponseDTO>(this.apiUrl, dto);
  }

  /**
   * PUT /templates-avaliacao/{id}
   */
  atualizar(id: string, dto: AtualizarTemplateAvaliacaoRequestDTO): Observable<TemplateAvaliacaoResponseDTO> {
    return this.http.put<TemplateAvaliacaoResponseDTO>(`${this.apiUrl}/${id}`, dto);
  }
}
