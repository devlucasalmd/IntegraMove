package com.br.integramove.infrastructure.persistence.pagamento;

import com.br.integramove.application.pagamento.PagamentoRepository;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.pagamento.Pagamento;
import com.br.integramove.domain.pagamento.PagamentoId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PagamentoRepositoryImpl implements PagamentoRepository {

    private final PagamentoJpaRepository pagamentoJpaRepository;
    private final PagamentoEntityMapper pagamentoEntityMapper;

    public PagamentoRepositoryImpl(
            PagamentoJpaRepository pagamentoJpaRepository,
            PagamentoEntityMapper pagamentoEntityMapper
    ) {
        this.pagamentoJpaRepository = pagamentoJpaRepository;
        this.pagamentoEntityMapper = pagamentoEntityMapper;
    }

    @Override
    public Pagamento salvar(Pagamento pagamento) {
        PagamentoEntity entity = pagamentoEntityMapper.toEntity(pagamento);
        PagamentoEntity salvo = pagamentoJpaRepository.save(entity);
        return pagamentoEntityMapper.toDomain(salvo);
    }

    @Override
    public Optional<Pagamento> buscarPorId(PagamentoId pagamentoId) {
        return pagamentoJpaRepository.findById(pagamentoId.getValue())
                .map(pagamentoEntityMapper::toDomain);
    }

    @Override
    public Optional<Pagamento> buscarPorAlunoIdEPagamentoId(AlunoId alunoId, PagamentoId pagamentoId) {
        return pagamentoJpaRepository
                .findByAluno_IdAndId(alunoId.getValue(), pagamentoId.getValue())
                .map(pagamentoEntityMapper::toDomain);
    }

    @Override
    public List<Pagamento> listarPorAlunoId(AlunoId alunoId) {
        return pagamentoJpaRepository.findByAluno_Id(alunoId.getValue())
                .stream()
                .map(pagamentoEntityMapper::toDomain)
                .toList();
    }
}