package com.br.integramove.application.avaliacao;

import com.br.integramove.domain.avaliacao.Avaliacao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarAvaliacao {

    private final AvaliacaoRepository avaliacaoRepository;


    public ListarAvaliacao(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public List<ListarAvaliacaoOutput> listar(String id){

        List<Avaliacao> avaliacoes = avaliacaoRepository.listarPorAlunoId(id);

        return avaliacoes.stream()
                .map(avaliacao -> new ListarAvaliacaoOutput(
                        avaliacao.getId().getValue().toString(),
                        avaliacao.getAlunoId().getValue().toString(),
                        avaliacao.getDataAvaliacao(),
                        avaliacao.getRemadaBracoD(),
                        avaliacao.getRemadaBracoE(),
                        avaliacao.getElevacaoLatD(),
                        avaliacao.getElevacaoLatE(),
                        avaliacao.getExtensaoJoelhoD(),
                        avaliacao.getExtensaoJoelhoE(),
                        avaliacao.getFlexaoJoelhoD(),
                        avaliacao.getFlexaoJoelhoE(),
                        avaliacao.getExtensaoQuadrilD(),
                        avaliacao.getExtensaoQuadrilE()
                ))
                .toList();
    }
}
