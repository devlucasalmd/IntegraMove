package com.br.integramove.application.avaliacao;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.avaliacao.*;
import com.br.integramove.application.avaliacao.AvaliacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CriarAvaliacao {

    private final AvaliacaoRepository avaliacaoRepository;

    public CriarAvaliacao(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public CriarAvaliacaoOutput criar( CriarAvaliacaoInput avaliacaoInput ){

        AvaliacaoId id = AvaliacaoId.novo();
        AlunoId alunoId = AlunoId.from(avaliacaoInput.alunoId());

        Avaliacao avaliacao = new Avaliacao(
                id,
                alunoId,
                avaliacaoInput.dataAvaliacao(),
                avaliacaoInput.remadaBracoD(),
                avaliacaoInput.remadaBracoE(),
                avaliacaoInput.elevacaoLatD(),
                avaliacaoInput.elevacaoLatE(),
                avaliacaoInput.extensaoJoelhoD(),
                avaliacaoInput.extensaoJoelhoE(),
                avaliacaoInput.flexaoJoelhoD(),
                avaliacaoInput.flexaoJoelhoE(),
                avaliacaoInput.extensaoQuadrilD(),
                avaliacaoInput.extensaoQuadrilE()
        );

        avaliacaoRepository.salvar(avaliacao);

        return new CriarAvaliacaoOutput(id.getValue().toString());
    }
}
