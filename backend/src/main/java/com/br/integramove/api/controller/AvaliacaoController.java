package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.AvaliacaoRequestDTO;
import com.br.integramove.api.mapper.AvaliacaoMapper;
import com.br.integramove.application.avaliacao.*;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final CriarAvaliacao criarAvaliacao;
    private final BuscarAvaliacao buscarAvaliacao;


    public AvaliacaoController(CriarAvaliacao criarAvaliacao, BuscarAvaliacao buscarAvaliacao) {
        this.criarAvaliacao = criarAvaliacao;
        this.buscarAvaliacao = buscarAvaliacao;
   }

   @PostMapping
   public ResponseEntity<?> criar(@RequestBody AvaliacaoRequestDTO dto){
       CriarAvaliacaoInput input = AvaliacaoMapper.toInput(dto);
       CriarAvaliacaoOutput output = criarAvaliacao.criar(input);
       return ResponseEntity.status(201).body(output);
   }

   @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable String id){
        BuscarAvaliacaoOutput output = buscarAvaliacao.buscar(id);
        return ResponseEntity.ok(AvaliacaoMapper.toResponse(output));
   }
}
