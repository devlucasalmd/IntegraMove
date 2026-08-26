import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AvaliacaoRealizadaRequestDTO } from '../models/avaliacao-request.model';
import { AvaliacaoRealizadaResponseDTO } from '../models/avaliacao-response.model';

@Injectable({
  providedIn: 'root'
})
export class AvaliacaoService {

  private apiUrl = 'http://localhost:8080/avaliacoes';

  constructor(private http: HttpClient) {}

  /**
   * GET /avaliacoes/aluno/{alunoId}
   */
  listarPorAluno(alunoId: string): Observable<AvaliacaoRealizadaResponseDTO[]> {
    return this.http.get<AvaliacaoRealizadaResponseDTO[]>(`${this.apiUrl}/aluno/${alunoId}`);
  }

  /**
   * POST /avaliacoes
   */
  criar(request: AvaliacaoRealizadaRequestDTO): Observable<AvaliacaoRealizadaResponseDTO> {
    return this.http.post<AvaliacaoRealizadaResponseDTO>(this.apiUrl, request);
  }
}
