import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ResumoAlunoResponseDTO } from '../model/resumo-aluno.model';

/**
 * Service responsável pelos dados da tela "Resumo do aluno".
 */
@Injectable({
  providedIn: 'root'
})
export class AlunoResumoService {

  private apiUrl = 'http://localhost:8080/alunos';

  constructor(private http: HttpClient) {}

  /**
   * Busca o resumo agregado do aluno (dados cadastrais, contrato ativo,
   * situação financeira e vendas recentes) em uma única chamada.
   *
   * GET /alunos/{alunoId}/resumo
   */
  buscarResumo(alunoId: string): Observable<ResumoAlunoResponseDTO> {
    return this.http.get<ResumoAlunoResponseDTO>(`${this.apiUrl}/${alunoId}/resumo`);
  }
}
