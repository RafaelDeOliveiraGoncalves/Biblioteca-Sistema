package com.sistema.biblioteca.exceptions;

public class CopiaNaoDisponivelException extends RuntimeException{
    public CopiaNaoDisponivelException(){
        super("Todas as unidades desse livro foram emprestados");
    }
}
