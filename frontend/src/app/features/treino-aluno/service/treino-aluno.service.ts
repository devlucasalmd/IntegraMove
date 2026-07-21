import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TreinoAlunoResponseDTO } from '../model/treino-aluno-response.model';
import { TreinoAlunoRequestDTO } from '../model/treino-aluno-request.model';

@Injectable({
  providedIn: 'root',
})
export class TreinoAlunoService {
  private apiUrl = 'http://localhost:8080/alunos';

  constructor(private http: HttpClient) {}

  listarPorAluno(alunoId: string): Observable<TreinoAlunoResponseDTO[]> {
    return this.http.get<TreinoAlunoResponseDTO[]>(
      `${this.apiUrl}/${alunoId}/treinos`,
    );
  }

  criarFicha(
    alunoId: string,
    request: TreinoAlunoRequestDTO,
  ): Observable<TreinoAlunoResponseDTO> {
    return this.http.post<TreinoAlunoResponseDTO>(
      `${this.apiUrl}/${alunoId}/treinos`,
      request,
    );
  }

  buscarPorId(
    alunoId: string,
    fichaId: string,
  ): Observable<TreinoAlunoResponseDTO> {
    return this.http.get<TreinoAlunoResponseDTO>(
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
