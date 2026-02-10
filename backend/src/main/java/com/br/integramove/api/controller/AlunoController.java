package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.AlunoRequestDTO;
import com.br.integramove.api.mapper.AlunoMapper;
import com.br.integramove.application.aluno.AtualizarAluno;
import com.br.integramove.application.aluno.BuscarAluno;
import com.br.integramove.application.aluno.CriarAluno;
import com.br.integramove.application.aluno.DesativarAluno;
import com.br.integramove.application.aluno.CriarAlunoInput;
import com.br.integramove.application.aluno.CriarAlunoOutput;
import com.br.integramove.application.aluno.BuscarAlunoOutput;
import com.br.integramove.application.aluno.DesativarAlunoInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final CriarAluno criarAluno;
    private final BuscarAluno buscarAluno;
    private final AtualizarAluno atualizarAluno;
    private final DesativarAluno desativarAluno;

    public AlunoController(
            CriarAluno criarAluno,
            BuscarAluno buscarAluno,
            AtualizarAluno atualizarAluno,
            DesativarAluno desativarAluno
    ) {
        this.criarAluno = criarAluno;
        this.buscarAluno = buscarAluno;
        this.atualizarAluno = atualizarAluno;
        this.desativarAluno = desativarAluno;
    }

    @GetMapping
    public String teste() {
        return "API de Alunos no ar";
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> desativar(@PathVariable String id){
        desativarAluno.desativar(new DesativarAlunoInput(id));
        return ResponseEntity.noContent().build();
    }
}