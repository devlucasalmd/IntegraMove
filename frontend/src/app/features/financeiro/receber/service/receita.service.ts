import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReceitasResponseDTO } from '../models/receita-response.model';

@Injectable({
  providedIn: 'root'
})
export class ReceitaService {

  private readonly apiUrl = 'http://localhost:8080/contas-receber';

  constructor(
    private readonly http: HttpClient
  ) {}

  listarReceitas(): Observable<ReceitasResponseDTO[]> {
    return this.http.get<ReceitasResponseDTO[]>(this.apiUrl);
  }

  buscarPorId(id: string): Observable<ReceitasResponseDTO> {
    return this.http.get<ReceitasResponseDTO>(
      `${this.apiUrl}/${id}`
    );
  }

  buscarCategoria(categoria: string): Observable<ReceitasResponseDTO[]> {
    return this.http.get<ReceitasResponseDTO[]>(
      `${this.apiUrl}/categoria/${categoria}`
    );
  }
}
