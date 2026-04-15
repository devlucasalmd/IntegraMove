package com.br.integramove.application.treino.aluno;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.treino.aluno.TreinoAluno;
import com.br.integramove.domain.treino.aluno.TreinoAlunoId;
import com.br.integramove.domain.treino.treino.TreinoId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CriarTreinoAluno {

    private final TreinoAlunoRepository repository;

    public CriarTreinoAluno(TreinoAlunoRepository repository) {
        this.repository = repository;
    }

    public CriarTreinoAlunoOutput criar(CriarTreinoAlunoInput input){

        TreinoAluno treinoAluno = new TreinoAluno(
                TreinoAlunoId.novo(),
                TreinoId.of(UUID.fromString(input.treinoId())),
                AlunoId.of(UUID.fromString(input.treinoId())),
                input.nome(),
                input.dataInicio(),
                input.ativo()
        );

        TreinoAluno saved = repository.salvar(treinoAluno);

        return new CriarTreinoAlunoOutput(

                saved.getId().getValue().toString(),
                saved.getTreinoId().getValue().toString(),
                saved.getAlunoId().getValue().toString(),
                saved.getNome(),
                saved.getDataInicio(),
                saved.isAtivo()

        );
    }

}
