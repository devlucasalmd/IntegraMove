    package com.br.integramove.api.mapper;

    import com.br.integramove.api.dto.request.AvaliacaoRequestDTO;
    import com.br.integramove.api.dto.response.AvaliacaoResponseDTO;
    import com.br.integramove.application.avaliacao.BuscarAvaliacaoOutput;
    import com.br.integramove.application.avaliacao.CriarAvaliacaoInput;
    import com.br.integramove.application.avaliacao.CriarAvaliacaoOutput;
    import com.br.integramove.application.avaliacao.ListarAvaliacaoOutput;
    import org.springframework.stereotype.Component;

    @Component
    public class AvaliacaoMapper {

        public static CriarAvaliacaoInput toInput(String alunoId, AvaliacaoRequestDTO dto) {
            return new CriarAvaliacaoInput(
                    alunoId,
                    dto.dataAvaliacao(),
                    dto.remadaBracoD(),
                    dto.remadaBracoE(),
                    dto.elevacaoLatD(),
                    dto.elevacaoLatE(),
                    dto.extensaoJoelhoD(),
                    dto.extensaoJoelhoE(),
                    dto.flexaoJoelhoD(),
                    dto.flexaoJoelhoE(),
                    dto.extensaoQuadrilD(),
                    dto.extensaoQuadrilE()
            );
        }

        public static AvaliacaoResponseDTO toResponse(CriarAvaliacaoOutput output){
            return new AvaliacaoResponseDTO(
                    output.id(),
                    output.alunoId(),
                    output.dataAvaliacao(),
                    output.remadaBracoD(),
                    output.remadaBracoE(),
                    output.elevacaoLatD(),
                    output.elevacaoLatE(),
                    output.extensaoJoelhoD(),
                    output.extensaoJoelhoE(),
                    output.flexaoJoelhoD(),
                    output.flexaoJoelhoE(),
                    output.extensaoQuadrilD(),
                    output.extensaoQuadrilE()
            );
        }

        public static AvaliacaoResponseDTO toResponse(BuscarAvaliacaoOutput output){
            return new AvaliacaoResponseDTO(
                    output.id(),
                    output.alunoId(),
                    output.dataAvaliacao(),
                    output.remadaBracoD(),
                    output.remadaBracoE(),
                    output.elevacaoLatD(),
                    output.elevacaoLatE(),
                    output.extensaoJoelhoD(),
                    output.extensaoJoelhoE(),
                    output.flexaoJoelhoD(),
                    output.flexaoJoelhoE(),
                    output.extensaoQuadrilD(),
                    output.extensaoQuadrilE()
            );
        }

        public static AvaliacaoResponseDTO toResponse(ListarAvaliacaoOutput output){
            return new AvaliacaoResponseDTO(
                    output.id(),
                    output.alunoId(),
                    output.dataAvaliacao(),
                    output.remadaBracoD(),
                    output.remadaBracoE(),
                    output.elevacaoLatD(),
                    output.elevacaoLatE(),
                    output.extensaoJoelhoD(),
                    output.extensaoJoelhoE(),
                    output.flexaoJoelhoD(),
                    output.flexaoJoelhoE(),
                    output.extensaoQuadrilD(),
                    output.extensaoQuadrilE()
            );
        }

    }

