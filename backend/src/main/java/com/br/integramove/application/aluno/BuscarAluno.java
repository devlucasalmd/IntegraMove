package com.br.integramove.application.aluno;

import com.br.integramove.api.exception.aluno.AlunoNaoEncontradoException;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.AlunoId;
import org.springframework.stereotype.Service;

@Service
public class BuscarAluno {

    private final AlunoRepository alunoRepository;

    public BuscarAluno(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public BuscarAlunoOutput buscar(String alunoId){
        AlunoId id = AlunoId.from(alunoId);

        Aluno aluno = alunoRepository.buscarPorId(id).orElseThrow(() -> new AlunoNaoEncontradoException(id));

        return new BuscarAlunoOutput(
                aluno.getId().toString(),
                aluno.getNome(),
                aluno.getDataNascimento(),
                aluno.getCpf().toString(),
                aluno.getGenero().name(),
                aluno.getTelefone(),
                aluno.getEmail().toString(),
                aluno.estaAtivo()
        );
    }
}
