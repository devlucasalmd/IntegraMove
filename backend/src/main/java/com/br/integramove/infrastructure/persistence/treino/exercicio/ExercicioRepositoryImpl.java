package com.br.integramove.infrastructure.persistence.treino.exercicio;

import com.br.integramove.domain.treino.exercicio.Exercicio;
import com.br.integramove.domain.treino.exercicio.ExercicioId;
import com.br.integramove.application.treino.exercicio.ExercicioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExercicioRepositoryImpl implements ExercicioRepository {

    private final ExercicioJpaRepository jpa;

    public ExercicioRepositoryImpl(ExercicioJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Exercicio salvar(Exercicio exercicio){
        ExercicioEntity entity = ExercicioEntityMapper.toEntity(exercicio);
        ExercicioEntity saved = jpa.save(entity);

        return ExercicioEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Exercicio> buscarPorId(ExercicioId id){
        return jpa.findById(id.getValue())
                .map(ExercicioEntityMapper::toDomain);
    }

    @Override
    public List<Exercicio> listarTodos(){
        return jpa.findAll()
                .stream()
                .map(ExercicioEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void deletarFisico(ExercicioId id) {
        jpa.deleteById(id.getValue());
    }
}
