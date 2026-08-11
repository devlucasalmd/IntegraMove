package com.br.integramove.application.aluno.services;

import com.br.integramove.api.exception.aluno.CpfJaCadastradoException;
import com.br.integramove.api.exception.plano.PlanoInativoException;
import com.br.integramove.api.exception.plano.PlanoNaoEncontradoException;
import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.aluno.inputs.CriarAlunoInput;
import com.br.integramove.application.aluno.inputs.EnderecoInput;
import com.br.integramove.application.aluno.outputs.CriarAlunoOutput;
import com.br.integramove.application.aluno.outputs.EnderecoOutput;
import com.br.integramove.application.plano.PlanoRepository;
import com.br.integramove.domain.aluno.*;
import com.br.integramove.domain.plano.Plano;
import com.br.integramove.domain.plano.PlanoId;
import com.br.integramove.domain.valueobjects.Endereco;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CriarAluno {

    private final AlunoRepository repository;
    private final PlanoRepository planoRepository;
    private final GeradorSenhaTemporaria geradorSenhaTemporaria;
    private final PasswordEncoder passwordEncoder;


    public CriarAluno(
            AlunoRepository repository,
            PlanoRepository planoRepository,
            GeradorSenhaTemporaria geradorSenhaTemporaria,
            PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.planoRepository = planoRepository;
        this.geradorSenhaTemporaria = geradorSenhaTemporaria;
        this.passwordEncoder = passwordEncoder;
    }


    public CriarAlunoOutput criar(
            CriarAlunoInput input
    ) {

        if (repository.existePorCpf(input.cpf().getValue())) {
            throw new CpfJaCadastradoException();
        }

        PlanoId planoId = null;


        if (input.planoId() != null && !input.planoId().isBlank()) {

            PlanoId idPlano = PlanoId.from(input.planoId());


            Plano plano = planoRepository.buscarPorId(idPlano)
                    .orElseThrow(
                            () -> new PlanoNaoEncontradoException(idPlano)
                    );


            if (!plano.estaAtivo()) {
                throw new PlanoInativoException();
            }


            planoId = plano.getId();
        }


        Aluno aluno = new Aluno(
                AlunoId.novo(),
                input.nome(),
                input.dataNascimento(),
                input.cpf(),
                input.genero(),
                input.telefone(),
                input.email(),
                input.status(),
                planoId,
                toEndereco(input.endereco())
        );


        String senhaTemporaria = geradorSenhaTemporaria.gerar();
        aluno.definirSenha(passwordEncoder.encode(senhaTemporaria), true);

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
                alunoSalvo.getPlanoId() != null
                        ? alunoSalvo.getPlanoId().getValue().toString()
                        : null,
                toEnderecoOutput(alunoSalvo.getEndereco()),
                senhaTemporaria
        );
    }


    private Endereco toEndereco(EnderecoInput input) {

        if (input == null) {
            return null;
        }

        return Endereco.of(
                input.cep(),
                input.estado(),
                input.cidade(),
                input.rua(),
                input.numero(),
                input.bairro()
        );
    }


    private EnderecoOutput toEnderecoOutput(
            Endereco endereco
    ) {

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