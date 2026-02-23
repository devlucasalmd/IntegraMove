package com.br.integramove.application.aluno;

import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.AlunoId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository {

    void salvar(Aluno aluno);

    boolean existePorCpf(String cpf);

    Optional<Aluno> buscarPorId(AlunoId id);

    List<Aluno> buscarTodosAlunos();
}
