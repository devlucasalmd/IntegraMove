package com.br.integramove.application.avaliacao;

import com.br.integramove.domain.avaliacao.*;
import com.br.integramove.application.avaliacao.AvaliacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarAvaliacao {

    private final AvaliacaoRepository avaliacaoRepository;

    public CriarAvaliacao(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public CriarAvaliacaoOutput criar( CriarAvaliacaoInput avaliacaoInput ){

        AvaliacaoId id = AvaliacaoId.novo();

        Avaliacao avaliacao = new Avaliacao(
                id,
                avaliacaoInput.dataAvaliacao(),
                avaliacaoInput.peso(),
                avaliacaoInput.altura(),
                avaliacaoInput.imc(),
                avaliacaoInput.percentualGordura(),
                avaliacaoInput.circuferencia()
        );

        avaliacaoRepository.salvar(avaliacao);

        return new CriarAvaliacaoOutput(id.getValue().toString());
    }
}
