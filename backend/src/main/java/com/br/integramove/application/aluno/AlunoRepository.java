package com.br.integramove.application.aluno;

import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.plano.PlanoId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository {

    Aluno salvar(Aluno aluno);

    Optional<Aluno> buscarPorId(AlunoId id);

    List<Aluno> listarTodos();

    boolean existePorCpf(String cpf);

    Optional<Aluno> buscarPorCpf(String cpf);

}
