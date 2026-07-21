package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.AtualizarContaReceberRequestDTO;
import com.br.integramove.api.dto.request.ContaReceberRequestDTO;
import com.br.integramove.api.dto.request.ReceberContaReceberRequestDTO;
import com.br.integramove.api.dto.response.ContaReceberResponseDTO;
import com.br.integramove.api.mapper.ContaReceberMapper;
import com.br.integramove.application.contaReceber.outputs.ContaReceberOutput;
import com.br.integramove.application.contaReceber.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas-receber")
public class ContaReceberController {

    private final CriarContaReceber criarContaReceber;
    private final BuscarContaReceber buscarContaReceber;
    private final AtualizarContaReceber atualizarContaReceber;
    private final ListarContasReceber listarContasReceber;
    private final ReceberContaReceber receberContaReceber;
    private final CancelarContaReceber cancelarContaReceber;
    private final ContaReceberMapper contaReceberMapper;

    public ContaReceberController(
            CriarContaReceber criarContaReceber,
            BuscarContaReceber buscarContaReceber,
            AtualizarContaReceber atualizarContaReceber,
            ListarContasReceber listarContasReceber,
            ReceberContaReceber receberContaReceber,
            CancelarContaReceber cancelarContaReceber,
            ContaReceberMapper contaReceberMapper
    ) {
        this.criarContaReceber = criarContaReceber;
        this.buscarContaReceber = buscarContaReceber;
        this.atualizarContaReceber = atualizarContaReceber;
        this.listarContasReceber = listarContasReceber;
        this.receberContaReceber = receberContaReceber;
        this.cancelarContaReceber = cancelarContaReceber;
        this.contaReceberMapper = contaReceberMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaReceberResponseDTO criar(@RequestBody ContaReceberRequestDTO request) {
        ContaReceberOutput output = criarContaReceber.executar(
                contaReceberMapper.toInput(request)
        );

        return contaReceberMapper.toResponse(output);
    }

    @GetMapping
    public List<ContaReceberResponseDTO> listar() {
        return listarContasReceber.executar()
                .stream()
                .map(contaReceberMapper::toResponse)
                .toList();
    }

    @GetMapping("/{contaReceberId}")
    public ContaReceberResponseDTO buscarPorId(@PathVariable String contaReceberId) {
        ContaReceberOutput output = buscarContaReceber.executar(contaReceberId);
        return contaReceberMapper.toResponse(output);
    }

    @PutMapping("/{contaReceberId}")
    public ContaReceberResponseDTO atualizar(
            @PathVariable String contaReceberId,
            @RequestBody AtualizarContaReceberRequestDTO request
    ) {
        ContaReceberOutput output = atualizarContaReceber.executar(
                contaReceberId,
                contaReceberMapper.toInput(request)
        );

        return contaReceberMapper.toResponse(output);
    }

    @PatchMapping("/{contaReceberId}/receber")
    public ContaReceberResponseDTO receber(
            @PathVariable String contaReceberId,
            @RequestBody ReceberContaReceberRequestDTO request
    ) {
        ContaReceberOutput output = receberContaReceber.executar(
                contaReceberId,
                contaReceberMapper.toInput(request)
        );

        return contaReceberMapper.toResponse(output);
    }

    @PatchMapping("/{contaReceberId}/cancelar")
    public ContaReceberResponseDTO cancelar(@PathVariable String contaReceberId) {
        ContaReceberOutput output = cancelarContaReceber.executar(contaReceberId);
        return contaReceberMapper.toResponse(output);
    }
}
