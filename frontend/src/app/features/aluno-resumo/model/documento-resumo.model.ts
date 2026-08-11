export type TipoDocumento = 'LAUDO' | 'EXAME' | 'CONTRATO' | 'OUTRO';

export interface DocumentoResumoDTO {
  id: string;
  nome: string;
  tipo: TipoDocumento;
  dataUpload: string;
  url: string;
}
