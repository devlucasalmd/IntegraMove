package com.br.integramove.application.avaliacao;

import com.br.integramove.api.exception.avaliacao.AvaliacaoNaoEncontradaException;
import com.br.integramove.domain.avaliacao.Avaliacao;
import org.springframework.stereotype.Service;

@Service
public class BuscarAvaliacao {

    private final AvaliacaoRepository avaliacaoRepository;

    public BuscarAvaliacao(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public BuscarAvaliacaoOutput buscar(String id){

        Avaliacao avaliacao = avaliacaoRepository.buscarPorId(id).orElseThrow(() -> new AvaliacaoNaoEncontradaException(id));

        return new BuscarAvaliacaoOutput(
                avaliacao.getDataAvaliacao(),
                avaliacao.getPeso(),
                avaliacao.getAltura(),
                avaliacao.getImc(),
                avaliacao.getPercentualGordura(),
                avaliacao.getCircuferencia()
        );
    }
}

