package com.br.integramove.application.aluno.services;

import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.aluno.outputs.ListarAlunosOutput;
import com.br.integramove.application.plano.PlanoRepository;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.enums.StatusPagamento;
import com.br.integramove.domain.plano.Plano;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarAlunos {


    private final AlunoRepository alunoRepository;
    private final PlanoRepository planoRepository;

    public ListarAlunos(
            AlunoRepository alunoRepository,
            PlanoRepository planoRepository
    ) {
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
    }

    public List<ListarAlunosOutput> listar() {
        return alunoRepository.listarTodos()
                .stream()
                .map(this::toOutput)
                .toList();
    }

    private ListarAlunosOutput toOutput(Aluno aluno) {

        String planoId = null;
        String nomePlano = "Sem plano";

        if (aluno.getPlanoId() != null) {
            planoId = aluno.getPlanoId().getValue().toString();

            nomePlano = planoRepository.buscarPorId(aluno.getPlanoId())
                    .map(Plano::getNome)
                    .orElse("Plano não encontrado");
        }


        return new ListarAlunosOutput(
                aluno.getId().getValue().toString(),
                aluno.getNome(),
                planoId,
                nomePlano,
                // Temporário até integrar com Financeiro
                StatusPagamento.EM_ABERTO,
                aluno.getStatus()
        );
    }
}
