import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { delay } from 'rxjs/operators';
import { PendenciaResumoDTO } from '../model/pendencia-resumo.model';
import { PresencaResumoDTO } from '../model/presenca-resumo.model';
import { DocumentoResumoDTO } from '../model/documento-resumo.model';

/**
 * Service responsável pelos dados da tela "Resumo do aluno".
 *
 * A maior parte dos dados consumidos aqui ainda não possui endpoint no
 * backend, por isso os métodos abaixo retornam dados mockados (marcados
 * com "TODO: mock"). Assim que os endpoints forem implementados, basta
 * trocar o corpo de cada método pela chamada HTTP real, mantendo a mesma
 * assinatura (Observable) já usada nos demais services do projeto.
 */
@Injectable({
  providedIn: 'root'
})
export class AlunoResumoService {

  private apiUrl = 'http://localhost:8080/alunos';

  constructor(private http: HttpClient) {}

  /**
   * TODO: mock — o backend não expõe o dia de pagamento do aluno hoje
   * (não existe em AlunoDetalheResponseDTO nem em PagamentoResponseDTO).
   * Sugestão de endpoint: GET /alunos/{alunoId}/dia-pagamento
   */
  buscarDiaPagamento(alunoId: string): Observable<number> {
    const diaPagamentoMock = 10;

    return of(diaPagamentoMock).pipe(delay(300));
  }

  /**
   * TODO: mock — não existe endpoint de pendências do aluno no backend.
   * Sugestão de endpoint: GET /alunos/{alunoId}/pendencias
   */
  listarPendencias(alunoId: string): Observable<PendenciaResumoDTO[]> {
    const pendenciasMock: PendenciaResumoDTO[] = [
      { id: '1', tipo: 'ANAMNESE', descricao: 'Anamnese não preenchida', status: 'PENDENTE' },
      { id: '2', tipo: 'PAGAMENTO', descricao: 'Mensalidade em atraso', status: 'PENDENTE' },
      { id: '3', tipo: 'AVALIACAO', descricao: 'Avaliação física vencida', status: 'RESOLVIDA' },
      { id: '4', tipo: 'CONTRATO', descricao: 'Contrato assinado', status: 'RESOLVIDA' }
    ];

    return of(pendenciasMock).pipe(delay(300));
  }

  /**
   * TODO: mock — não existe endpoint de histórico de presença/acesso do
   * aluno no backend. Sugestão de endpoint:
   * GET /alunos/{alunoId}/presencas?mes={mes}&ano={ano}
   */
  listarPresencasDoMes(alunoId: string): Observable<PresencaResumoDTO[]> {
    const presencasMock: PresencaResumoDTO[] = [
      { id: '1', data: '2026-08-01', horario: '07:15' },
      { id: '2', data: '2026-08-03', horario: '18:40' },
      { id: '3', data: '2026-08-05', horario: '07:05' },
      { id: '4', data: '2026-08-08', horario: '19:10' },
      { id: '5', data: '2026-08-10', horario: '06:55' }
    ];

    return of(presencasMock).pipe(delay(300));
  }

  /**
   * TODO: mock — não existe endpoint de documentos anexados ao aluno no
   * backend. Sugestão de endpoint: GET /alunos/{alunoId}/documentos
   */
  listarDocumentos(alunoId: string): Observable<DocumentoResumoDTO[]> {
    const documentosMock: DocumentoResumoDTO[] = [
      {
        id: '1',
        nome: 'laudo-medico.pdf',
        tipo: 'LAUDO',
        dataUpload: '2026-07-20',
        url: '#'
      }
    ];

    return of(documentosMock).pipe(delay(300));
  }

  /**
   * TODO: mock — não existe endpoint de upload de documento no backend.
   * Sugestão de endpoint: POST /alunos/{alunoId}/documentos
   * (multipart/form-data, campo "arquivo" + metadados "tipo"/"descricao").
   */
  enviarDocumento(alunoId: string, arquivo: File): Observable<DocumentoResumoDTO> {
    const documentoMock: DocumentoResumoDTO = {
      id: crypto.randomUUID(),
      nome: arquivo.name,
      tipo: 'OUTRO',
      dataUpload: new Date().toISOString().substring(0, 10),
      url: '#'
    };

    return of(documentoMock).pipe(delay(500));
  }
}
