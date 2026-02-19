package com.sistema.biblioteca.exceptions;

public class NenhumaCopiaEmprestadaException extends RuntimeException{
    public NenhumaCopiaEmprestadaException(){
        super("Nenhuma copia desse livro foi emprestada");
    }
}
