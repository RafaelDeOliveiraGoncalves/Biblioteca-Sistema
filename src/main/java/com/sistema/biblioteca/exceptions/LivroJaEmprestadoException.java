package com.sistema.biblioteca.exceptions;

public class LivroJaEmprestadoException extends RuntimeException{
    public LivroJaEmprestadoException(){
        super("Usuário já possui esse livro emprestado");
    }
}
