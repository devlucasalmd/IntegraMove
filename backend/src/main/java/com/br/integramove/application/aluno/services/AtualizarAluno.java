package com.br.integramove.application.aluno.services;

import com.br.integramove.api.dto.request.EnderecoRequestDTO;
import com.br.integramove.api.exception.aluno.AlunoNaoEncontradoException;
import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.aluno.inputs.AtualizarAlunoInput;
import com.br.integramove.application.aluno.inputs.EnderecoInput;
import com.br.integramove.application.aluno.outputs.AtualizarAlunoOutput;
import com.br.integramove.application.aluno.outputs.EnderecoOutput;
import com.br.integramove.domain.aluno.*;
import org.springframework.stereotype.Service;

@Service
public class AtualizarAluno {

    private final AlunoRepository alunoRepository;

    public AtualizarAluno(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AtualizarAlunoOutput atualizar(AtualizarAlunoInput input) {

        AlunoId id = AlunoId.from(input.id());

        Aluno aluno = alunoRepository.buscarPorId(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException(id));


        aluno.atualizarDados(
                input.nome(),
                input.dataNascimento(),
                input.genero(),
                input.telefone(),
                Email.of(input.email().toString()),
                toEndereco(input.endereco()),
                input.status()
        );

        Aluno alunoAtualizado = alunoRepository.salvar(aluno);

        return new AtualizarAlunoOutput(
                alunoAtualizado.getId().getValue().toString(),
                alunoAtualizado.getNome(),
                alunoAtualizado.getDataNascimento(),
                alunoAtualizado.getCpf().getValue(),
                alunoAtualizado.getGenero(),
                alunoAtualizado.getTelefone(),
                alunoAtualizado.getEmail().getValue(),
                alunoAtualizado.getStatus(),
                toEnderecoOutput(alunoAtualizado.getEndereco())
        );
    }

    private Endereco toEndereco(EnderecoInput input) {
        if (input == null) {
            return null;
        }

        return new Endereco(
                input.cep(),
                input.estado(),
                input.cidade(),
                input.rua(),
                input.numero(),
                input.bairro()
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
