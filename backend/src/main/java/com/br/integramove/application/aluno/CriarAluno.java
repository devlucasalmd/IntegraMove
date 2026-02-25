package com.br.integramove.application.aluno;

import com.br.integramove.api.exception.aluno.CpfJaCadastradoException;
import com.br.integramove.domain.aluno.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CriarAluno {

    private final AlunoRepository alunoRepository;

    public CriarAluno(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    public CriarAlunoOutput criar( CriarAlunoInput alunoInput){

        if(alunoRepository.existePorCpf(alunoInput.cpf())){
            throw new CpfJaCadastradoException();
        }

        AlunoId id = AlunoId.novo();
        Cpf cpf = new Cpf(alunoInput.cpf());
        Email email = new Email(alunoInput.email());

        Endereco endereco = new Endereco(
                alunoInput.cep(),
                alunoInput.estado(),
                alunoInput.cidade(),
                alunoInput.rua(),
                alunoInput.numero(),
                alunoInput.bairro()
        );

        Aluno aluno = new Aluno(
                id,
                alunoInput.nome(),
                alunoInput.dataNascimento(),
                cpf,
                Genero.OUTRO,
                alunoInput.telefone(),
                email,
                endereco,
                alunoInput.ativo()
        );

        alunoRepository.salvar(aluno);

        return new CriarAlunoOutput(id.getValue().toString());
    }


}
