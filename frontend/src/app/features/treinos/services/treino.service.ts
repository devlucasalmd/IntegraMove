import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core"
import { Observable } from "rxjs";
import { TreinoResponseDTO } from "../models/treino-response.model";
import { TreinoRequestDTO } from "../models/treinos-request.model";

@Injectable({
  providedIn: 'root'
})
export class TreinoService {

  private apiUrl = 'http://localhost:8080/treinos';

  constructor(private http: HttpClient) {}

  criarTreino(treino: TreinoRequestDTO): Observable<TreinoResponseDTO> {
    return this.http.post<TreinoResponseDTO>(this.apiUrl, treino);
  }

  listarTreinos(): Observable<TreinoResponseDTO[]> {
    return this.http.get<TreinoResponseDTO[]>(this.apiUrl);
  }


}


