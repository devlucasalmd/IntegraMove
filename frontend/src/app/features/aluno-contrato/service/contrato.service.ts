import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ContratoResponseDTO } from '../model/contrato.model';

/**
 * Service responsável pelos contratos de um aluno (aba "Contratos" do perfil).
 */
@Injectable({
  providedIn: 'root'
})
export class ContratoService {

  private apiUrl = 'http://localhost:8080/contratos';

  constructor(private http: HttpClient) {}

  /**
   * GET /contratos/aluno/{alunoId}
   */
  listarPorAluno(alunoId: string): Observable<ContratoResponseDTO[]> {
    return this.http.get<ContratoResponseDTO[]>(`${this.apiUrl}/aluno/${alunoId}`);
  }
}
