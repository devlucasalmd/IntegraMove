package com.br.integramove.application.treino.aluno.services;

import com.br.integramove.application.treino.aluno.TreinoAlunoRepository;
import com.br.integramove.application.treino.aluno.inputs.CriarTreinoAlunoInput;
import com.br.integramove.application.treino.aluno.outputs.CriarTreinoAlunoOutput;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.treino.aluno.TreinoAluno;
import com.br.integramove.domain.treino.aluno.TreinoAlunoId;
import com.br.integramove.domain.treino.treino.TreinoId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CriarTreinoAluno {

    private final TreinoAlunoRepository treinoAlunoRepository;

    public CriarTreinoAluno(TreinoAlunoRepository treinoAlunoRepository) {
        this.treinoAlunoRepository = treinoAlunoRepository;
    }

    public CriarTreinoAlunoOutput criar(CriarTreinoAlunoInput input){

        System.out.println("USECASE input.alunoId: " + input.alunoId());
        System.out.println("USECASE input.treinoId: " + input.treinoId());

        TreinoAluno treinoAluno = new TreinoAluno(
                TreinoAlunoId.novo(),
                AlunoId.of(UUID.fromString(input.alunoId())),
                TreinoId.of(UUID.fromString(input.treinoId())),
                input.nome(),
                input.dataInicio(),
                input.ativo()
        );

        TreinoAluno saved = treinoAlunoRepository.salvar(treinoAluno);

        return new CriarTreinoAlunoOutput(

                saved.getId().getValue().toString(),
                saved.getAlunoId().getValue().toString(),
                saved.getTreinoId().getValue().toString(),
                saved.getNome(),
                saved.getDataInicio(),
                saved.isAtivo()

        );
    }

}
