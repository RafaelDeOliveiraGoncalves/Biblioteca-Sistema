package com.sistema.biblioteca.exceptions;

public class CodigoLivroDuplicadoException extends RuntimeException{
    public CodigoLivroDuplicadoException(int codigo){
        super("Já existe um livro cadastrado com o código: " + codigo);
    }
}
