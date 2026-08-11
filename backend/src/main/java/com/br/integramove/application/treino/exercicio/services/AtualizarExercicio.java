package com.br.integramove.application.treino.exercicio.services;

import com.br.integramove.api.exception.treino.exercicio.ExercicioNaoEncontradoException;
import com.br.integramove.application.treino.exercicio.ExercicioRepository;
import com.br.integramove.application.treino.exercicio.inputs.AtualizarExercicioInput;
import com.br.integramove.application.treino.exercicio.outputs.AtualizarExercicioOutput;
import com.br.integramove.domain.treino.exercicio.Exercicio;
import com.br.integramove.domain.treino.exercicio.ExercicioId;
import org.springframework.stereotype.Service;

@Service
public class AtualizarExercicio {

    private final ExercicioRepository repository;

    public AtualizarExercicio(ExercicioRepository repository) {
        this.repository = repository;
    }

    public AtualizarExercicioOutput atualizar(AtualizarExercicioInput input){
        ExercicioId exercicioId = ExercicioId.from(input.id());

        Exercicio exercicio = repository.buscarPorId(exercicioId)
                .orElseThrow(() -> new ExercicioNaoEncontradoException(input.id()));

        exercicio.atualizar(
                input.nome(),
                input.grupoMuscular(),
                input.descricao(),
                input.intensidade(),
                input.ativo()
        );

        Exercicio atualizado = repository.salvar(exercicio);

        return new AtualizarExercicioOutput(
                atualizado.getId().getValue().toString(),
                atualizado.getNome(),
                atualizado.getGrupoMuscular(),
                atualizado.getDescricao(),
                atualizado.getIntensidade(),
                atualizado.getAtivo()
        );
    }
}
