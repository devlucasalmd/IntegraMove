import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { PlanoResponseDTO } from "./models/plano-response";
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
}
