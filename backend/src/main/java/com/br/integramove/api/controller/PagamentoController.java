package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.PagamentoRequestDTO;
import com.br.integramove.api.dto.request.PagarPagamentoRequestDTO;
import com.br.integramove.api.dto.response.PagamentoResponseDTO;
import com.br.integramove.api.mapper.PagamentoMapper;
import com.br.integramove.application.pagamento.inputs.CriarPagamentoInput;
import com.br.integramove.application.pagamento.outputs.PagamentoOutput;
import com.br.integramove.application.pagamento.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos/{alunoId}/pagamentos")
public class PagamentoController {

    private final CriarPagamento criarPagamento;
    private final ListarPagamentosPorAluno listarPagamentos;
    private final BuscarPagamento buscarPagamento;
    private final PagarPagamento pagarPagamento;
    private final CancelarPagamento cancelarPagamento;
    private final PagamentoMapper pagamentoMapper;

    public PagamentoController(
            CriarPagamento criarPagamento,
            ListarPagamentosPorAluno listarPagamentos,
            BuscarPagamento buscarPagamento,
            PagarPagamento pagarPagamento,
            CancelarPagamento cancelarPagamento,
            PagamentoMapper pagamentoMapper
    ) {
        this.criarPagamento = criarPagamento;
        this.listarPagamentos = listarPagamentos;
        this.buscarPagamento = buscarPagamento;
        this.pagarPagamento = pagarPagamento;
        this.cancelarPagamento = cancelarPagamento;
        this.pagamentoMapper = pagamentoMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PagamentoResponseDTO criar(
            @PathVariable String alunoId,
            @RequestBody PagamentoRequestDTO request
    ) {
        PagamentoOutput output = criarPagamento.executar(
                pagamentoMapper.toInput(alunoId, request)
        );

        return pagamentoMapper.toResponse(output);
    }

    @GetMapping
    public List<PagamentoResponseDTO> listarPorAluno(@PathVariable String alunoId) {
        return listarPagamentos.executar(alunoId)
                .stream()
                .map(pagamentoMapper::toResponse)
                .toList();
    }

    @GetMapping("/{pagamentoId}")
    public PagamentoResponseDTO buscarPorId(
            @PathVariable String alunoId,
            @PathVariable String pagamentoId
    ) {
        PagamentoOutput output = buscarPagamento.executar(
                alunoId,
                pagamentoId
        );

        return pagamentoMapper.toResponse(output);
    }

    @PatchMapping("/{pagamentoId}/pagar")
    public PagamentoResponseDTO pagar(
            @PathVariable String alunoId,
            @PathVariable String pagamentoId,
            @RequestBody PagarPagamentoRequestDTO request
    ) {
        PagamentoOutput output = pagarPagamento.executar(
                alunoId,
                pagamentoId,
                pagamentoMapper.toInput(request)
        );

        return pagamentoMapper.toResponse(output);
    }

    @PatchMapping("/{pagamentoId}/cancelar")
    public PagamentoResponseDTO cancelar(
            @PathVariable String alunoId,
            @PathVariable String pagamentoId
    ) {
        PagamentoOutput output = cancelarPagamento.executar(
                alunoId,
                pagamentoId
        );

        return pagamentoMapper.toResponse(output);
    }
}