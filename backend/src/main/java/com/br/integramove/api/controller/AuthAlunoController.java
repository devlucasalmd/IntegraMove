package com.br.integramove.api.controller;

import com.br.integramove.api.dto.request.LoginAlunoRequestDTO;
import com.br.integramove.api.dto.request.TrocarSenhaAlunoRequestDTO;
import com.br.integramove.api.dto.response.LoginAlunoResponseDTO;
import com.br.integramove.api.mapper.AuthAlunoMapper;
import com.br.integramove.application.auth.aluno.inputs.AutenticarAlunoInput;
import com.br.integramove.application.auth.aluno.inputs.TrocarSenhaAlunoInput;
import com.br.integramove.application.auth.aluno.outputs.AutenticarAlunoOutput;
import com.br.integramove.application.auth.aluno.services.AutenticarAluno;
import com.br.integramove.application.auth.aluno.services.TrocarSenhaAluno;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/aluno")
public class AuthAlunoController {

    private final AutenticarAluno autenticarAluno;
    private final TrocarSenhaAluno trocarSenhaAluno;

    public AuthAlunoController(
            AutenticarAluno autenticarAluno,
            TrocarSenhaAluno trocarSenhaAluno
    ) {
        this.autenticarAluno = autenticarAluno;
        this.trocarSenhaAluno = trocarSenhaAluno;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginAlunoResponseDTO> login(@RequestBody LoginAlunoRequestDTO request) {

        AutenticarAlunoInput input = AuthAlunoMapper.toInput(request);
        AutenticarAlunoOutput output = autenticarAluno.autenticar(input);

        return ResponseEntity.ok(AuthAlunoMapper.toResponse(output));
    }

    @PostMapping("/trocar-senha")
    public ResponseEntity<Void> trocarSenha(@RequestBody TrocarSenhaAlunoRequestDTO request) {

        TrocarSenhaAlunoInput input = AuthAlunoMapper.toInput(request);
        trocarSenhaAluno.trocar(input);

        return ResponseEntity.noContent().build();
    }
}