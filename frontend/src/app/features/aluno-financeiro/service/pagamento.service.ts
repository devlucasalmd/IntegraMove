import { PagamentoResponseDTO } from './../model/pagamento-response.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PagarPagamentoRequestDTO } from '../model/pagar-pagamento-request';


@Injectable({
  providedIn: 'root'
})
export class PagamentoService {

  private apiUrl = 'http://localhost:8080/financeiro';

  constructor(private http: HttpClient) {}

  listarPorAluno(alunoId: string): Observable<PagamentoResponseDTO[]> {
    return this.http.get<PagamentoResponseDTO[]>(
      `${this.apiUrl}/aluno/${alunoId}`
    );
  }

  buscarPorId(pagamentoId: string): Observable<PagamentoResponseDTO> {
    return this.http.get<PagamentoResponseDTO>(
      `${this.apiUrl}/${pagamentoId}`
    );
  }

  pagarPagamento(
    pagamentoId: string,
    request: PagarPagamentoRequestDTO
  ): Observable<PagamentoResponseDTO> {
    return this.http.post<PagamentoResponseDTO>(
      `${this.apiUrl}/${pagamentoId}/pagar`,
      request
    );
  }
}
