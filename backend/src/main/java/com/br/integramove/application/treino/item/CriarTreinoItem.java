package com.br.integramove.application.treino.item;

import com.br.integramove.domain.treino.item.TreinoItem;
import com.br.integramove.domain.treino.item.TreinoItemId;
import com.br.integramove.domain.treino.exercicio.ExercicioId;
import com.br.integramove.domain.treino.treino.TreinoId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CriarTreinoItem {

    private final TreinoItemRepository repository;


    public CriarTreinoItem(TreinoItemRepository repository) {
        this.repository = repository;
    }

    public CriarTreinoItemOutput criar(CriarTreinoItemInput treinoItemInput){

        TreinoItem treinoItem = new TreinoItem(
                TreinoItemId.novo(),
                ExercicioId.of(UUID.fromString(treinoItemInput.exercicioId())),
                TreinoId.of(UUID.fromString(treinoItemInput.treinoId())),
                treinoItemInput.series(),
                treinoItemInput.repeticoes(),
                treinoItemInput.carga(),
                treinoItemInput.descanso(),
                treinoItemInput.ordem()
        );

        TreinoItem treinoItemSalvo = repository.salvar(treinoItem);

        return new CriarTreinoItemOutput(
                treinoItemSalvo.getId().getValue().toString(),
                treinoItemSalvo.getTreinoId().getValue().toString(),
                treinoItemSalvo.getExercicioId().getValue().toString(),
                treinoItemSalvo.getSeries(),
                treinoItemSalvo.getRepeticoes(),
                treinoItemSalvo.getCarga(),
                treinoItemSalvo.getDescanso(),
                treinoItemSalvo.getOrdem()
        );
    }
}
