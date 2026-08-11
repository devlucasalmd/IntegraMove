import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AlunoRequestDTO } from '../models/aluno-request.model';
import { AlunoDetalheResponseDTO } from '../models/aluno-detalhe-response.model';
import { AlunoResumoResponseDTO } from '../models/aluno-resumo-response.model';

@Injectable({
  providedIn: 'root'
})
export class AlunoService {

  private apiUrl = 'http://localhost:8080/alunos';

  constructor(private http: HttpClient) {}

  cadastrarAluno(aluno: AlunoRequestDTO): Observable<AlunoDetalheResponseDTO> {
    return this.http.post<AlunoDetalheResponseDTO>(this.apiUrl, aluno);
  }

  listarAlunos(): Observable<AlunoResumoResponseDTO[]> {
    return this.http.get<AlunoResumoResponseDTO[]>(this.apiUrl);
  }

  buscarAlunoPorId(id: string): Observable<AlunoDetalheResponseDTO>{
    return this.http.get<AlunoDetalheResponseDTO>(`${this.apiUrl}/${id}`);
  }

  atualizarAluno(id: string, aluno: AlunoRequestDTO): Observable<AlunoDetalheResponseDTO> {
    return this.http.put<AlunoDetalheResponseDTO>( `${this.apiUrl}/${id}`, aluno);
  }

  /**
   * TODO: mock — endpoint ainda não existe no backend.
   * Sugestão: PATCH /alunos/{id}/observacao com body { "observacao": string }
   * retornando o próprio recurso atualizado (ou apenas 204 No Content).
   * A chamada é mantida no formato HTTP real para já ficar pronta quando
   * o endpoint for implementado.
   */
  atualizarObservacao(id: string, observacao: string): Observable<{ observacao: string }> {
    return this.http.patch<{ observacao: string }>(`${this.apiUrl}/${id}/observacao`, { observacao });
  }
}
