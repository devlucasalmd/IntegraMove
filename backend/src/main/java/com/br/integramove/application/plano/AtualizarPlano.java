package com.br.integramove.application.plano;

import com.br.integramove.api.exception.plano.PlanoNaoEncontradoException;
import com.br.integramove.domain.plano.Plano;
import com.br.integramove.domain.plano.PlanoId;
import org.springframework.stereotype.Service;

@Service
public class AtualizarPlano {

    private final PlanoRepository planoRepository;


    public AtualizarPlano(PlanoRepository planoRepository) { this.planoRepository = planoRepository; }

    public AtualizarPlanoOutput atualizar(AtualizarPlanoInput input){

        PlanoId id = PlanoId.from(input.id());

        Plano plano = planoRepository.buscarPorId(id)
                .orElseThrow(() -> new PlanoNaoEncontradoException(id));

        plano.atualizarDados(
                input.nome(),
                input.valor(),
                input.descricao(),
                input.ativo()
        );

        Plano atualizado =  planoRepository.salvar(plano);

        return new AtualizarPlanoOutput(
                atualizado.getId().getValue().toString(),
                atualizado.getNome(),
                atualizado.getValor(),
                atualizado.getDescricao(),
                atualizado.getAtivo()
                );
    }

}
