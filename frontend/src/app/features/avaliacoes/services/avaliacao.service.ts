import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AvaliacaoRequestDTO } from '../models/avaliacao.model';

@Injectable({
  providedIn: 'root'
})
export class AvaliacaoService {

  private apiUrl = 'http://localhost:8080/avaliacao';

  constructor(private http: HttpClient) {}

  cadastrarAvaliacao(avaliacao: AvaliacaoRequestDTO): Observable<any> {
    return this.http.post(this.apiUrl, avaliacao);
  }

}
