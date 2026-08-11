import { PagamentoResponseDTO } from './../model/pagamento-response.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PagamentoRequestDTO } from '../model/pagamento-request.model';
import { PagarPagamentoRequestDTO } from '../model/pagar-pagamento-request';


@Injectable({
  providedIn: 'root'
})
export class PagamentoService {

  private apiUrl = 'http://localhost:8080/alunos';

  constructor(private http: HttpClient) {}

  listarPorAluno(alunoId: string): Observable<PagamentoResponseDTO[]> {
    return this.http.get<PagamentoResponseDTO[]>(
      `${this.apiUrl}/${alunoId}/pagamentos`
    );
  }

  buscarPorId(alunoId: string, pagamentoId: string): Observable<PagamentoResponseDTO> {
    return this.http.get<PagamentoResponseDTO>(
      `${this.apiUrl}/${alunoId}/pagamentos/${pagamentoId}`
    );
  }

  criarPagamento(
    alunoId: string,
    pagamento: PagamentoRequestDTO
  ): Observable<PagamentoResponseDTO> {
    return this.http.post<PagamentoResponseDTO>(
      `${this.apiUrl}/${alunoId}/pagamentos`,
      pagamento
    );
  }

  pagarPagamento(
    alunoId: string,
    pagamentoId: string,
    request: PagarPagamentoRequestDTO
  ): Observable<PagamentoResponseDTO> {
    return this.http.patch<PagamentoResponseDTO>(
      `${this.apiUrl}/${alunoId}/pagamentos/${pagamentoId}/pagar`,
      request
    );
  }

  cancelarPagamento(
    alunoId: string,
    pagamentoId: string
  ): Observable<PagamentoResponseDTO> {
    return this.http.patch<PagamentoResponseDTO>(
      `${this.apiUrl}/${alunoId}/pagamentos/${pagamentoId}/cancelar`,
      {}
    );
  }
}
