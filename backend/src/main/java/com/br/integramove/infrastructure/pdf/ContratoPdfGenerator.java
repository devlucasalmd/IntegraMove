package com.br.integramove.infrastructure.pdf;

import com.br.integramove.application.contrato.DadosDocumentoContrato;
import com.br.integramove.application.contrato.GeradorDocumentoContratoPort;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

@Component
public class ContratoPdfGenerator implements GeradorDocumentoContratoPort {

    private static final DateTimeFormatter DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final float MARGEM_ESQUERDA = 60f;
    private static final float LINHA = 20f;

    private final Path diretorioBase;

    public ContratoPdfGenerator(@Value("${contrato.storage.path:storage/contratos}") String diretorioBase) {
        this.diretorioBase = Path.of(diretorioBase);
    }

    @Override
    public String gerar(DadosDocumentoContrato dados) {
        try {
            Files.createDirectories(diretorioBase);
            Path destino = diretorioBase.resolve(dados.contratoId() + ".pdf");

            try (PDDocument documento = new PDDocument()) {
                PDPage pagina = new PDPage();
                documento.addPage(pagina);

                PDType1Font fonteTitulo = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                PDType1Font fonteTexto = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

                try (PDPageContentStream conteudo = new PDPageContentStream(documento, pagina)) {
                    float y = pagina.getMediaBox().getHeight() - 80f;

                    y = escreverLinha(conteudo, fonteTitulo, 16, y, "Contrato de Prestação de Serviços");
                    y -= LINHA;

                    y = escreverLinha(conteudo, fonteTexto, 12, y, "Aluno: " + dados.alunoNome());
                    y = escreverLinha(conteudo, fonteTexto, 12, y, "Plano: " + dados.planoNome());
                    y = escreverLinha(conteudo, fonteTexto, 12, y, "Valor do plano: R$ " + dados.planoValor());
                    y = escreverLinha(conteudo, fonteTexto, 12, y, "Início: " + dados.dataInicio().format(DATA));
                    y = escreverLinha(conteudo, fonteTexto, 12, y, "Fim: " + dados.dataFim().format(DATA));
                    escreverLinha(conteudo, fonteTexto, 12, y, "Dia de vencimento: " + dados.diaVencimento());
                }

                documento.save(destino.toFile());
            }

            return destino.toString();
        } catch (IOException e) {
            throw new UncheckedIOException("Falha ao gerar documento do contrato", e);
        }
    }

    private float escreverLinha(PDPageContentStream conteudo, PDType1Font fonte, int tamanho, float y, String texto) throws IOException {
        conteudo.beginText();
        conteudo.setFont(fonte, tamanho);
        conteudo.newLineAtOffset(MARGEM_ESQUERDA, y);
        conteudo.showText(texto);
        conteudo.endText();
        return y - LINHA;
    }
}