package com.br.integramove.application.aluno;

import com.br.integramove.domain.aluno.Aluno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarAlunos {

    private final AlunoRepository alunoRepository;

    public ListarAlunos(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<ListarAlunosOutput> listar() {
        List<Aluno> alunos = alunoRepository.buscarTodosAlunos();

        return alunos.stream()
                .map(aluno -> new ListarAlunosOutput(
                        aluno.getId().toString(),
                        aluno.getNome(),
                        aluno.getCpf().toString(),
                        aluno.getEmail().toString(),
                        aluno.estaAtivo()
                ))
                .toList();
    }
}
