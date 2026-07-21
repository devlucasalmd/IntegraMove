package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.AvaliacaoRequestDTO;
import com.br.integramove.api.dto.response.AvaliacaoResponseDTO;
import com.br.integramove.api.mapper.AvaliacaoMapper;

import com.br.integramove.application.avaliacao.inputs.CriarAvaliacaoInput;
import com.br.integramove.application.avaliacao.outputs.BuscarAvaliacaoOutput;
import com.br.integramove.application.avaliacao.outputs.CriarAvaliacaoOutput;
import com.br.integramove.application.avaliacao.outputs.ListarAvaliacaoOutput;
import com.br.integramove.application.avaliacao.services.BuscarAvaliacao;
import com.br.integramove.application.avaliacao.services.CriarAvaliacao;
import com.br.integramove.application.avaliacao.services.ListarAvaliacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/alunos/{alunoId}/avaliacoes")
public class AvaliacaoController {

    private final CriarAvaliacao criarAvaliacao;
    private final BuscarAvaliacao buscarAvaliacao;
    private final ListarAvaliacao listarAvaliacao;


    public AvaliacaoController(CriarAvaliacao criarAvaliacao, BuscarAvaliacao buscarAvaliacao, ListarAvaliacao listarAvaliacao) {
        this.criarAvaliacao = criarAvaliacao;
        this.buscarAvaliacao = buscarAvaliacao;
        this.listarAvaliacao = listarAvaliacao;
   }

   @PostMapping
   public ResponseEntity<AvaliacaoResponseDTO> criar(
           @PathVariable String alunoId,
           @RequestBody AvaliacaoRequestDTO dto
   ){
       CriarAvaliacaoInput input = AvaliacaoMapper.toInput(alunoId, dto);
       CriarAvaliacaoOutput output = criarAvaliacao.criar(input);

       AvaliacaoResponseDTO response = AvaliacaoMapper.toResponse(output);

       return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }

   @GetMapping("/{avaliacaoId}")
    public ResponseEntity<?> buscar(@PathVariable String alunoId, @PathVariable String avaliacaoId){
        BuscarAvaliacaoOutput output = buscarAvaliacao.buscar(alunoId, avaliacaoId);

        AvaliacaoResponseDTO response = AvaliacaoMapper.toResponse(output);

        return ResponseEntity.ok(response);
   }

    @GetMapping
    public ResponseEntity<List<AvaliacaoResponseDTO>> listar(@PathVariable String alunoId){
        List<ListarAvaliacaoOutput> outputs = listarAvaliacao.listar(alunoId);

        List<AvaliacaoResponseDTO> response = outputs.stream()
                .map(AvaliacaoMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }
}
