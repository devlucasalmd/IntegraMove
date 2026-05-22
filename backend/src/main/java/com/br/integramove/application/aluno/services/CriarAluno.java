package com.br.integramove.application.aluno.services;

import com.br.integramove.api.exception.aluno.CpfJaCadastradoException;
import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.aluno.inputs.CriarAlunoInput;
import com.br.integramove.application.aluno.inputs.EnderecoInput;
import com.br.integramove.application.aluno.outputs.CriarAlunoOutput;
import com.br.integramove.application.aluno.outputs.EnderecoOutput;
import com.br.integramove.domain.aluno.*;
import org.springframework.stereotype.Service;

@Service
public class CriarAluno {

    private final AlunoRepository repository;

    public CriarAluno(AlunoRepository repository) {
        this.repository = repository;
    }

    public CriarAlunoOutput criar(CriarAlunoInput input) {
        Aluno aluno = new Aluno(
                AlunoId.novo(),
                input.nome(),
                input.dataNascimento(),
                Cpf.of(input.cpf().toString()),
                input.genero(),
                input.telefone(),
                Email.of(input.email().toString()),
                toEndereco(input.endereco()),
                input.status()
        );

        Aluno alunoSalvo = repository.salvar(aluno);

        return new CriarAlunoOutput(
                alunoSalvo.getId().getValue().toString(),
                alunoSalvo.getNome(),
                alunoSalvo.getDataNascimento(),
                alunoSalvo.getCpf().getValue(),
                alunoSalvo.getGenero(),
                alunoSalvo.getTelefone(),
                alunoSalvo.getEmail().getValue(),
                alunoSalvo.getStatus(),
                toEnderecoOutput(alunoSalvo.getEndereco())
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
