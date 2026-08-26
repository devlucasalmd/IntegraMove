import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { VendaRequestDTO, VendaResponseDTO } from '../model/venda.model';

/**
 * Service responsável pelas vendas de um aluno (aba "Vendas" do perfil).
 */
@Injectable({
  providedIn: 'root'
})
export class VendaService {

  private apiUrl = 'http://localhost:8080/vendas';

  constructor(private http: HttpClient) {}

  /**
   * GET /vendas/aluno/{alunoId}
   */
  listarPorAluno(alunoId: string): Observable<VendaResponseDTO[]> {
    return this.http.get<VendaResponseDTO[]>(`${this.apiUrl}/aluno/${alunoId}`);
  }

  /**
   * POST /vendas
   */
  criar(request: VendaRequestDTO): Observable<VendaResponseDTO> {
    return this.http.post<VendaResponseDTO>(this.apiUrl, request);
  }
}
