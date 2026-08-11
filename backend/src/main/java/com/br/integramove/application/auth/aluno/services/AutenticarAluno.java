package com.br.integramove.application.auth.aluno.services;

import com.br.integramove.api.exception.aluno.CpfInvalidoException;
import com.br.integramove.api.exception.auth.CredenciaisInvalidasException;
import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.auth.aluno.inputs.AutenticarAlunoInput;
import com.br.integramove.application.auth.aluno.outputs.AutenticarAlunoOutput;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.valueobjects.Cpf;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class
AutenticarAluno {

    private final AlunoRepository alunoRepository;
    private final PasswordEncoder passwordEncoder;

    public AutenticarAluno(
            AlunoRepository alunoRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.alunoRepository = alunoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AutenticarAlunoOutput autenticar(AutenticarAlunoInput input) {

        String cpf;
        try {
            cpf = Cpf.of(input.cpf()).getValue();
        } catch (CpfInvalidoException ex) {
            throw new CredenciaisInvalidasException();
        }

        Aluno aluno = alunoRepository.buscarPorCpf(cpf)
                .orElseThrow(CredenciaisInvalidasException::new);

        if (aluno.getSenhaHash() == null
                || !passwordEncoder.matches(input.senha(), aluno.getSenhaHash())) {
            throw new CredenciaisInvalidasException();
        }

        return new AutenticarAlunoOutput(
                aluno.getId().getValue().toString(),
                aluno.getNome(),
                aluno.isSenhaTemporaria()
        );
    }
}