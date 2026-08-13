package com.br.integramove.application.venda;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.venda.Venda;
import com.br.integramove.domain.venda.VendaId;

import java.util.List;
import java.util.Optional;

public interface VendaRepository {

    Venda salvar(Venda venda);

    Optional<Venda> buscarPorId(VendaId id);

    List<Venda> listarPorAlunoId(AlunoId alunoId);
}