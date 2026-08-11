package com.br.integramove.application.treino.item.service;

import com.br.integramove.api.exception.treino.exercicio.TreinoItemNaoEncontradoException;
import com.br.integramove.application.treino.item.TreinoItemRepository;
import com.br.integramove.application.treino.item.outputs.BuscarTreinoItemOutput;
import com.br.integramove.domain.treino.item.TreinoItem;
import com.br.integramove.domain.treino.item.TreinoItemId;
import org.springframework.stereotype.Service;

@Service
public class BuscarTreinoItem {

    private final TreinoItemRepository repository;

    public BuscarTreinoItem(TreinoItemRepository repository) {
        this.repository = repository;
    }

    public BuscarTreinoItemOutput buscar(String id) {

        TreinoItem treinoItem = repository.buscarPorId(TreinoItemId.from(id))
                .orElseThrow(() -> new TreinoItemNaoEncontradoException(id));

        return new BuscarTreinoItemOutput(
                treinoItem.getId().getValue().toString(),
                treinoItem.getExercicioId().getValue().toString(),
                treinoItem.getNomeExercicio(),
                treinoItem.getSeries(),
                treinoItem.getRepeticoes(),
                treinoItem.getCarga(),
                treinoItem.getDescanso(),
                treinoItem.getOrdem()
        );
    }
}
