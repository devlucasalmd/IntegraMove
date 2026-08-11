package com.br.integramove.infrastructure.persistence.contaReceber;

import com.br.integramove.application.contaReceber.ContaReceberRepository;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.financas.contasReceber.ContaReceber;
import com.br.integramove.domain.financas.contasReceber.ContaReceberId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContaReceberRepositoryImpl implements ContaReceberRepository {

    private final ContaReceberJpaRepository contaReceberJpaRepository;
    private final ContaReceberEntityMapper contaReceberEntityMapper;

    public ContaReceberRepositoryImpl(
            ContaReceberJpaRepository contaReceberJpaRepository,
            ContaReceberEntityMapper contaReceberEntityMapper
    ) {
        this.contaReceberJpaRepository = contaReceberJpaRepository;
        this.contaReceberEntityMapper = contaReceberEntityMapper;
    }

    @Override
    public ContaReceber salvar(ContaReceber contaReceber) {
        ContaReceberEntity entity = contaReceberEntityMapper.toEntity(contaReceber);
        ContaReceberEntity salvo = contaReceberJpaRepository.save(entity);
        return contaReceberEntityMapper.toDomain(salvo);
    }

    @Override
    public Optional<ContaReceber> buscarPorId(ContaReceberId contaReceberId) {
        return contaReceberJpaRepository.findById(contaReceberId.getValue())
                .map(contaReceberEntityMapper::toDomain);
    }

    @Override
    public List<ContaReceber> listar() {
        return contaReceberJpaRepository.findAll()
                .stream()
                .map(contaReceberEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<ContaReceber> listarPorAlunoId(AlunoId alunoId) {
        return contaReceberJpaRepository.findByAluno_Id(alunoId.getValue())
                .stream()
                .map(contaReceberEntityMapper::toDomain)
                .toList();
    }
}