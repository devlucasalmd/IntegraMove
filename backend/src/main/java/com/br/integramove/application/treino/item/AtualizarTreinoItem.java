package com.br.integramove.application.treino.item;

import com.br.integramove.api.exception.treino.exercicio.TreinoItemNaoEncontradoExcpetion;
import com.br.integramove.domain.treino.TreinoItem;
import com.br.integramove.domain.treino.TreinoItemId;
import org.springframework.stereotype.Service;

@Service
public class AtualizarTreinoItem {

    private final TreinoItemRepository repository;


    public AtualizarTreinoItem(TreinoItemRepository repository) {
        this.repository = repository;
    }

    public AtualizarTreinoItemOutput atualizar(AtualizarTreinoItemInput input){

        TreinoItemId id = TreinoItemId.from(input.id());

        TreinoItem treinoItem = repository.buscarPorId(id)
                .orElseThrow(() -> new TreinoItemNaoEncontradoExcpetion(id.toString()));

        treinoItem.atualizar(
                input.series(),
                input.repeticoes(),
                input.carga(),
                input.descanso(),
                input.ordem()
        );

        TreinoItem treinoItemSalvo = repository.salvar(treinoItem);

        return new AtualizarTreinoItemOutput(
                treinoItemSalvo.getId().getValue().toString(),
                treinoItemSalvo.getExercicioId().getValue().toString(),
                treinoItemSalvo.getSeries(),
                treinoItemSalvo.getRepeticoes(),
                treinoItemSalvo.getCarga(),
                treinoItemSalvo.getDescanso(),
                treinoItemSalvo.getOrdem()
        );

    }
}
