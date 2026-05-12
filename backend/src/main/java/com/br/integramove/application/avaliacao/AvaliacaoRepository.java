package com.br.integramove.application.avaliacao;

import com.br.integramove.domain.avaliacao.Avaliacao;
import com.br.integramove.domain.avaliacao.AvaliacaoId;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository {

    void salvar(Avaliacao avaliacao);

    Optional<Avaliacao> buscarPorAlunoIdEId(String alunoId, AvaliacaoId id);

    List<Avaliacao> listarPorAlunoId(String alunoId);
}
