package com.sistema.biblioteca.exceptions;

public class UsuarioTemLivroEmprestadoExeption extends RuntimeException{
    public UsuarioTemLivroEmprestadoExeption(){
        super("Usuário não pode ser removido pois possui livros emprestados.");
    }
}
