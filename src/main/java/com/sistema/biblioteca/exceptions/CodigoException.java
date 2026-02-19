package com.sistema.biblioteca.exceptions;

public class CodigoException extends RuntimeException{
    public CodigoException(){
        super("Código Inválido");
    }
}
