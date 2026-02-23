package com.br.integramove.application.aluno;

import com.br.integramove.api.exception.aluno.AlunoNaoEncontradoException;
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

        Email email = new Email(input.email());

        Endereco endereco = new Endereco(
                input.cep(),
                input.estado(),
                input.cidade(),
                input.rua(),
                input.numero(),
                input.bairro()
        );

        aluno.atualizarDados(
                input.nome(),
                input.dataNascimento(),
                Genero.valueOf(input.genero()),
                input.telefone(),
                email,
                endereco,
                input.ativo()
        );

        alunoRepository.salvar(aluno);

        return new AtualizarAlunoOutput(id.getValue().toString());
    }

}
