package com.br.integramove.application.treino.item;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarTreinoItem {

    private final TreinoItemRepository repository;


    public ListarTreinoItem(TreinoItemRepository repository) {
        this.repository = repository;
    }

    public List<ListarTreinoItemOutput> listar(){

        return repository.listarTodos()
                .stream()
                .map( treinoItem -> new ListarTreinoItemOutput(
                        treinoItem.getId().getValue().toString(),
                        treinoItem.getExercicioId().getValue().toString(),
                        treinoItem.getSeries(),
                        treinoItem.getRepeticoes(),
                        treinoItem.getCarga(),
                        treinoItem.getDescanso(),
                        treinoItem.getOrdem()
                ))
                .toList();
    }
}
