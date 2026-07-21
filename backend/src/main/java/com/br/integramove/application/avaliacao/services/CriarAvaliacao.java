package com.br.integramove.application.avaliacao.services;

import com.br.integramove.application.avaliacao.AvaliacaoRepository;
import com.br.integramove.application.avaliacao.inputs.CriarAvaliacaoInput;
import com.br.integramove.application.avaliacao.outputs.CriarAvaliacaoOutput;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.avaliacao.*;
import org.springframework.stereotype.Service;

@Service
public class CriarAvaliacao {

    private final AvaliacaoRepository avaliacaoRepository;

    public CriarAvaliacao(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public CriarAvaliacaoOutput criar(CriarAvaliacaoInput avaliacaoInput ){

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

        return new CriarAvaliacaoOutput(
                id.getValue().toString(),
                avaliacao.getAlunoId().getValue().toString(),
                avaliacao.getDataAvaliacao(),
                avaliacao.getRemadaBracoD(),
                avaliacao.getRemadaBracoE(),
                avaliacao.getElevacaoLatD(),
                avaliacao.getElevacaoLatE(),
                avaliacao.getExtensaoJoelhoD(),
                avaliacao.getExtensaoJoelhoE(),
                avaliacao.getFlexaoJoelhoD(),
                avaliacao.getFlexaoJoelhoE(),
                avaliacao.getExtensaoQuadrilD(),
                avaliacao.getExtensaoQuadrilE()
        );
    }
}
