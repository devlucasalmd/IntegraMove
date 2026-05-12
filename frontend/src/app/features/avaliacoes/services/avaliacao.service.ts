import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AvaliacaoRequestDTO } from '../models/avaliacao.model';

@Injectable({
  providedIn: 'root'
})
export class AvaliacaoService {

  private apiUrl = 'http://localhost:8080/alunos';

  constructor(private http: HttpClient) { }

  cadastrarAvaliacao(alunoId: string, avaliacao: AvaliacaoRequestDTO): Observable<any> {
    return this.http.post(`${this.apiUrl}/${alunoId}/avaliacoes`, avaliacao);
  }

  listarAvaliacoes(alunoId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${alunoId}/avaliacoes`);
  }

  buscarPorId(
    alunoId: string,
    avaliacaoId: string
  ): Observable<any> {

    return this.http.get<any>(
      `${this.apiUrl}/${alunoId}/avaliacoes/${avaliacaoId}`
    );
  }
}
