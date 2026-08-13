package com.br.integramove.infrastructure.persistence.venda;

import com.br.integramove.application.venda.VendaRepository;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.venda.Venda;
import com.br.integramove.domain.venda.VendaId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VendaRepositoryImpl implements VendaRepository {

    private final VendaJpaRepository vendaJpaRepository;
    private final VendaEntityMapper vendaEntityMapper;

    public VendaRepositoryImpl(VendaJpaRepository vendaJpaRepository, VendaEntityMapper vendaEntityMapper) {
        this.vendaJpaRepository = vendaJpaRepository;
        this.vendaEntityMapper = vendaEntityMapper;
    }

    @Override
    public Venda salvar(Venda venda) {
        VendaEntity entity = vendaEntityMapper.toEntity(venda);
        VendaEntity salvo = vendaJpaRepository.save(entity);
        return vendaEntityMapper.toDomain(salvo);
    }

    @Override
    public Optional<Venda> buscarPorId(VendaId id) {
        return vendaJpaRepository.findById(id.getValue())
                .map(vendaEntityMapper::toDomain);
    }

    @Override
    public List<Venda> listarPorAlunoId(AlunoId alunoId) {
        return vendaJpaRepository.findByAluno_Id(alunoId.getValue())
                .stream().map(vendaEntityMapper::toDomain).toList();
    }
}