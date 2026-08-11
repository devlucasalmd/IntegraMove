package com.br.integramove.application.treino.exercicio.services;

import com.br.integramove.application.treino.exercicio.ExercicioRepository;
import com.br.integramove.application.treino.exercicio.outputs.ListarExercicioOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarExercicios {

    private final ExercicioRepository repository;

    public ListarExercicios(ExercicioRepository repository) {
        this.repository = repository;
    }

    public List<ListarExercicioOutput> listar(){

        return repository.listarTodos()
                .stream()
                .map( exercicio -> new ListarExercicioOutput(
                        exercicio.getId().getValue().toString(),
                        exercicio.getNome(),
                        exercicio.getGrupoMuscular(),
                        exercicio.getDescricao(),
                        exercicio.getIntensidade(),
                        exercicio.getAtivo()
                ))
                .toList();
    }
}
