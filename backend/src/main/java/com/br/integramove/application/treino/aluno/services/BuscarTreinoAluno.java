package com.br.integramove.application.treino.aluno.services;

import com.br.integramove.api.exception.treino.aluno.TreinoAlunoNaoEncontradoException;
import com.br.integramove.application.treino.aluno.TreinoAlunoRepository;
import com.br.integramove.application.treino.aluno.outputs.BuscarTreinoAlunoOutput;
import com.br.integramove.domain.treino.aluno.TreinoAluno;
import com.br.integramove.domain.treino.aluno.TreinoAlunoId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarTreinoAluno {

    private final TreinoAlunoRepository repository;

    public BuscarTreinoAluno(TreinoAlunoRepository repository) {
        this.repository = repository;
    }

    public BuscarTreinoAlunoOutput buscar(String alunoId, String id) {

        TreinoAluno treinoAluno = repository.buscarPorId(TreinoAlunoId.from(id))
                .orElseThrow(() -> new TreinoAlunoNaoEncontradoException());

        if (!treinoAluno.getAlunoId().getValue().toString().equals(alunoId)) {
            throw new TreinoAlunoNaoEncontradoException();
        }

        List<String> treinosIds = treinoAluno.getTreinosIds()
                .stream()
                .map(treinoId -> treinoId.getValue().toString())
                .toList();

        return new BuscarTreinoAlunoOutput(
                treinoAluno.getId().getValue().toString(),
                treinoAluno.getAlunoId().getValue().toString(),
                treinosIds,
                treinoAluno.getNome(),
                treinoAluno.getDataInicio(),
                treinoAluno.getDataFim(),
                treinoAluno.isAtivo()
        );
    }
}