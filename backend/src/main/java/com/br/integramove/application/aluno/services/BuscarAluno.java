package com.br.integramove.application.aluno.services;

import com.br.integramove.api.exception.aluno.AlunoNaoEncontradoException;
import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.aluno.outputs.BuscarAlunoOutput;
import com.br.integramove.application.aluno.outputs.EnderecoOutput;
import com.br.integramove.application.plano.PlanoRepository;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.valueobjects.Endereco;
import com.br.integramove.domain.plano.Plano;
import org.springframework.stereotype.Service;

@Service
public class BuscarAluno {

    private final AlunoRepository alunoRepository;
    private final PlanoRepository planoRepository;

    public BuscarAluno(AlunoRepository alunoRepository, PlanoRepository planoRepository) {
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
    }

    public BuscarAlunoOutput buscar(String alunoId){

        AlunoId id = AlunoId.from(alunoId);
        Aluno aluno = alunoRepository.buscarPorId(id).orElseThrow(() -> new AlunoNaoEncontradoException(id));

        String planoId = null;
        String nomePlano = null;

        if (aluno.getPlanoId() != null) {
            planoId = aluno.getPlanoId().getValue().toString();

            nomePlano = planoRepository.buscarPorId(aluno.getPlanoId())
                    .map(Plano::getNome)
                    .orElse(null);
        }

        return new BuscarAlunoOutput(
                aluno.getId().getValue().toString(),
                aluno.getNome(),
                aluno.getDataNascimento(),
                aluno.getCpf().getValue(),
                aluno.getGenero(),
                aluno.getTelefone(),
                aluno.getEmail().getValue(),
                aluno.getStatus(),
                toEnderecoOutput(aluno.getEndereco()),
                aluno.getPlanoId().getValue().toString(),
                nomePlano
        );
    }

    private EnderecoOutput toEnderecoOutput(Endereco endereco) {
        if (endereco == null) {
            return null;
        }

        return new EnderecoOutput(
                endereco.getCep(),
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro()
        );
    }
}
