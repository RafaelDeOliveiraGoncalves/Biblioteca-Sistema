package com.sistema.biblioteca.controller.common;

import com.sistema.biblioteca.controller.dto.ErroResposta;
import com.sistema.biblioteca.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CodigoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleCodigoException(CodigoException e){
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "código inválido",
                List.of()
        );
    }

    @ExceptionHandler(CodigoLivroDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleCodigoLivroDuplicadoException(CodigoLivroDuplicadoException e){
        return ErroResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(CopiaNaoDisponivelException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleCopiaNaoDisponivelException(CopiaNaoDisponivelException e){
        return ErroResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(CpfDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleCpfDuplicadoException(CpfDuplicadoException e){
        return ErroResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(LivroNaoCadastradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta handleLivroNaoCadastradoException(LivroNaoCadastradoException e){
        return new ErroResposta(HttpStatus.NOT_FOUND.value(), e.getMessage(),List.of());
    }

    @ExceptionHandler(NenhumaCopiaEmprestadaException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleNenhumaCopiaEmprestadaException(NenhumaCopiaEmprestadaException e){
        return ErroResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(NomeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleNomeException(NomeException e){
        return ErroResposta.respostaPadrao(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta handleErrosNaoTratados(RuntimeException e){
        return new ErroResposta(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro no servidor.",
                List.of()
        );
    }

}
