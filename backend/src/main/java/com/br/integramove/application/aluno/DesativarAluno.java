package com.br.integramove.application.aluno;

import com.br.integramove.api.exception.aluno.AlunoNaoEncontradoException;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.AlunoId;
import org.springframework.stereotype.Service;

@Service
public class DesativarAluno {

    private final AlunoRepository alunoRepository;

    public DesativarAluno(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void desativar(DesativarAlunoInput alunoInput){

        AlunoId id = AlunoId.from(alunoInput.id());

        Aluno aluno = alunoRepository.buscarPorId(id).orElseThrow(() -> new AlunoNaoEncontradoException(id));

        aluno.setAtivo(false);

        alunoRepository.salvar(aluno);
    }
}
