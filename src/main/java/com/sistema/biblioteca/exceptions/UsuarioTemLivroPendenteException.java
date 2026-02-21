package com.sistema.biblioteca.exceptions;

public class UsuarioTemLivroPendenteException extends RuntimeException{
    public UsuarioTemLivroPendenteException(){
        super("Usuário tem livro pendente");
    }
}
