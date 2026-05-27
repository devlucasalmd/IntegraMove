package com.br.integramove.application.aluno.services;

import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.plano.PlanoRepository;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.plano.Plano;
import com.br.integramove.domain.plano.PlanoId;
import org.springframework.stereotype.Service;

@Service
public class VincularPlanoAoAluno {

    private final AlunoRepository alunoRepository;
    private final PlanoRepository planoRepository;

    public VincularPlanoAoAluno(
            AlunoRepository alunoRepository,
            PlanoRepository planoRepository
    ) {
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
    }

    public void executar(String alunoId, String planoId){
        Aluno aluno = alunoRepository.buscarPorId(AlunoId.from(alunoId))
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Plano plano = planoRepository.buscarPorId(PlanoId.from(planoId))
                .orElseThrow(()-> new RuntimeException("Plano não encontrado"));

        if (!plano.estaAtivo()) {
            throw new RuntimeException("Plano está inativo");
        }

        aluno.vincularPlano(plano.getId());

        alunoRepository.salvar(aluno);

    }
}
