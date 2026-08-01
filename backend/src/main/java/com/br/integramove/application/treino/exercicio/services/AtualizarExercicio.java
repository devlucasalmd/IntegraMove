package com.br.integramove.application.treino.exercicio;

import com.br.integramove.api.exception.treino.exercicio.ExercicioNaoEncontradoException;
import com.br.integramove.application.treino.exercicio.inputs.AtualizarExercicioInput;
import com.br.integramove.application.treino.exercicio.outputs.AtualizarExercicioOutput;
import com.br.integramove.domain.treino.exercicio.Exercicio;
import com.br.integramove.domain.treino.exercicio.ExercicioId;
import com.br.integramove.domain.enums.GrupoMuscular;
import com.br.integramove.domain.enums.Intensidade;
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

        GrupoMuscular grupo = GrupoMuscular.from(input.grupoMuscular());

        exercicio.atualizar(
                input.nome(),
                grupo,
                input.descricao(),
                Intensidade.valueOf(input.intensidade()),
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
