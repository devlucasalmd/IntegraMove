import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AlunoTreinoResponseDTO } from '../model/aluno-treino-response.model';
import { AlunoTreinoRequestDTO } from '../model/aluno-treino-request.model';

@Injectable({
  providedIn: 'root',
})
export class AlunoTreinoService {
  private apiUrl = 'http://localhost:8080/alunos';

  constructor(private http: HttpClient) {}

  listarPorAluno(alunoId: string): Observable<AlunoTreinoResponseDTO[]> {
    return this.http.get<AlunoTreinoResponseDTO[]>(
      `${this.apiUrl}/${alunoId}/treinos`,
    );
  }

  criarFicha(
    alunoId: string,
    request: AlunoTreinoRequestDTO,
  ): Observable<AlunoTreinoResponseDTO> {
    return this.http.post<AlunoTreinoResponseDTO>(
      `${this.apiUrl}/${alunoId}/treinos`,
      request,
    );
  }

  buscarPorId(
    alunoId: string,
    fichaId: string,
  ): Observable<AlunoTreinoResponseDTO> {
    return this.http.get<AlunoTreinoResponseDTO>(
      `${this.apiUrl}/${alunoId}/treinos/${fichaId}`,
    );
  }

  inativar(alunoId: string, fichaId: string): Observable<void> {
    return this.http.patch<void>(
      `${this.apiUrl}/${alunoId}/treinos/${fichaId}/inativar`,
      {},
    );
  }
}
