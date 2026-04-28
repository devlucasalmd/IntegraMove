import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { TreinoRequestDTO } from "../models/treinos-request.model";
import { TreinoResponseDTO } from '../models/treino-response.model';


@Injectable({
  providedIn: 'root'
})
export class TreinoAlunoService {

  private apiUrl = 'http://localhost:8080/alunos';

  constructor(private http: HttpClient) {}


  // ✅ 🔥 MAIS IMPORTANTE (seu caso)
  listarTreinosPorAluno(alunoId: string): Observable<TreinoResponseDTO[]> {
    return this.http.get<TreinoResponseDTO[]>(
      `${this.apiUrl}/aluno/${alunoId}`
    );
  }

  // ✅ Buscar por ID
  buscarTreinoPorId(id: string): Observable<TreinoResponseDTO> {
    return this.http.get<TreinoResponseDTO>(`${this.apiUrl}/${id}`);
  }

  // (Opcional futuro)
  atualizarTreino(id: string, treino: TreinoRequestDTO) {
    return this.http.put(`${this.apiUrl}/${id}`, treino);
  }

  // (Opcional futuro)
  deletarTreino(id: string) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
