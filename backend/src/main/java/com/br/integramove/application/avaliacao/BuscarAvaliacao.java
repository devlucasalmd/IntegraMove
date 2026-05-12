package com.br.integramove.application.avaliacao;

import com.br.integramove.api.exception.avaliacao.AvaliacaoNaoEncontradaException;
import com.br.integramove.domain.avaliacao.Avaliacao;
import com.br.integramove.domain.avaliacao.AvaliacaoId;
import org.springframework.stereotype.Service;

@Service
public class BuscarAvaliacao {

    private final AvaliacaoRepository avaliacaoRepository;

    public BuscarAvaliacao(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public BuscarAvaliacaoOutput buscar(String alunoId, String avaliacaoId){

        AvaliacaoId id = AvaliacaoId.from(avaliacaoId);

        Avaliacao avaliacao = avaliacaoRepository.buscarPorAlunoIdEId(alunoId, id).orElseThrow(() -> new AvaliacaoNaoEncontradaException(id.getValue().toString()));

        if (!avaliacao.getAlunoId().getValue().toString().equals(alunoId)) {

            throw new AvaliacaoNaoEncontradaException(
                    avaliacao.getId().getValue().toString()
            );
        }

        return new BuscarAvaliacaoOutput(
                avaliacao.getId().getValue().toString(),
                alunoId,
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

