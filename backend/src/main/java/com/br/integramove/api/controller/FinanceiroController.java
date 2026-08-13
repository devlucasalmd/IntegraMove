package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.RegistrarPagamentoRequestDTO;
import com.br.integramove.api.dto.response.FinanceiroResponseDTO;
import com.br.integramove.api.mapper.FinanceiroMapper;
import com.br.integramove.application.financeiro.inputs.RegistrarPagamentoInput;
import com.br.integramove.application.financeiro.outputs.FinanceiroOutput;
import com.br.integramove.application.financeiro.services.BuscarFinanceiro;
import com.br.integramove.application.financeiro.services.ListarFinanceiroPorAluno;
import com.br.integramove.application.financeiro.services.RegistrarPagamento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroController {

    private final ListarFinanceiroPorAluno listarFinanceiroPorAluno;
    private final BuscarFinanceiro buscarFinanceiro;
    private final RegistrarPagamento registrarPagamento;

    public FinanceiroController(
            ListarFinanceiroPorAluno listarFinanceiroPorAluno,
            BuscarFinanceiro buscarFinanceiro,
            RegistrarPagamento registrarPagamento
    ) {
        this.listarFinanceiroPorAluno = listarFinanceiroPorAluno;
        this.buscarFinanceiro = buscarFinanceiro;
        this.registrarPagamento = registrarPagamento;
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<FinanceiroResponseDTO>> listarPorAluno(@PathVariable String alunoId) {
        List<FinanceiroOutput> outputs = listarFinanceiroPorAluno.listar(alunoId);
        return ResponseEntity.ok(
                outputs.stream()
                        .map(FinanceiroMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinanceiroResponseDTO> buscar(@PathVariable String id) {
        FinanceiroOutput output = buscarFinanceiro.buscar(id);
        return ResponseEntity.ok(FinanceiroMapper.toResponse(output));
    }

    @PostMapping("/{id}/pagar")
    public ResponseEntity<FinanceiroResponseDTO> pagar(
            @PathVariable String id,
            @RequestBody RegistrarPagamentoRequestDTO dto
    ) {
        RegistrarPagamentoInput input = FinanceiroMapper.toInput(id, dto);
        FinanceiroOutput output = registrarPagamento.registrar(input);
        return ResponseEntity.ok(FinanceiroMapper.toResponse(output));
    }
}