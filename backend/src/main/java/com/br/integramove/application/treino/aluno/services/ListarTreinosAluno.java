package com.br.integramove.application.treino.aluno.services;

import com.br.integramove.application.treino.aluno.TreinoAlunoRepository;
import com.br.integramove.application.treino.aluno.outputs.ListarTreinoAlunoOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarTreinosAluno {

    private final TreinoAlunoRepository repository;

    public ListarTreinosAluno(TreinoAlunoRepository repository){
        this.repository = repository;
    }

    public List<ListarTreinoAlunoOutput> listar(){

        return repository.listarTodos()
                .stream()
                .map(treinoAluno -> new ListarTreinoAlunoOutput(
                        treinoAluno.getId().getValue().toString(),
                        treinoAluno.getTreinoId().getValue().toString(),
                        treinoAluno.getAlunoId().getValue().toString(),
                        treinoAluno.getNome(),
                        treinoAluno.getDataInicio(),
                        treinoAluno.isAtivo()
                ))
                .toList();
    }
}
