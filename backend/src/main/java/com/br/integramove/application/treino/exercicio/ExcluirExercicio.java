package com.br.integramove.application.treino.exercicio;

import com.br.integramove.api.exception.treino.exercicio.ExercicioNaoEncontradoException;
import com.br.integramove.domain.treino.exercicio.Exercicio;
import com.br.integramove.domain.treino.exercicio.ExercicioId;
import org.springframework.stereotype.Service;

@Service
public class ExcluirExercicio {

    private final ExercicioRepository repository;

    public ExcluirExercicio(ExercicioRepository repository) {
        this.repository = repository;
    }

    public void excluir(ExcluirExercicioInput input) {

        ExercicioId id = ExercicioId.from(input.id());

        Exercicio exercicio = repository.buscarPorId(id)
                .orElseThrow(() -> new ExercicioNaoEncontradoException(input.id()));

        exercicio.desativar();

        repository.salvar(exercicio);
    }
}
