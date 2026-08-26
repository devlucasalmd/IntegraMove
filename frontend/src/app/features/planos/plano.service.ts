import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { PlanoResponseDTO } from "./models/plano-response";
import { PlanoRequestDTO } from "./models/plano-request.model";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PlanoService {

  private apiUrl = 'http://localhost:8080/planos';

  constructor(private http: HttpClient) {}

  listarPlanos(): Observable<PlanoResponseDTO[]> {
    return this.http.get<PlanoResponseDTO[]>(this.apiUrl);
  }

  buscarPorId(id: string): Observable<PlanoResponseDTO> {
    return this.http.get<PlanoResponseDTO>(`${this.apiUrl}/${id}`);
  }

  /**
   * POST /planos
   */
  criar(dto: PlanoRequestDTO): Observable<PlanoResponseDTO> {
    return this.http.post<PlanoResponseDTO>(this.apiUrl, dto);
  }

  /**
   * PUT /planos/{id}
   *
   * Exige o payload completo. O backend rejeita com HTTP 400 caso o
   * `valor` enviado seja diferente do valor já persistido no plano.
   */
  atualizar(id: string, dto: PlanoRequestDTO): Observable<PlanoResponseDTO> {
    return this.http.put<PlanoResponseDTO>(`${this.apiUrl}/${id}`, dto);
  }
}
