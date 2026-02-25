package com.br.integramove.application.avaliacao;

import com.br.integramove.domain.avaliacao.Avaliacao;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository {

    void salvar(Avaliacao avaliacao);

    Optional<Avaliacao> buscarPorId(String id);

//    List<Avaliacao> listarAvaliacoes();
}
