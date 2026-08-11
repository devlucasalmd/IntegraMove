package com.br.integramove.api.mapper;

import com.br.integramove.api.dto.request.LoginAlunoRequestDTO;
import com.br.integramove.api.dto.request.TrocarSenhaAlunoRequestDTO;
import com.br.integramove.api.dto.response.LoginAlunoResponseDTO;
import com.br.integramove.application.auth.aluno.inputs.AutenticarAlunoInput;
import com.br.integramove.application.auth.aluno.inputs.TrocarSenhaAlunoInput;
import com.br.integramove.application.auth.aluno.outputs.AutenticarAlunoOutput;
import org.springframework.stereotype.Component;

@Component
public class AuthAlunoMapper {

    public static AutenticarAlunoInput toInput(LoginAlunoRequestDTO dto) {
        return new AutenticarAlunoInput(
                dto.cpf(),
                dto.senha()
        );
    }

    public static TrocarSenhaAlunoInput toInput(TrocarSenhaAlunoRequestDTO dto) {
        return new TrocarSenhaAlunoInput(
                dto.alunoId(),
                dto.senhaAtual(),
                dto.novaSenha()
        );
    }

    public static LoginAlunoResponseDTO toResponse(AutenticarAlunoOutput output) {
        return new LoginAlunoResponseDTO(
                output.alunoId(),
                output.nome(),
                output.precisaTrocarSenha()
        );
    }
}