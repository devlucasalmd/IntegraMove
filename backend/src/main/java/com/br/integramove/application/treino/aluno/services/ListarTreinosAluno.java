package com.br.integramove.application.treino.aluno.services;

import com.br.integramove.application.treino.aluno.TreinoAlunoRepository;
import com.br.integramove.application.treino.aluno.outputs.ListarTreinoAlunoOutput;
import com.br.integramove.domain.aluno.AlunoId;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ListarTreinosAluno {

    private final TreinoAlunoRepository repository;

    public ListarTreinosAluno(TreinoAlunoRepository repository) {
        this.repository = repository;
    }

    public List<ListarTreinoAlunoOutput> listar(String alunoId) {

        return repository.listarPorAlunoId(AlunoId.from(alunoId))
                .stream()
                .map(treinoAluno -> {

                    List<String> treinosIds = treinoAluno.getTreinosIds()
                            .stream()
                            .map(treinoId -> treinoId.getValue().toString())
                            .toList();

                    return new ListarTreinoAlunoOutput(
                            treinoAluno.getId().getValue().toString(),
                            treinoAluno.getAlunoId().getValue().toString(),
                            treinosIds,
                            treinoAluno.getNome(),
                            treinoAluno.getDataInicio(),
                            treinoAluno.getDataFim(),
                            treinoAluno.isAtivo()
                    );
                })
                .toList();
    }
}
