import { ReceitaRequestDTO } from '../models/receita-request.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReceitaResponseDTO } from '../models/receita-response.model';
import { Observable } from 'rxjs';
import { ReceberReceitaRequestDTO } from '../models/receber-receita-request.model';

@Injectable({
  providedIn: 'root'
})
export class ReceitaService {

  private apiUrl = 'http://localhost:8080/contas-receber';

  constructor(private http: HttpClient) {}

  listar(): Observable<ReceitaResponseDTO[]> {
    return this.http.get<ReceitaResponseDTO[]>(this.apiUrl);
  }

  buscarPorId(receitaId: string): Observable<ReceitaResponseDTO> {
    return this.http.get<ReceitaResponseDTO>(
      `${this.apiUrl}/${receitaId}`
    );
  }

  criar(
    request: ReceitaRequestDTO
  ): Observable<ReceitaResponseDTO> {
    return this.http.post<ReceitaResponseDTO>(
      this.apiUrl,
      request
    );
  }

  atualizar(
    receitaId: string,
    request: ReceitaRequestDTO
  ): Observable<ReceitaResponseDTO> {
    return this.http.put<ReceitaResponseDTO>(
      `${this.apiUrl}/${receitaId}`,
      request
    );
  }

  receber(
    receitaId: string,
    request: ReceberReceitaRequestDTO
  ): Observable<ReceitaResponseDTO> {

    return this.http.patch<ReceitaResponseDTO>(
      `${this.apiUrl}/${receitaId}/receber`,
      request
    );
  }

  cancelar(receitaId: string): Observable<ReceitaResponseDTO> {

    return this.http.patch<ReceitaResponseDTO>(
      `${this.apiUrl}/${receitaId}/cancelar`,
      {}
    );
  }

}
