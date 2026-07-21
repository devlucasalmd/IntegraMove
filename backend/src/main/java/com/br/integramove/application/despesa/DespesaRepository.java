package com.br.integramove.application.despesa;

import com.br.integramove.domain.despesa.Despesa;
import com.br.integramove.domain.despesa.DespesaId;

import java.util.List;
import java.util.Optional;

public interface DespesaRepository {

    Despesa salvar(Despesa despesa);

    Optional<Despesa> buscarPorId(DespesaId despesaId);

    List<Despesa> listar();
}
