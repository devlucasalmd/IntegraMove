package com.br.integramove.api.exception;

import com.br.integramove.api.exception.aluno.*;
import com.br.integramove.api.exception.auth.CredenciaisInvalidasException;
import com.br.integramove.api.exception.avaliacao.AvaliacaoNaoEncontradaException;
import com.br.integramove.api.exception.plano.PlanoNaoEncontradoException;
import com.br.integramove.api.exception.treino.aluno.TreinoAlunoNaoEncontradoException;
import com.br.integramove.api.exception.treino.exercicio.ExercicioNaoEncontradoException;
import com.br.integramove.api.exception.treino.exercicio.TreinoItemNaoEncontradoException;
import com.br.integramove.api.exception.treino.treino.TreinoNaoEncontradoException;
import com.br.integramove.api.exception.usuario.UsuarioNaoEncontradoException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex
    ) {

        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        erros.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erros);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(
            ConstraintViolationException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "erro",
                        ex.getMessage()
                ));
    }


    @ExceptionHandler(AlunoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleAlunoNaoEncontrado(
            AlunoNaoEncontradoException ex
    ) {

        return response(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }


    @ExceptionHandler({
            CpfJaCadastradoException.class,
            EmailInvalidoException.class,
            CpfInvalidoException.class,
            NomeInvalidoException.class,
            SenhaInvalidaException.class
    })
    public ResponseEntity<Map<String, String>> handleAlunoDadosInvalidos(
            RuntimeException ex
    ) {

        return response(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }


    @ExceptionHandler(AvaliacaoNaoEncontradaException.class)
    public ResponseEntity<Map<String, String>> handleAvaliacaoNaoEncontrada(
            AvaliacaoNaoEncontradaException ex
    ) {

        return response(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }


    @ExceptionHandler(PlanoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handlePlanoNaoEncontrado(
            PlanoNaoEncontradoException ex
    ) {

        return response(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }


    @ExceptionHandler({
            TreinoNaoEncontradoException.class,
            TreinoAlunoNaoEncontradoException.class,
            TreinoItemNaoEncontradoException.class
    })
    public ResponseEntity<Map<String, String>> handleTreinoNaoEncontrado(
            RuntimeException ex
    ) {

        return response(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }


    @ExceptionHandler(ExercicioNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleExercicioNaoEncontrado(
            ExercicioNaoEncontradoException ex
    ) {

        return response(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }

    @ExceptionHandler(CredenciaisInvalidasException.class)
    public ResponseEntity<Map<String, String>> handleCredenciaisInvalidas(
            CredenciaisInvalidasException ex
    ) {

        return response(
                HttpStatus.UNAUTHORIZED,
                ex.getMessage()
        );
    }


    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioNaoEncontrado(
            UsuarioNaoEncontradoException ex
    ) {

        return response(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(
            Exception ex
    ) {

        return response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno no servidor."
        );
    }


    private ResponseEntity<Map<String, String>> response(
            HttpStatus status,
            String mensagem
    ) {

        return ResponseEntity
                .status(status)
                .body(Map.of(
                        "erro",
                        mensagem
                ));
    }
}
