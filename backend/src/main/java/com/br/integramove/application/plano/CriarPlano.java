package com.br.integramove.application.plano;

import com.br.integramove.domain.plano.Plano;
import com.br.integramove.domain.plano.PlanoId;
import org.springframework.stereotype.Service;

@Service
public class CriarPlano {

    private final PlanoRepository planoRepository;

    public CriarPlano(PlanoRepository planoRepository) { this.planoRepository = planoRepository; }

    public CriarPlanoOutput criar( CriarPlanoInput input){

        PlanoId id = PlanoId.novo();

        Plano plano = new Plano(
                id,
                input.nome(),
                input.valor(),
                input.descricao(),
                input.ativo()
        );

        Plano saved = planoRepository.salvar(plano);

        return new CriarPlanoOutput(
                saved.getId().getValue().toString(),
                saved.getNome(),
                saved.getValor(),
                saved.getDescricao(),
                saved.getAtivo()
                );
    }

}
