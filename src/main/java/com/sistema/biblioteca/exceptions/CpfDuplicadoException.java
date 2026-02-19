package com.sistema.biblioteca.exceptions;

public class CpfDuplicadoException extends RuntimeException{
    public CpfDuplicadoException(){
        super("Usuário já cadastrado.");
    }
}
