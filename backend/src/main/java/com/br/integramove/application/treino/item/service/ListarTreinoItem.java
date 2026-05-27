package com.br.integramove.application.treino.item.service;

import com.br.integramove.application.treino.item.TreinoItemRepository;
import com.br.integramove.application.treino.item.outputs.ListarTreinoItemOutput;
import com.br.integramove.domain.treino.treino.TreinoId;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ListarTreinoItem {

    private final TreinoItemRepository repository;

    public ListarTreinoItem(TreinoItemRepository repository) {
        this.repository = repository;
    }

    public List<ListarTreinoItemOutput> listarPorTreino(TreinoId treinoId) {

        return repository.listarPorTreinoId(treinoId)
                .stream()
                .map(treinoItem -> new ListarTreinoItemOutput(
                        treinoItem.getId().getValue().toString(),
                        treinoItem.getExercicioId().getValue().toString(),
                        treinoItem.getNomeExercicio(),
                        treinoItem.getSeries(),
                        treinoItem.getRepeticoes(),
                        treinoItem.getCarga(),
                        treinoItem.getDescanso(),
                        treinoItem.getOrdem()
                ))
                .toList();
    }
}