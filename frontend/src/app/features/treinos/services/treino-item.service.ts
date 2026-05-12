import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

import { TreinoItemRequestDTO } from "../models/treino-item-request";
import { TreinoItemResponseDTO } from "../models/treino-item-response";


@Injectable({
  providedIn: 'root'
})
export class TreinoItemService {

  private apiUrl = 'http://localhost:8080/treino';

  constructor(private http: HttpClient) {}

  adicionarItemAoTreino(
    treinoId: string,
    request: TreinoItemRequestDTO,
  ): Observable<TreinoItemResponseDTO> {
    return this.http.post<TreinoItemResponseDTO>(
      `${this.apiUrl}/${treinoId}/item`,
      request
    );
  }

  listarItensDoTreino(treinoId: string): Observable<TreinoItemResponseDTO[]> {
    return this.http.get<TreinoItemResponseDTO[]>(
      `${this.apiUrl}/${treinoId}/item`
    );
  }

  buscarItemDoTreino(
    treinoId: string,
    itemId: string
  ): Observable<TreinoItemResponseDTO> {
    return this.http.get<TreinoItemResponseDTO>(
      `${this.apiUrl}/${treinoId}/item/${itemId}`
    );
  }

  atualizarItemDoTreino(
    treinoId: string,
    itemId: string,
    request: TreinoItemRequestDTO
  ): Observable<TreinoItemResponseDTO> {
    return this.http.put<TreinoItemResponseDTO>(
      `${this.apiUrl}/${treinoId}/item/${itemId}`,
      request
    );
  }
}
