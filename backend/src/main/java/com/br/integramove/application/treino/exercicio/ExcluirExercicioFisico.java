package com.br.integramove.application.treino.exercicio;

import com.br.integramove.api.exception.treino.exercicio.ExercicioNaoEncontradoException;
import com.br.integramove.domain.treino.exercicio.Exercicio;
import com.br.integramove.domain.treino.exercicio.ExercicioId;
import org.springframework.stereotype.Service;

@Service
public class ExcluirExercicioFisico {

    private final ExercicioRepository repository;

    public ExcluirExercicioFisico(ExercicioRepository repository) {
        this.repository = repository;
    }

    public void excluir(String id) {

        ExercicioId exercicioId = ExercicioId.from(id);

        Exercicio exercicio = repository.buscarPorId(exercicioId)
                .orElseThrow(() -> new ExercicioNaoEncontradoException(id));

        if (exercicio.getAtivo()) {
            throw new IllegalStateException(
                    "Exercício deve estar inativo antes da exclusão física"
            );
        }

        repository.deletarFisico(exercicioId);
    }

}
