import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ExercicioRequestDTO } from '../models/exercicio-request.model';
import { ExercicioResponseDTO } from '../models/exercicio-response';

@Injectable({
  providedIn: 'root'
})
export class ExercicioService {

  private apiUrl = 'http://localhost:8080/exercicios';

  constructor(private http: HttpClient) {}

  cadastrarExercicio(exercicio: ExercicioRequestDTO): Observable<ExercicioRequestDTO> {
    return this.http.post<ExercicioRequestDTO>(this.apiUrl, exercicio);
  }

  listarExercicios(): Observable<ExercicioResponseDTO[]>  {
    return this.http.get<ExercicioResponseDTO[]>(this.apiUrl);
  }

  buscarExercicioPorId(id: string): Observable<ExercicioResponseDTO> {
    return this.http.get<ExercicioResponseDTO>(`${this.apiUrl}/${id}`);
  }

  atualizarExercicio(id: string, exercicio: any) {
    return this.http.put(`${this.apiUrl}/${id}`, exercicio);
  }

}
