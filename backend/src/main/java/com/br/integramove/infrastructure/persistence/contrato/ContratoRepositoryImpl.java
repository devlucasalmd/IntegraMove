package com.br.integramove.infrastructure.persistence.contrato;

import com.br.integramove.application.contrato.ContratoRepository;
import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.contrato.Contrato;
import com.br.integramove.domain.contrato.ContratoId;
import com.br.integramove.domain.enums.StatusContrato;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class ContratoRepositoryImpl implements ContratoRepository {

    private final ContratoJpaRepository contratoJpaRepository;
    private final ContratoEntityMapper contratoEntityMapper;

    public ContratoRepositoryImpl(ContratoJpaRepository contratoJpaRepository, ContratoEntityMapper contratoEntityMapper) {
        this.contratoJpaRepository = contratoJpaRepository;
        this.contratoEntityMapper = contratoEntityMapper;
    }

    @Override
    public Contrato salvar(Contrato contrato) {
        ContratoEntity entity = contratoEntityMapper.toEntity(contrato);
        ContratoEntity salvo = contratoJpaRepository.save(entity);
        return contratoEntityMapper.toDomain(salvo);
    }

    @Override
    public Optional<Contrato> buscarPorId(ContratoId id) {
        return contratoJpaRepository.findById(id.getValue())
                .map(contratoEntityMapper::toDomain);
    }

    @Override
    public Optional<Contrato> buscarPorAlunoIdEStatus(AlunoId alunoId, StatusContrato status) {
        return contratoJpaRepository.findByAluno_IdAndStatus(alunoId.getValue(), status)
                .map(contratoEntityMapper::toDomain);
    }

    @Override
    public List<Contrato> listarPorAlunoId(AlunoId alunoId) {
        return contratoJpaRepository.findByAluno_Id(alunoId.getValue())
                .stream().map(contratoEntityMapper::toDomain).toList();
    }

    @Override
    public List<Contrato> listarVencidosSemRenovacaoAutomatica(StatusContrato status, LocalDate dataReferencia) {
        return contratoJpaRepository.buscarVencidosSemRenovacaoAutomatica(status, dataReferencia)
                .stream().map(contratoEntityMapper::toDomain).toList();
    }
}