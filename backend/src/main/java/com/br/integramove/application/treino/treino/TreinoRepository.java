package com.br.integramove.application.treino.treino;

import com.br.integramove.domain.treino.treino.Treino;
import com.br.integramove.domain.treino.treino.TreinoId;

import java.util.List;
import java.util.Optional;

public interface TreinoRepository {

    Treino salvar(Treino treino);

    Optional<Treino> buscarPorId(TreinoId id);

    List<Treino> listarTodos();
}
