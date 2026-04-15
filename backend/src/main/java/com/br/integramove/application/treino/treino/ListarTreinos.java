package com.br.integramove.application.treino.treino;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarTreinos {

    private final TreinoRepository repository;

    public ListarTreinos(TreinoRepository repository) {
        this.repository = repository;
    }

    public List<ListarTreinosOutput> listar() {

        return repository.listarTodos()
                .stream()
                .map(treino -> new ListarTreinosOutput(
                        treino.getId().getValue().toString(),
                        treino.getNome(),
                        treino.getResponsavel(),
                        treino.getFuncionalidade(),
                        treino.getNivel(),
                        treino.getRepeticoes()
                ))
                .toList();
    }
}
