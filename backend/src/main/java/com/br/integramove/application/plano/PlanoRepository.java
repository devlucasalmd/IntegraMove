package com.br.integramove.application.plano;

import com.br.integramove.domain.plano.Plano;
import com.br.integramove.domain.plano.PlanoId;

import java.util.List;
import java.util.Optional;

public interface PlanoRepository {

    Plano salvar(Plano plano);

    Optional<Plano> buscarPorId(PlanoId id);

    List<Plano> buscarTodosPlanos();
}
