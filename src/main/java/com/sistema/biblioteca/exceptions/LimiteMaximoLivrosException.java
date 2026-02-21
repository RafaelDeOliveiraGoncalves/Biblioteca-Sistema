package com.sistema.biblioteca.exceptions;

public class LimiteMaximoLivrosException extends RuntimeException{
    public LimiteMaximoLivrosException(){
        super("Usuário atingiu limite máximo de livros emprestados");
    }
}
