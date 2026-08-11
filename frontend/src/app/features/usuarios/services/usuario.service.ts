import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsuarioRequestDTO } from '../models/usuario-request.model';
import { UsuarioDetalheResponseDTO } from '../models/usuario-detalhe-response.model';
import { UsuarioResumoResponseDTO } from '../models/usuario-resumo-response.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiUrl = 'http://localhost:8080/usuarios';

  constructor(private http: HttpClient) {}

  cadastrarUsuario(usuario: UsuarioRequestDTO): Observable<UsuarioDetalheResponseDTO> {
    return this.http.post<UsuarioDetalheResponseDTO>(this.apiUrl, usuario);
  }

  listarUsuarios(): Observable<UsuarioResumoResponseDTO[]> {
    return this.http.get<UsuarioResumoResponseDTO[]>(this.apiUrl);
  }

  buscarUsuarioPorId(id: string): Observable<UsuarioDetalheResponseDTO> {
    return this.http.get<UsuarioDetalheResponseDTO>(`${this.apiUrl}/${id}`);
  }

  atualizarUsuario(id: string, usuario: UsuarioRequestDTO): Observable<UsuarioDetalheResponseDTO> {
    return this.http.put<UsuarioDetalheResponseDTO>(`${this.apiUrl}/${id}`, usuario);
  }

  desativarUsuario(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
