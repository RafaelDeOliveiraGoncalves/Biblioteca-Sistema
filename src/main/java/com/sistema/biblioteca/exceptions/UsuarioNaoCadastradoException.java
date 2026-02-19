package com.sistema.biblioteca.exceptions;

public class UsuarioNaoCadastradoException extends RuntimeException {

    public UsuarioNaoCadastradoException(){
        super("Usuário não cadastrado no sistema");
    }

    public UsuarioNaoCadastradoException(String cpf){
        super("Usuário com CPF: " + cpf + " não cadastrado");
    }
}
