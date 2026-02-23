package com.br.integramove.infrastructure.persistence.aluno;

import com.br.integramove.application.aluno.AlunoRepository;
import com.br.integramove.domain.aluno.Aluno;
import com.br.integramove.domain.aluno.AlunoId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlunoRepositoryImpl implements AlunoRepository {

    private final AlunoJpaRepository jpa;

    public AlunoRepositoryImpl(AlunoJpaRepository jpa){
        this.jpa = jpa;
    }

    @Override
    public void salvar(Aluno aluno){
        AlunoEntity entity = AlunoEntityMapper.toEntity(aluno);
        jpa.save(entity);
    }

    @Override
    public Optional<Aluno> buscarPorId(AlunoId id){
        return jpa.findById(id.getValue())
                .map(AlunoEntityMapper::toDomain);
    }

    @Override
    public List<Aluno> buscarTodosAlunos() {
        return jpa.findAll()
                .stream()
                .map(AlunoEntityMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existePorCpf(String cpf) {
        return jpa.existsByCpf(cpf);
    }
}