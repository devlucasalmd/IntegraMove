package com.br.integramove.application.contaReceber;

import com.br.integramove.domain.aluno.AlunoId;
import com.br.integramove.domain.contasReceber.ContaReceber;
import com.br.integramove.domain.contasReceber.ContaReceberId;

import java.util.List;
import java.util.Optional;

public interface ContaReceberRepository {

    ContaReceber salvar(ContaReceber contaReceber);

    Optional<ContaReceber> buscarPorId(ContaReceberId contaReceberId);

    List<ContaReceber> listar();

    List<ContaReceber> listarPorAlunoId(AlunoId alunoId);

}
