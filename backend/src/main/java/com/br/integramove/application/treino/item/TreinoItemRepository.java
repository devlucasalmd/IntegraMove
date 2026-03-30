package com.br.integramove.application.treino.item;

import com.br.integramove.domain.treino.TreinoItem;
import com.br.integramove.domain.treino.TreinoItemId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TreinoItemRepository {

    TreinoItem salvar(TreinoItem item);

//    void deletarPorId(UUID id);

    Optional<TreinoItem> buscarPorId(TreinoItemId id);

    List<TreinoItem> listarTodos();
}
