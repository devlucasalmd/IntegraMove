package com.br.integramove.application.auth.aluno.services;

import com.br.integramove.api.exception.aluno.AlunoNaoEncontradoException;
import com.br.integramove.api.exception.auth.CredenciaisInvalidasException;
import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.application.auth.aluno.inputs.TrocarSenhaAlunoInput;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.AlunoId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TrocarSenhaAluno {

    private final AlunoRepository alunoRepository;
    private final PasswordEncoder passwordEncoder;

    public TrocarSenhaAluno(
            AlunoRepository alunoRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.alunoRepository = alunoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void trocar(TrocarSenhaAlunoInput input) {

        AlunoId id = AlunoId.from(input.alunoId());

        Aluno aluno = alunoRepository.buscarPorId(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException(id));

        if (aluno.getSenhaHash() == null
                || !passwordEncoder.matches(input.senhaAtual(), aluno.getSenhaHash())) {
            throw new CredenciaisInvalidasException();
        }

        aluno.trocarSenha(passwordEncoder.encode(input.novaSenha()));

        alunoRepository.salvar(aluno);
    }
}