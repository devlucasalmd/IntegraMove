package com.br.integramove.application.treino.item;

import com.br.integramove.domain.treino.item.TreinoItem;
import com.br.integramove.domain.treino.item.TreinoItemId;
import com.br.integramove.domain.treino.treino.TreinoId;

import java.util.List;
import java.util.Optional;

public interface TreinoItemRepository {

    TreinoItem salvar(TreinoItem item);

//    void deletarPorId(UUID id);

    Optional<TreinoItem> buscarPorId(TreinoItemId id);

    List<TreinoItem> listarTodos();

    List<TreinoItem> listarPorTreinoId(TreinoId treinoId);
}
