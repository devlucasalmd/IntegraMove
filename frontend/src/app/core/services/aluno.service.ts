import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AlunoRequestDTO } from '../../features/model/aluno.model';


@Injectable({
  providedIn: 'root'
})
export class AlunoService {

  private apiUrl = 'http://localhost:8080/alunos';

  constructor(private http: HttpClient) {}

  cadastrarAluno(aluno: AlunoRequestDTO): Observable<AlunoRequestDTO> {
    return this.http.post<AlunoRequestDTO>(this.apiUrl, aluno);
  }

}
