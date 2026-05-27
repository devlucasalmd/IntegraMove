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

    private final PagamentoJpaRepository jpa;

    public PagamentoRepositoryImpl(PagamentoJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Pagamento salvar(Pagamento pagamento){
        PagamentoEntity entity = PagamentoEntityMapper.toEntity(pagamento);
        PagamentoEntity saved = jpa.save(entity);

        return PagamentoEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Pagamento> buscarPorId(PagamentoId id){
        return jpa.findById(id.getValue())
                .map(PagamentoEntityMapper::toDomain);
    }

    @Override
    public List<Pagamento> listarTodos(){
        return jpa.findAll()
                .stream()
                .map(PagamentoEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Pagamento> listarPorAlunoId(AlunoId alunoId) {
        return jpa.findByAluno_Id(alunoId.getValue())
                .stream()
                .map(PagamentoEntityMapper::toDomain)
                .toList();
    }

}
