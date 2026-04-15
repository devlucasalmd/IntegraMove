package com.br.integramove.application.treino.aluno;

import com.br.integramove.domain.treino.aluno.TreinoAluno;
import com.br.integramove.domain.treino.aluno.TreinoAlunoId;

import java.util.List;
import java.util.Optional;

public interface TreinoAlunoRepository {

    TreinoAluno salvar(TreinoAluno treinoAluno);

    Optional<TreinoAluno> buscarPorId(TreinoAlunoId id);

    List<TreinoAluno> listarTodos();

}
