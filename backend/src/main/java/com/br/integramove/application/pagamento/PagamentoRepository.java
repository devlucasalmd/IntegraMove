package com.br.integramove.application.pagamento;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.pagamentoAluno.Pagamento;
import com.br.integramove.domain.pagamentoAluno.PagamentoId;
import com.br.integramove.infrastructure.persistence.pagamento.PagamentoEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PagamentoRepository {

    Pagamento salvar(Pagamento pagamento);

    Optional<Pagamento> buscarPorId(PagamentoId pagamentoId);

    Optional<Pagamento> buscarPorAlunoIdEPagamentoId( AlunoId alunoId, PagamentoId pagamentoId);

    List<Pagamento> listarPorAlunoId(AlunoId alunoId);
}