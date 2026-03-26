package com.br.integramove.application.treino.exercicio;

import com.br.integramove.api.exception.treino.exercicio.ExercicioNaoEncontradoException;
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
                .orElseThrow(() -> new ExercicioNaoEncontradoException("Exercicio não encontado"));

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
