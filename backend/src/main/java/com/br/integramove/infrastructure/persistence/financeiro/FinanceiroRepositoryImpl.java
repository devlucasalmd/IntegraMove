package com.br.integramove.infrastructure.persistence.financeiro;

import com.br.integramove.application.financeiro.FinanceiroRepository;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.financeiro.Financeiro;
import com.br.integramove.domain.financeiro.FinanceiroId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FinanceiroRepositoryImpl implements FinanceiroRepository {

    private final FinanceiroJpaRepository financeiroJpaRepository;
    private final FinanceiroEntityMapper financeiroEntityMapper;

    public FinanceiroRepositoryImpl(FinanceiroJpaRepository financeiroJpaRepository, FinanceiroEntityMapper financeiroEntityMapper) {
        this.financeiroJpaRepository = financeiroJpaRepository;
        this.financeiroEntityMapper = financeiroEntityMapper;
    }

    @Override
    public Financeiro salvar(Financeiro financeiro) {
        FinanceiroEntity entity = financeiroEntityMapper.toEntity(financeiro);
        FinanceiroEntity salvo = financeiroJpaRepository.save(entity);
        return financeiroEntityMapper.toDomain(salvo);
    }

    @Override
    public Optional<Financeiro> buscarPorId(FinanceiroId id) {
        return financeiroJpaRepository.findById(id.getValue())
                .map(financeiroEntityMapper::toDomain);
    }

    @Override
    public List<Financeiro> listarPorAlunoId(AlunoId alunoId) {
        return financeiroJpaRepository.findByAluno_Id(alunoId.getValue())
                .stream().map(financeiroEntityMapper::toDomain).toList();
    }
}