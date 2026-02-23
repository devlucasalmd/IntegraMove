package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.AlunoRequestDTO;
import com.br.integramove.api.mapper.AlunoMapper;
import com.br.integramove.application.aluno.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final CriarAluno criarAluno;
    private final BuscarAluno buscarAluno;
    private final ListarAlunos listarAlunos;
    private final AtualizarAluno atualizarAluno;
    private final DesativarAluno desativarAluno;

    public AlunoController(
            CriarAluno criarAluno,
            BuscarAluno buscarAluno,
            ListarAlunos listarAlunos,
            AtualizarAluno atualizarAluno,
            DesativarAluno desativarAluno
    ) {
        this.criarAluno = criarAluno;
        this.buscarAluno = buscarAluno;
        this.listarAlunos = listarAlunos;
        this.atualizarAluno = atualizarAluno;
        this.desativarAluno = desativarAluno;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody AlunoRequestDTO dto){
        CriarAlunoInput input = AlunoMapper.toInput(dto);
        CriarAlunoOutput output = criarAluno.criar(input);
        return ResponseEntity.status(201).body(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable String id){
        BuscarAlunoOutput output = buscarAluno.buscar(id);
        return ResponseEntity.ok(AlunoMapper.toResponse(output));
    }

    @GetMapping
    public ResponseEntity<List<ListarAlunosOutput>> listar() {
        return ResponseEntity.ok(listarAlunos.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody AlunoRequestDTO dto
    ) {
        AtualizarAlunoInput input = AlunoMapper.toAtualizar(id, dto);
        AtualizarAlunoOutput output = atualizarAluno.atualizar(input);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> desativar(@PathVariable String id){
        desativarAluno.desativar(new DesativarAlunoInput(id));
        return ResponseEntity.noContent().build();
    }
}