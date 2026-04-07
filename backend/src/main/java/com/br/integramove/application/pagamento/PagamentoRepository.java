package com.br.integramove.application.pagamento;

import com.br.integramove.domain.pagamento.Pagamento;
import com.br.integramove.domain.pagamento.PagamentoId;

import java.util.List;
import java.util.Optional;

public interface PagamentoRepository {

    Pagamento salvar(Pagamento pagamento);

    Optional<Pagamento> buscarPorId(PagamentoId id);

    List<Pagamento> listarTodos();
}
