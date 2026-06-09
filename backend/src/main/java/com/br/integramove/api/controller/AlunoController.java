package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.AlunoRequestDTO;
import com.br.integramove.api.dto.response.AlunoResponseDTO;
import com.br.integramove.api.dto.response.AlunoResumoResponseDTO;
import com.br.integramove.api.dto.response.FinanceiroAlunoResponseDTO;
import com.br.integramove.api.dto.response.PagamentoResponseDTO;
import com.br.integramove.api.mapper.AlunoMapper;
import com.br.integramove.api.mapper.FinanceiroAlunoMapper;
import com.br.integramove.api.mapper.PagamentoMapper;
import com.br.integramove.application.aluno.inputs.AtualizarAlunoInput;
import com.br.integramove.application.aluno.inputs.CriarAlunoInput;
import com.br.integramove.application.aluno.inputs.DesativarAlunoInput;
import com.br.integramove.application.aluno.outputs.*;
import com.br.integramove.application.aluno.services.*;
import com.br.integramove.application.pagamento.services.ListarPagamentosPorAluno;
import org.springframework.http.HttpStatus;
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
    private final VincularPlanoAoAluno vincularPlano;
    private final ListarPagamentosPorAluno listarPagamentosPorAluno;
    private final BuscarFinanceiroAluno buscarFinanceiroAluno;

    public AlunoController(
            CriarAluno criarAluno,
            BuscarAluno buscarAluno,
            ListarAlunos listarAlunos,
            AtualizarAluno atualizarAluno,
            DesativarAluno desativarAluno,
            VincularPlanoAoAluno vincularPlano,
            ListarPagamentosPorAluno listarPagamentosPorAluno,
            BuscarFinanceiroAluno buscarFinanceiroAluno
            ) {
        this.criarAluno = criarAluno;
        this.buscarAluno = buscarAluno;
        this.listarAlunos = listarAlunos;
        this.atualizarAluno = atualizarAluno;
        this.desativarAluno = desativarAluno;
        this.vincularPlano = vincularPlano;
        this.listarPagamentosPorAluno = listarPagamentosPorAluno;
        this.buscarFinanceiroAluno = buscarFinanceiroAluno;
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criar(@RequestBody AlunoRequestDTO request){
        CriarAlunoInput input = AlunoMapper.toInput(request);
        CriarAlunoOutput output = criarAluno.criar(input);
        return ResponseEntity .status(HttpStatus.CREATED)
                .body(AlunoMapper.toResponse(output));
    }

    @GetMapping
    public ResponseEntity<List<AlunoResumoResponseDTO>> listar() {

        List<ListarAlunosOutput> output = listarAlunos.listar();

        List<AlunoResumoResponseDTO> response = output.stream()
                .map(AlunoMapper::toResumoResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> buscar(@PathVariable String id){
        BuscarAlunoOutput output = buscarAluno.buscar(id);
        return ResponseEntity.ok(AlunoMapper.toResponse(output));
    }


    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizar(@PathVariable String id, @RequestBody AlunoRequestDTO request) {
        AtualizarAlunoInput input = AlunoMapper.toAtualizar(id, request);
        AtualizarAlunoOutput output = atualizarAluno.atualizar(input);
        return ResponseEntity.ok(AlunoMapper.toResponse(output));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> desativar(@PathVariable String id){
        desativarAluno.desativar(new DesativarAlunoInput(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{alunoId}/plano/{planoId}")
    public ResponseEntity<Void> vincularPlano(@PathVariable String alunoId, @PathVariable String planoId){
        vincularPlano.executar(alunoId, planoId);
        return ResponseEntity.noContent().build();
    }


}