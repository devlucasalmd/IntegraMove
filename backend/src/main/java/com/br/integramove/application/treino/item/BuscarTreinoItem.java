package com.br.integramove.application.treino.item;

import com.br.integramove.api.exception.treino.exercicio.TreinoItemNaoEncontradoExcpetion;
import com.br.integramove.domain.treino.item.TreinoItem;
import com.br.integramove.domain.treino.item.TreinoItemId;
import org.springframework.stereotype.Service;

@Service
public class BuscarTreinoItem {

    private final TreinoItemRepository repository;


    public BuscarTreinoItem(TreinoItemRepository repository) {
        this.repository = repository;
    }

    public BuscarTreinoItemOutput buscar(String id){

        TreinoItem treinoItem = repository.buscarPorId(TreinoItemId.from(id))
                .orElseThrow(() -> new TreinoItemNaoEncontradoExcpetion("Treino item não encontrado"));

        return new BuscarTreinoItemOutput(
                treinoItem.getId().getValue().toString(),
                treinoItem.getTreinoId().getValue().toString(),
                treinoItem.getExercicioId().getValue().toString(),
                treinoItem.getSeries(),
                treinoItem.getRepeticoes(),
                treinoItem.getCarga(),
                treinoItem.getDescanso(),
                treinoItem.getOrdem()
        );



    }
}
