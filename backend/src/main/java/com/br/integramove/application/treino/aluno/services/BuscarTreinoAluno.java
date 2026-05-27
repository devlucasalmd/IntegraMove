package com.br.integramove.application.treino.aluno.services;

import com.br.integramove.api.exception.treino.aluno.TreinoAlunoNaoEncontradoExcpetion;
import com.br.integramove.application.treino.aluno.TreinoAlunoRepository;
import com.br.integramove.application.treino.aluno.outputs.BuscarTreinoAlunoOutput;
import com.br.integramove.domain.treino.aluno.TreinoAluno;
import com.br.integramove.domain.treino.aluno.TreinoAlunoId;
import org.springframework.stereotype.Service;

@Service
public class BuscarTreinoAluno {

    private final TreinoAlunoRepository repository;

    public BuscarTreinoAluno(TreinoAlunoRepository repository) {
        this.repository = repository;
    }

    public BuscarTreinoAlunoOutput buscar(String id){

        TreinoAluno treinoAluno = repository.buscarPorId(TreinoAlunoId.from(id))
                .orElseThrow(() -> new TreinoAlunoNaoEncontradoExcpetion("Treino Aluno não encontrado"));

        return new BuscarTreinoAlunoOutput(
                treinoAluno.getId().getValue().toString(),
                treinoAluno.getTreinoId().getValue().toString(),
                treinoAluno.getAlunoId().getValue().toString(),
                treinoAluno.getNome(),
                treinoAluno.getDataInicio(),
                treinoAluno.isAtivo()
        );
    }
}
