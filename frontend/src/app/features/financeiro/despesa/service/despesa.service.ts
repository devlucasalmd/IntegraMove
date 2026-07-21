import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { DespesaResponseDTO } from "../models/despesa-response.model";
import { DespesaRequestDTO } from "../models/despesa-request.model";
import { AtualizarDespesaRequestDTO } from "../models/atualizar-despesa-request.model";
import { PagarDespesaRequestDTO } from "../models/pagar-despesa.model";

@Injectable({
  providedIn: 'root'
})
export class DespesaService {

  private apiUrl = 'http://localhost:8080/despesas';

  constructor(private http: HttpClient) {}

  listar(): Observable<DespesaResponseDTO[]> {
    return this.http.get<DespesaResponseDTO[]>(this.apiUrl);
  }

  buscarPorId(despesaId: string): Observable<DespesaResponseDTO> {
    return this.http.get<DespesaResponseDTO>(`${this.apiUrl}/${despesaId}`);
  }

  criar(request: DespesaRequestDTO): Observable<DespesaResponseDTO> {
    return this.http.post<DespesaResponseDTO>(this.apiUrl, request);
  }

  atualizar(
    despesaId: string,
    request: AtualizarDespesaRequestDTO
  ): Observable<DespesaResponseDTO> {
    return this.http.put<DespesaResponseDTO>(
      `${this.apiUrl}/${despesaId}`,
      request
    );
  }

  pagar(
    despesaId: string,
    request: PagarDespesaRequestDTO
  ): Observable<DespesaResponseDTO> {
    return this.http.patch<DespesaResponseDTO>(
      `${this.apiUrl}/${despesaId}/pagar`,
      request
    );
  }

  cancelar(despesaId: string): Observable<DespesaResponseDTO> {
    return this.http.patch<DespesaResponseDTO>(
      `${this.apiUrl}/${despesaId}/cancelar`,
      {}
    );
  }
}
