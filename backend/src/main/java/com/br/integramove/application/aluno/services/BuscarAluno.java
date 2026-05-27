package com.br.integramove.application.aluno.services;

import com.br.integramove.api.exception.aluno.AlunoNaoEncontradoException;
import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.aluno.outputs.BuscarAlunoOutput;
import com.br.integramove.application.aluno.outputs.EnderecoOutput;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.aluno.Endereco;
import org.springframework.stereotype.Service;

@Service
public class BuscarAluno {

    private final AlunoRepository repository;

    public BuscarAluno(AlunoRepository repository) {
        this.repository = repository;
    }

    public BuscarAlunoOutput buscar(String alunoId){

        AlunoId id = AlunoId.from(alunoId);
        Aluno aluno = repository.buscarPorId(id).orElseThrow(() -> new AlunoNaoEncontradoException(id));

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
                null
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
