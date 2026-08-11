package com.br.integramove.application.treino.exercicio.services;

import com.br.integramove.api.exception.treino.exercicio.ExercicioNaoEncontradoException;
import com.br.integramove.application.treino.exercicio.ExercicioRepository;
import com.br.integramove.application.treino.exercicio.outputs.BuscarExercicioOutput;
import com.br.integramove.domain.treino.exercicio.Exercicio;
import com.br.integramove.domain.treino.exercicio.ExercicioId;
import org.springframework.stereotype.Service;

@Service
public class BuscarExercicio {

    private final ExercicioRepository repository;

    public BuscarExercicio(ExercicioRepository repository) {
        this.repository = repository;
    }

    public BuscarExercicioOutput buscar(String id){

        Exercicio exercicio = repository.buscarPorId(ExercicioId.from(id))
                .orElseThrow(() -> new ExercicioNaoEncontradoException(id));

        return new BuscarExercicioOutput(
                exercicio.getId().getValue().toString(),
                exercicio.getNome(),
                exercicio.getGrupoMuscular(),
                exercicio.getDescricao(),
                exercicio.getIntensidade(),
                exercicio.getAtivo()
        );
    }
}
