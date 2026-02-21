package com.sistema.biblioteca.exceptions;

public class CopiaEmprestadaException extends RuntimeException{
    public CopiaEmprestadaException(){
        super("Livro não pode remover livro que está sendo emprestado.");
    }
}
