package com.br.integramove.application.treino.exercicio;

import com.br.integramove.domain.treino.exercicio.Exercicio;
import com.br.integramove.domain.treino.exercicio.ExercicioId;

import java.util.List;
import java.util.Optional;

public interface ExercicioRepository {

    Exercicio salvar(Exercicio exercicio);

    Optional<Exercicio> buscarPorId(ExercicioId id);

    List<Exercicio> listarTodos();

    void deletarFisico(ExercicioId id);

}
