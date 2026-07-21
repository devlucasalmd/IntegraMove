package com.br.integramove.infrastructure.persistence.despesa;

import com.br.integramove.application.despesa.DespesaRepository;
import com.br.integramove.domain.despesa.Despesa;
import com.br.integramove.domain.despesa.DespesaId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DespesaRepositoryImpl implements DespesaRepository {

    private final DespesaJpaRepository despesaJpaRepository;
    private final DespesaEntityMapper despesaEntityMapper;

    public DespesaRepositoryImpl(
            DespesaJpaRepository despesaJpaRepository,
            DespesaEntityMapper despesaEntityMapper
    ) {
        this.despesaJpaRepository = despesaJpaRepository;
        this.despesaEntityMapper = despesaEntityMapper;
    }

    @Override
    public Despesa salvar(Despesa despesa) {
        DespesaEntity entity = despesaEntityMapper.toEntity(despesa);
        DespesaEntity salvo = despesaJpaRepository.save(entity);
        return despesaEntityMapper.toDomain(salvo);
    }

    @Override
    public Optional<Despesa> buscarPorId(DespesaId despesaId) {
        return despesaJpaRepository.findById(despesaId.getValue())
                .map(despesaEntityMapper::toDomain);
    }

    @Override
    public List<Despesa> listar() {
        return despesaJpaRepository.findAll()
                .stream()
                .map(despesaEntityMapper::toDomain)
                .toList();
    }
}
